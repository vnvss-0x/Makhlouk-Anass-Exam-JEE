package com.makhlouk.makhloukanassexamjee.repositories;

import com.makhlouk.makhloukanassexamjee.entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
    List<Agence> findByNomContainsIgnoreCase(String keyword);
    List<Agence> findByVille(String ville);
}
