package com.makhlouk.makhloukanassexamjee.dtos;

import lombok.Data;

import java.util.Date;


@Data
public class LouerVehiculeRequest {
    private Long vehiculeId;
    private Date dateDebut;
    private Date dateFin;
    private String nomClient;
    private String emailClient;
}
