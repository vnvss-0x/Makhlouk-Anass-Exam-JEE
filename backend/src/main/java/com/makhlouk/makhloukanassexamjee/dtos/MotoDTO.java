package com.makhlouk.makhloukanassexamjee.dtos;

import com.makhlouk.makhloukanassexamjee.enums.TypeMoto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MotoDTO extends VehiculeDTO {
    private Integer cylindree;
    private TypeMoto typeMoto;
    private Boolean casqueInclus;
}
