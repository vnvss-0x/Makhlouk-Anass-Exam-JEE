package com.makhlouk.makhloukanassexamjee.entities;

import com.makhlouk.makhloukanassexamjee.enums.BoiteVitesse;
import com.makhlouk.makhloukanassexamjee.enums.TypeCarburant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("VOITURE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voiture extends Vehicule {

    private Integer nombrePortes;

    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;

    @Enumerated(EnumType.STRING)
    private BoiteVitesse boiteVitesse;
}
