package com.makhlouk.makhloukanassexamjee.dtos;

import com.makhlouk.makhloukanassexamjee.enums.BoiteVitesse;
import com.makhlouk.makhloukanassexamjee.enums.TypeCarburant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VoitureDTO extends VehiculeDTO {
    private Integer nombrePortes;
    private TypeCarburant typeCarburant;
    private BoiteVitesse boiteVitesse;
}
