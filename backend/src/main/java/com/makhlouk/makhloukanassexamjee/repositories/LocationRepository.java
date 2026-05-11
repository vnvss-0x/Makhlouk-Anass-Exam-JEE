package com.makhlouk.makhloukanassexamjee.repositories;

import com.makhlouk.makhloukanassexamjee.entities.Location;
import com.makhlouk.makhloukanassexamjee.enums.StatutLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByVehiculeId(Long vehiculeId);
    List<Location> findByStatut(StatutLocation statut);
    List<Location> findByEmailClient(String email);
}
