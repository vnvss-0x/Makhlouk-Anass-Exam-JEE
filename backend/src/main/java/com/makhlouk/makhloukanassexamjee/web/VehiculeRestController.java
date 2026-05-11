package com.makhlouk.makhloukanassexamjee.web;

import com.makhlouk.makhloukanassexamjee.dtos.MotoDTO;
import com.makhlouk.makhloukanassexamjee.dtos.VehiculeDTO;
import com.makhlouk.makhloukanassexamjee.dtos.VoitureDTO;
import com.makhlouk.makhloukanassexamjee.services.RentalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Vehicules", description = "Gestion des véhicules (voitures + motos)")
public class VehiculeRestController {

    private RentalService rentalService;

    @GetMapping
    public List<VehiculeDTO> listVehicules() {
        return rentalService.listVehicules();
    }

    @GetMapping("/{id}")
    public VehiculeDTO getVehicule(@PathVariable Long id) {
        return rentalService.getVehicule(id);
    }

    @GetMapping("/by-agence/{agenceId}")
    public List<VehiculeDTO> byAgence(@PathVariable Long agenceId) {
        return rentalService.getVehiculesByAgence(agenceId);
    }

    @GetMapping("/disponibles")
    public List<VehiculeDTO> disponibles() {
        return rentalService.getVehiculesDisponibles();
    }

    @PostMapping("/voitures")
    public VoitureDTO saveVoiture(@RequestBody VoitureDTO dto) {
        return rentalService.saveVoiture(dto);
    }

    @PostMapping("/motos")
    public MotoDTO saveMoto(@RequestBody MotoDTO dto) {
        return rentalService.saveMoto(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicule(@PathVariable Long id) {
        rentalService.deleteVehicule(id);
    }
}
