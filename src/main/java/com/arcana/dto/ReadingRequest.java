package com.arcana.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadingRequest {
	private Long readingId;
    private String letturaPassato, letturaPresente,letturaFuturo;
    private String cartaPassato, cartaPresente, cartaFuturo;
    private String email;
}
