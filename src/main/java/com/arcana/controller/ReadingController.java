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

    
    @PostMapping("/create")
    public ResponseEntity<Void> createPost(@RequestBody ReadingRequest postRequest) {
       readingService.saveReading(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<ReadingResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(readingService.getAllPosts());
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<List<ReadingResponse>> getPostsByUsername(@PathVariable("email") String email) {
        return status(HttpStatus.OK).body(readingService.getPostsByEmail(email));
    }
}