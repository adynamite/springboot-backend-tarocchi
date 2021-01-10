package com.arcana.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arcana.dto.ReadingRequest;
import com.arcana.dto.ReadingResponse;
import com.arcana.model.Reading;
import com.arcana.model.User;

@Mapper(componentModel = "spring")
public interface ReadingMapper {
	 @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	 @Mapping(target = "letturaPassato", source = "readingRequest.letturaPassato")
	 @Mapping(target = "letturaPresente", source = "readingRequest.letturaPresente")
	 @Mapping(target = "letturaFuturo", source = "readingRequest.letturaFuturo")
	 @Mapping(target = "cartaPassato", source = "readingRequest.cartaPassato")
	 @Mapping(target = "cartaPresente", source = "readingRequest.cartaPresente")
	 @Mapping(target = "cartaFuturo", source = "readingRequest.cartaFuturo")
	Reading map (ReadingRequest readingRequest, User user);
	 
	 @Mapping(target = "readingId", source = "readingId")
	 @Mapping(target = "letturaPassato", source = "letturaPassato")
	 @Mapping(target = "letturaPresente", source = "letturaPresente")
	 @Mapping(target = "letturaFuturo", source = "letturaFuturo")
	 @Mapping(target = "cartaPassato", source = "cartaPassato")
	 @Mapping(target = "cartaPresente", source = "cartaPresente")
	 @Mapping(target = "cartaFuturo", source = "cartaFuturo")
	 @Mapping(target="email", source="user.email")
	 @Mapping(target="date", source="createdDate")
	 ReadingResponse mapToDto(Reading read);
	 
	 

}
