package com.arcana.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadingResponse {
	private Long readingId;
    private String letturaPassato, letturaPresente,letturaFuturo;
    private String cartaPassato, cartaPresente, cartaFuturo;
    private String email;
    private Date date;
   
}