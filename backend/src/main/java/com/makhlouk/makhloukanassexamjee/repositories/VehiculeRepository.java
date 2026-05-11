package com.makhlouk.makhloukanassexamjee.repositories;

import com.makhlouk.makhloukanassexamjee.entities.Vehicule;
import com.makhlouk.makhloukanassexamjee.enums.StatutVehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByMarqueContainsIgnoreCase(String keyword);
    List<Vehicule> findByStatut(StatutVehicule statut);
    List<Vehicule> findByAgenceId(Long agenceId);
}
