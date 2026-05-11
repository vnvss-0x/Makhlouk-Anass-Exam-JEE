package com.makhlouk.makhloukanassexamjee.entities;

import com.makhlouk.makhloukanassexamjee.enums.TypeMoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("MOTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moto extends Vehicule {

    private Integer cylindree;

    @Enumerated(EnumType.STRING)
    private TypeMoto typeMoto;

    private Boolean casqueInclus;
}
