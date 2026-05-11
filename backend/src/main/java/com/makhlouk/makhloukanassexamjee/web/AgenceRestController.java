package com.makhlouk.makhloukanassexamjee.web;

import com.makhlouk.makhloukanassexamjee.dtos.AgenceDTO;
import com.makhlouk.makhloukanassexamjee.services.RentalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Agences", description = "Gestion des agences de location")
public class AgenceRestController {

    private RentalService rentalService;

    @GetMapping
    public List<AgenceDTO> listAgences() {
        return rentalService.listAgences();
    }

    @GetMapping("/{id}")
    public AgenceDTO getAgence(@PathVariable Long id) {
        return rentalService.getAgence(id);
    }

    @PostMapping
    public AgenceDTO saveAgence(@RequestBody AgenceDTO dto) {
        return rentalService.saveAgence(dto);
    }

    @PutMapping("/{id}")
    public AgenceDTO updateAgence(@PathVariable Long id, @RequestBody AgenceDTO dto)
           {
        return rentalService.updateAgence(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAgence(@PathVariable Long id) {
        rentalService.deleteAgence(id);
    }
}
