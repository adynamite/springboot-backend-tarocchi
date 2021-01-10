package com.arcana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reading {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long readingId;
    

    @Nullable
    @Lob
    private String cartaPassato;
    @Nullable
    @Lob
    private String letturaPassato;
    
    @Nullable
    @Lob
    private String cartaPresente;
    @Nullable
    @Lob
    private String letturaPresente;
    
    @Nullable
    @Lob
    private String cartaFuturo;
    @Nullable
    @Lob
    private String letturaFuturo;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
   
}