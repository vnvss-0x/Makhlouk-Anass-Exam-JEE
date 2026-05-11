package com.makhlouk.makhloukanassexamjee.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.makhlouk.makhloukanassexamjee.enums.StatutVehicule;
import lombok.Data;

import java.util.Date;


@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = VoitureDTO.class, name = "VOITURE"),
        @JsonSubTypes.Type(value = MotoDTO.class,    name = "MOTO")
})
public abstract class VehiculeDTO {
    private Long id;
    private String marque;
    private String modele;
    private String matricule;
    private Double prixParJour;
    private Date dateMiseEnService;
    private StatutVehicule statut;
    private Long agenceId;
}
