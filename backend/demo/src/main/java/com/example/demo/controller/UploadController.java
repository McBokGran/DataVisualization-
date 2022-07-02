package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.model.MediaPrepare;
import com.example.demo.model.PrintCycle;
import com.example.demo.model.Test;
import com.example.demo.serviceInterfaces.IImageService;
import com.example.demo.serviceInterfaces.IMediaPrepareService;
import com.example.demo.serviceInterfaces.IPrintCycleService;
import com.example.demo.serviceInterfaces.ITestService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Parser;
import javax.transaction.Transactional;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UploadController {

    @Autowired
    private IImageService imageService;

    @Autowired
    private IMediaPrepareService mediaPrepareService;

    @Autowired
    private IPrintCycleService printCycleService;

    CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-csv-file/images")
    public String uploadCSVFileImages(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Image> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Image.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build();

                // convert `CsvToBean` object to list of users
                List<Image> images = csvToBean.parse();

                //save objects to database
                for (Image image : images) {
                    imageService.save(image);
                }

                // save users list on model
                model.addAttribute("images", images);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }

    @PostMapping("/upload-csv-file/mediaprepare")
    public String uploadCSVFileMediaprepare(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {



            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<MediaPrepare> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(MediaPrepare.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build();



                // convert `CsvToBean` object to list of users
                List<MediaPrepare> mediaPrepares = csvToBean.parse();


                //save objects to database
                for (MediaPrepare mediaPrepare : mediaPrepares) {
                    mediaPrepareService.save(mediaPrepare);
                }


                try (CSVReader reader2 = new CSVReaderBuilder(new InputStreamReader(file
                        .getInputStream()))
                        .withCSVParser(csvParser)
                        .build()) {

                    List<String[]> r = reader2.readAll();
                    if(r.get(0).length>39){
                        r.stream().skip(1).forEach(l-> {
                            for(int i=38; i<l.length; i++){
                                this.mediaPrepareService.addExtraColumn(Long.parseLong(l[0]), r.get(0)[i], l[i]);
                            }
                        });

                    }

                } catch (Exception ex){

                }

                // save users list on model
                model.addAttribute("mediaPrepares", mediaPrepares);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }

    @PostMapping("/upload-csv-file/printcycle")
    public ResponseEntity<?> uploadCSVFilePrintcycle(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<PrintCycle> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(PrintCycle.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build();

                // convert `CsvToBean` object to list of users
                List<PrintCycle> printCycles = csvToBean.parse();


                //save objects to database
                for (PrintCycle printCycle : printCycles) {
                    printCycleService.save(printCycle);
                }


                try (CSVReader reader2 = new CSVReaderBuilder(new InputStreamReader(file
                        .getInputStream()))
                        .withCSVParser(csvParser)
                        .build()) {

                    List<String[]> r = reader2.readAll();
                    if(r.get(0).length>11){
                        r.stream().skip(1).forEach(l-> {
                            for(int i=11; i<l.length; i++){
                                this.printCycleService.addExtraColumn(Long.parseLong(l[0]), r.get(0)[i], l[i]);
                            }
                        });

                    }

                } catch (Exception ex){

                }
                // save users list on model
                model.addAttribute("printCycles", printCycles);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return ResponseEntity.ok(model);
    }

//    @PostMapping("/upload-csv-file/testing")
//    public String testing( Model model) {
//        csvMedia(model);
//        csvImage(model);
//
//        return "file-upload-status";
//    }

    public void csvImageT(){
        File directoryPath = new File("/Users/sadboi/Downloads/700/engineControl/accounting/image/");
        File filesList[] = directoryPath.listFiles();
        for(File file : filesList) {
            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new FileReader(file))) {

                // create csv bean reader
                CsvToBean<Image> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Image.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build();

                // convert `CsvToBean` object to list of users
                List<Image> images = csvToBean.parse();

                //save objects to database
                for (Image image : images) {
                    imageService.save(image);
                }



            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    public void csvMediaT(){
        File directoryPath = new File("/Users/sadboi/Downloads/700/engineControl/MediaPrepare/");
        File filesList[] = directoryPath.listFiles();
        for(File file : filesList) {
            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new FileReader(file))) {

                // create csv bean reader
                CsvToBean<MediaPrepare> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(MediaPrepare.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build();

                // convert `CsvToBean` object to list of users
                List<MediaPrepare> mediaPrepares = csvToBean.parse();

                //save objects to database
//                for (MediaPrepare mediaPrepare : mediaPrepares) {
//                    mediaPrepareService.save(mediaPrepare);
//                }

                try (CSVReader reader2 = new CSVReaderBuilder(new FileReader(file))
                        .withCSVParser(csvParser)
                        .build()) {

                    List<String[]> r = reader2.readAll();
                    if(r.get(0).length>38){
                        r.stream().skip(1).forEach(l-> {
                            for(int i=38; i<l.length; i++){
                                this.mediaPrepareService.addExtraColumn(Long.parseLong(l[0]), r.get(0)[i], l[i]);
                            }
                        });

                    }

                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }


            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }


    @Scheduled(fixedRate = 500000)
    @Transactional
    public void tesingP (){

        csvMediaT();
        csvImageT();
    }
}