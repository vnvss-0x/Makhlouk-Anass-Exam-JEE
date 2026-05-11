package com.makhlouk.makhloukanassexamjee.dtos;

import com.makhlouk.makhloukanassexamjee.enums.StatutLocation;
import lombok.Data;

import java.util.Date;

@Data
public class LocationDTO {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private Double montantTotal;
    private StatutLocation statut;
    private String nomClient;
    private String emailClient;
    private Long vehiculeId;
    private String vehiculeMarque;
    private String vehiculeModele;
}
