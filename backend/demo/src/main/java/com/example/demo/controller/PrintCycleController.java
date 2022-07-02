package com.example.demo.controller;

import com.example.demo.model.MediaPrepare;
import com.example.demo.model.PrintCycle;
import com.example.demo.model.dto.MediaTypesDTO;
import com.example.demo.model.dto.PrintmodeDTO;
import com.example.demo.model.dto.ToptenDTO;
import com.example.demo.service.MediaPrepareService;
import com.example.demo.service.PrintCycleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.*;
import java.util.Map.Entry;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PrintCycleController {

    private final PrintCycleService printCycleService;
    private final MediaPrepareService mediaPrepareService;

    public PrintCycleController(PrintCycleService printCycleService, MediaPrepareService mediaPrepareService) {
        this.printCycleService = printCycleService;
        this.mediaPrepareService = mediaPrepareService;
    }

    @GetMapping("/api/print-cycle/all")
    public ResponseEntity<?> getAllPrintCycles(){
        List<PrintCycle> printCycles = this.printCycleService.getAllPrintCycles();

        if(printCycles.isEmpty()){
            return new ResponseEntity<>(printCycles, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(printCycles, HttpStatus.OK);
    }

    @GetMapping("/api/print-cycle/printmodes")
    public ResponseEntity<?> getPrintModes(@RequestParam Date startDate, @RequestParam Date endDate){
        List<String> printModes = this.printCycleService.getPrintModes(startDate, endDate);
        List<String> printModesWithoutDuplicates = new ArrayList<>(new HashSet<>(printModes));
//        for (int i = 0; i < printModesWithoutDuplicates.size(); i++) {
//            if (printModesWithoutDuplicates.get(i).equals("1_pass")) {
//                printModesWithoutDuplicates.set(i, "Max speed");
//            } else if (printModesWithoutDuplicates.get(i).equals("8_pass")) {
//                printModesWithoutDuplicates.set(i, "Specialty");
//            }
//        }

        return new ResponseEntity<>(printModesWithoutDuplicates, HttpStatus.OK);
    }

    @GetMapping("/api/print-cycle/machineids")
    public ResponseEntity<?> getMachineIds(@RequestParam Date startDate, @RequestParam Date endDate){
        List<String> machineIds = this.printCycleService.getMachineId(startDate, endDate);
        Set<String> set = new HashSet<>(machineIds);
        machineIds.clear();
        machineIds.addAll(set);
        //List<String> machineIdsWithoutDuplicates = new ArrayList<>(new HashSet<>(machineIds));
        return new ResponseEntity<>(machineIds, HttpStatus.OK);
    }

    @GetMapping("/api/print-cycle/mediatypes")
    public ResponseEntity<?> getMediatypes(@RequestParam Date startDate, @RequestParam Date endDate){
        List<String> mediaTypes = this.mediaPrepareService.getMediaTypes(startDate, endDate);
        Set<String> set = new HashSet<>(mediaTypes);
        mediaTypes.clear();
        mediaTypes.addAll(set);
        //List<String> mediaTypesWithoutDuplicates = new ArrayList<>(new HashSet<>(mediaTypes));
        return new ResponseEntity<>(mediaTypes, HttpStatus.OK);
    }

    @GetMapping("/api/print-cycle/parsed")
    public ResponseEntity<?> getPrintModesParsed(@RequestParam Date startDate, @RequestParam Date endDate){
        List<PrintCycle> printCycles = this.printCycleService.findAllByDateBetween(startDate, endDate);
        List<PrintmodeDTO> printmodeDTOS = new ArrayList<>();

        if (printCycles.size() > 0) {
            // add first printmodeDTO in order to compare later
            Map<String, Float> printModes  = new HashMap<String, Float>();
            printModes.put(printCycles.get(0).getPrintMode(), printCycles.get(0).getSquareDecimeter());
            printmodeDTOS.add(new PrintmodeDTO(printCycles.get(0).getDate(), printModes));
            //loop through all image data
            for (int i = 1; i < printCycles.size(); i++) {
                //if previous date == current date
                var currentDate = printCycles.get(i).getDate();
                var previousDate = printCycles.get(i-1).getDate();
                if (currentDate.equals(previousDate)) {
                    //if mediatype has already been added, increment the previous value
                    var mediatype = printCycles.get(i).getPrintMode();
                    var mediatypeExists = printModes.containsKey(mediatype);
                    if (mediatypeExists) {
                        var previousSqm = printModes.get(printCycles.get(i).getPrintMode());
                        var Sqm =  printCycles.get(i).getSquareDecimeter();
                        printModes.put(printCycles.get(i).getPrintMode(), previousSqm += Sqm);
                    }
                    //else add the media type for the first time
                    else {
                        printModes.put(printCycles.get(i).getPrintMode(), printCycles.get(i).getSquareDecimeter());
                    }
                }
                //else if previous date != current date
                else {
                    // create a new hashmap, add the media type and add the new imagedto
                    printModes  = new HashMap<String, Float>();
                    printModes.put(printCycles.get(i).getPrintMode(), printCycles.get(i).getSquareDecimeter());
                    printmodeDTOS.add(new PrintmodeDTO(printCycles.get(i).getDate(), printModes));
                }
            }
        }

        return new ResponseEntity<>(printmodeDTOS, HttpStatus.OK);
    }

    @GetMapping("/api/print-cycle/toptenparsed")
    public ResponseEntity<?> getTopTenMachinesParsed(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<PrintCycle> printCycles = this.printCycleService.findAllByDateBetween(startDate, endDate);
        //just a map of machineID to squaredecimeter
        List<ToptenDTO> toptenDTOS = new ArrayList<>();

        if (printCycles.size() > 0) {
            // add first printmodeDTO in order to compare later
            HashMap<String, Float> machineSquaredecimeter  = new HashMap<String, Float>();
            machineSquaredecimeter.put(printCycles.get(0).getMachineId().toString(), printCycles.get(0).getSquareDecimeter());

            //loop through all image data
            for (int i = 1; i < printCycles.size(); i++) {
                //if mediatype has already been added, increment the previous value
                var machineId = printCycles.get(i).getMachineId().toString();
                var machineIdExists = machineSquaredecimeter.containsKey(machineId);
                if (machineIdExists) {
                    var previousSqm = machineSquaredecimeter.get(printCycles.get(i).getMachineId().toString());
                    var Sqm =  printCycles.get(i).getSquareDecimeter();
                    machineSquaredecimeter.put(printCycles.get(i).getMachineId().toString(), previousSqm += Sqm);
                }
                //else add the media type for the first time
                else {
                    machineSquaredecimeter.put(printCycles.get(i).getMachineId().toString(), printCycles.get(i).getSquareDecimeter());
                }
            }

            var sortedMachineSquareDecimeter = sortByComparator(machineSquaredecimeter);
            toptenDTOS.add(new ToptenDTO(sortedMachineSquareDecimeter));
        }
        return new ResponseEntity<>(toptenDTOS, HttpStatus.OK);
    }

    @GetMapping("/api/print-cycle/mediatypespermachine")
    public ResponseEntity<?> getMediaTypePerMachinesParsed(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<PrintCycle> printCycles = this.printCycleService.findAllByDateBetween(startDate, endDate);
        List<MediaPrepare> mediaPrepares = this.mediaPrepareService.findAllByDateBetween(startDate, endDate);


        //just a map of mediaTypes to squaredecimeter
        List<MediaTypesDTO> mediaTypesDTOS = new ArrayList<>();

        HashMap<String, String> mediaPrepareMap = new HashMap<String, String>();
        HashMap<String, Float> typesPerMachine = new HashMap<String, Float>();

        if (printCycles.size() > 0 || mediaPrepares.size() > 0) {

            //create hashmap of printcycles all values
            for (int i = 0; i < mediaPrepares.size(); i++) {
                //assume enginecycleid is unique
                String eng = mediaPrepares.get(i).getEngineCycleId();
                String medprep = mediaPrepares.get(i).getMediaTypeDisplayName();
                if (!eng.equals("-1")) {
                    mediaPrepareMap.put(mediaPrepares.get(i).getEngineCycleId(),
                            mediaPrepares.get(i).getMediaTypeDisplayName());
                }

            }
            for (int i = 0; i < printCycles.size(); i++) {
                Boolean mediaTrue = mediaPrepareMap.containsKey(printCycles.get(i).getEngineCycleId());
                float sqm = printCycles.get(i).getSquareDecimeter()/100;
                String med = mediaPrepareMap.get(printCycles.get(i).getEngineCycleId());
                if (mediaPrepareMap.containsKey(printCycles.get(i).getEngineCycleId())) {
                    typesPerMachine.put(mediaPrepareMap.get(printCycles.get(i).getEngineCycleId()),
                            printCycles.get(i).getSquareDecimeter()/100);
                }
            }

        }
        return new ResponseEntity<>(typesPerMachine, HttpStatus.OK);
    }

    private Map<String, Float> sortByComparator(Map<String, Float> unsorted) {
        List<Entry<String, Float>> list = new LinkedList<>(unsorted.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Float>>() {
            @Override
            public int compare(Entry<String, Float> o1, Entry<String, Float> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
        for (Entry<String, Float> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
