package com.arcana.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arcana.dto.ReadingRequest;
import com.arcana.dto.ReadingResponse;
import com.arcana.model.Reading;
import com.arcana.service.ReadingService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/readings")
@AllArgsConstructor
public class ReadingController {

    private final ReadingService readingService;

    //Lettura salvata nel DB
    @PostMapping("/create")
    public ResponseEntity<Void> createReading(@RequestBody ReadingRequest readingRequest) {
       readingService.saveReading(readingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    //Trova tutte le letture dal DB
    @GetMapping
    public ResponseEntity<List<ReadingResponse>> getAllReadings() {
        return status(HttpStatus.OK).body(readingService.getAllReadings());
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<List<ReadingResponse>> getReadingsByEmail(@PathVariable("email") String email) {
        return status(HttpStatus.OK).body(readingService.getReadingsByEmail(email));
    }
}