package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.model.dto.ImageDTO;
import com.example.demo.model.dto.InkusageDTO;
import com.example.demo.service.ImageService;
import com.example.demo.serviceInterfaces.IImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/api/images/all")
    public ResponseEntity<?> getImages(){
        List<Image> images = this.imageService.getAllImages();

        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    //this will return pagination json - use data.content to get the image data
    //http://localhost:8080/api/images/daterange?startDate=2021-01-01&endDate=2021-02-06&pageNumber=0&pageSize=50&sortBy=date&sortDir=desc
    @GetMapping("/api/images/daterange")
    public ResponseEntity<?> getImagesBetweenDates(String sortBy, String sortDir, @RequestParam Date startDate, @RequestParam Date endDate){
        List<Image> images = this.imageService.findAllByDateBetween(startDate, endDate);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/api/images/mediatypes")
    public ResponseEntity<?> getMediaTypes(@RequestParam Date startDate, @RequestParam Date endDate){
        List<String> mediaTypes = this.imageService.getMediaTypes(startDate, endDate);
        List<String> mediaTypesWithoutDuplicates = new ArrayList<>(new HashSet<>(mediaTypes));
        return new ResponseEntity<>(mediaTypesWithoutDuplicates, HttpStatus.OK);
    }

    @GetMapping("/api/images/inktypes")
    public ResponseEntity<?> getInkTypes(@RequestParam Date startDate, @RequestParam Date endDate){
        List<String> inkTypes=new ArrayList<String>(){{
            add("Black");
            add("Cyan");
            add("Magenta");
            add("Yellow");
        }};
        return new ResponseEntity<>(inkTypes, HttpStatus.OK);
    }

    @GetMapping("/api/images/parsed")
    public ResponseEntity<?> getImagesParsed(@RequestParam Date startDate, @RequestParam Date endDate){
        List<Image> images = this.imageService.findAllByDateBetween(startDate, endDate);
        //var content = images.getContent().get(0);
        //var content = images.getContent();
        List<ImageDTO> imageDTOS = new ArrayList<>();

        if (images.size() > 0) {
            // add first imagedto in order to compare later
            Map<String, Float> mediaTypes = new HashMap<String, Float>();
            mediaTypes.put(images.get(0).getMediaType(), images.get(0).getImageLength() * images.get(0).getImageWidth());
            imageDTOS.add(new ImageDTO(images.get(0).getDate(), mediaTypes));
            //loop through all image data
            for (int i = 1; i < images.size(); i++) {
                //if previous date == current date
                var currentDate = images.get(i).getDate();
                var previousDate = images.get(i - 1).getDate();
                if (currentDate.equals(previousDate)) {
                    //if mediatype has already been added, increment the previous value
                    var mediatype = images.get(i).getMediaType();
                    var mediatypeExists = mediaTypes.containsKey(mediatype);
                    if (mediatypeExists) {
                        var previousSqm = mediaTypes.get(images.get(i).getMediaType());
                        var Sqm = images.get(i).getImageLength() * images.get(i).getImageWidth();
                        mediaTypes.put(images.get(i).getMediaType(), previousSqm += Sqm);
                    }
                    //else add the media type for the first time
                    else {
                        mediaTypes.put(images.get(i).getMediaType(), images.get(i).getImageLength() * images.get(i).getImageWidth());
                    }
                }
                //else if previous date != current date
                else {
                    // create a new hashmap, add the media type and add the new imagedto
                    mediaTypes = new HashMap<String, Float>();
                    var printedSqmExists = images.get(i).getImageLength() * images.get(i).getImageWidth() > 0;
                    if (printedSqmExists) {
                        mediaTypes.put(images.get(i).getMediaType(), images.get(i).getImageLength() * images.get(i).getImageWidth());
                        imageDTOS.add(new ImageDTO(images.get(i).getDate(), mediaTypes));
                    }
                }
            }
        }
        return new ResponseEntity<>(imageDTOS, HttpStatus.OK);
    }

    @GetMapping("/api/images/inkusage")
    public ResponseEntity<?> getInkUsageParsed(@RequestParam Date startDate, @RequestParam Date endDate){
        List<Image> images = this.imageService.findAllByDateBetween(startDate, endDate);
        List<InkusageDTO> inkusageDTOS= new ArrayList<>();

        if (images.size() > 0) {
            // add first imagedto in order to compare later
            Map<String, Float> inkTypes = new HashMap<String, Float>();
            if (images.get(0).getAccountedInkBlack()/1000 > 0) { inkTypes.put("Black", images.get(0).getAccountedInkBlack()/1000);}
            if (images.get(0).getAccountedInkCyan()/1000 > 0) {inkTypes.put("Cyan", images.get(0).getAccountedInkCyan()/1000);}
            if (images.get(0).getAccountedInkMagenta()/1000 > 0) { inkTypes.put("Magenta", images.get(0).getAccountedInkMagenta()/1000);}
            if (images.get(0).getAccountedInkYellow()/1000 > 0) {inkTypes.put("Yellow", images.get(0).getAccountedInkYellow()/1000);}
            inkusageDTOS.add(new InkusageDTO(images.get(0).getDate(), inkTypes));
            //loop through all image data
            for (int i = 1; i < images.size(); i++) {
                //if previous date == current date
                var currentDate = images.get(i).getDate();
                var previousDate = images.get(i - 1).getDate();
                if (currentDate.equals(previousDate)) {
                    //if inktype has already been added, increment the previous value
                    if (inkTypes.containsKey("Black")) {
                        var previousLiter = inkTypes.get("Black");
                        var Liter = images.get(i).getAccountedInkBlack()/1000;
                        inkTypes.put("Black", previousLiter += Liter);
                    } else {
                        inkTypes.put("Black", images.get(i).getAccountedInkBlack()/1000);
                    }
                    if (inkTypes.containsKey("Cyan")) {
                        var previousLiter = inkTypes.get("Cyan");
                        var Liter = images.get(i).getAccountedInkCyan()/1000;
                        inkTypes.put("Cyan", previousLiter += Liter);
                    } else {
                        inkTypes.put("Cyan", images.get(i).getAccountedInkCyan()/1000);
                    }
                    if (inkTypes.containsKey("Magenta")) {
                        var previousLiter = inkTypes.get("Magenta");
                        var Liter = images.get(i).getAccountedInkMagenta()/1000;
                        inkTypes.put("Magenta", previousLiter += Liter);
                    } else {
                        inkTypes.put("Magenta", images.get(i).getAccountedInkMagenta()/1000);
                    }
                    if (inkTypes.containsKey("Yellow")) {
                        var previousLiter = inkTypes.get("Yellow");
                        var Liter = images.get(i).getAccountedInkYellow()/1000;
                        inkTypes.put("Yellow", previousLiter += Liter);
                    }
                    //else add the media type for the first time
                    else {
                        inkTypes.put("Yellow", images.get(i).getAccountedInkYellow()/1000);
                    }
                }
                //else if previous date != current date
                else {
                    // create a new hashmap, add the media type and add the new imagedto
                    inkTypes = new HashMap<String, Float>();
                    var blackInk = images.get(i).getAccountedInkBlack()/1000 > 0;
                    var cyanInk = images.get(i).getAccountedInkBlack()/1000 > 0;
                    var magentaInk = images.get(i).getAccountedInkBlack()/1000 > 0;
                    var yellowInk = images.get(i).getAccountedInkBlack()/1000 > 0;
                    //inkTypes.put(images.get(i).getMediaType(), images.get(i).getImageLength() * images.get(i).getImageWidth());
                    if (blackInk) { inkTypes.put("Black", images.get(i).getAccountedInkBlack()/1000);}
                    if (cyanInk) {inkTypes.put("Cyan", images.get(i).getAccountedInkCyan()/1000);}
                    if (magentaInk) { inkTypes.put("Magenta", images.get(i).getAccountedInkMagenta()/1000);}
                    if (yellowInk) {inkTypes.put("Yellow", images.get(i).getAccountedInkYellow()/1000);}
                    if (blackInk && cyanInk && magentaInk && yellowInk) {
                        inkusageDTOS.add(new InkusageDTO(images.get(i).getDate(), inkTypes));
                    }
                }
            }
        }
        return new ResponseEntity<>(inkusageDTOS, HttpStatus.OK);
    }

}
