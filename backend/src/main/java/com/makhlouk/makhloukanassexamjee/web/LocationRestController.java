package com.makhlouk.makhloukanassexamjee.web;

import com.makhlouk.makhloukanassexamjee.dtos.LocationDTO;
import com.makhlouk.makhloukanassexamjee.dtos.LouerVehiculeRequest;
import com.makhlouk.makhloukanassexamjee.services.RentalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Locations", description = "Gestion des locations de véhicules")
public class LocationRestController {

    private RentalService rentalService;

    @PostMapping("/louer")
    public LocationDTO louer(@RequestBody LouerVehiculeRequest req)
    {
        return rentalService.louerVehicule(req);
    }

    @PostMapping("/{id}/terminer")
    public LocationDTO terminer(@PathVariable Long id)  {
        return rentalService.terminerLocation(id);
    }

    @PostMapping("/{id}/annuler")
    public LocationDTO annuler(@PathVariable Long id) {
        return rentalService.annulerLocation(id);
    }

    @GetMapping
    public List<LocationDTO> listAll() {
        return rentalService.listLocations();
    }

    @GetMapping("/by-vehicule/{vehiculeId}")
    public List<LocationDTO> byVehicule(@PathVariable Long vehiculeId) {
        return rentalService.historiqueByVehicule(vehiculeId);
    }

    @GetMapping("/by-client")
    public List<LocationDTO> byClient(@RequestParam String email) {
        return rentalService.historiqueByClient(email);
    }
}
