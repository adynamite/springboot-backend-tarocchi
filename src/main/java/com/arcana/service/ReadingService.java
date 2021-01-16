package com.arcana.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcana.dto.ReadingRequest;
import com.arcana.mapper.ReadingMapper;
import com.arcana.dto.ReadingResponse;
import com.arcana.exception.ReadingNotFoundException;
import com.arcana.model.Reading;
import com.arcana.model.User;
import com.arcana.repository.ReadingRepository;
import com.arcana.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ReadingService {

    private final ReadingRepository readingRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final ReadingMapper readingMapper;
  
    public void saveReading( ReadingRequest readingRequest) {
    	Reading reading =new Reading();
    	
    	reading.setLetturaPassato(readingRequest.getLetturaPassato());
    	reading.setCartaPassato(readingRequest.getCartaPassato());
    	reading.setLetturaPresente(readingRequest.getLetturaPresente());
    	reading.setCartaPresente(readingRequest.getCartaPresente());
    	reading.setLetturaFuturo(readingRequest.getLetturaFuturo());
    	reading.setCartaFuturo(readingRequest.getCartaFuturo());
    	 reading.setCreatedDate(Instant.now());
    	
    	
    	String email=readingRequest.getEmail();
    	
    	Optional<User> optional=userRepository.findByEmail(email);
    	User user=optional 
    			.orElseThrow(()->new UsernameNotFoundException("User email not found - " + email));
   reading.setUser(user);
  
   
   
   readingRepository.save(reading);
    
    }
    

    @Transactional(readOnly = true)
    public List<ReadingResponse> getAllReadings() {
        return readingRepository.findAll()
                .stream()
                .map(readingMapper::mapToDto)
                .collect(toList());
    }
    
    @Transactional(readOnly = true)
    public List<ReadingResponse> getReadingsByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return readingRepository.findByUser(user)
                .stream()
                .map(readingMapper::mapToDto)
                .collect(toList());
    }

    
 

}