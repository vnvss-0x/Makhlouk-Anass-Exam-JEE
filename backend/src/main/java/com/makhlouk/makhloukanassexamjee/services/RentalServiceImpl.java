package com.makhlouk.makhloukanassexamjee.services;

import com.makhlouk.makhloukanassexamjee.dtos.*;
import com.makhlouk.makhloukanassexamjee.entities.*;
import com.makhlouk.makhloukanassexamjee.enums.StatutLocation;
import com.makhlouk.makhloukanassexamjee.enums.StatutVehicule;
import com.makhlouk.makhloukanassexamjee.mappers.VehiculeRentalMapper;
import com.makhlouk.makhloukanassexamjee.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RentalServiceImpl implements RentalService {

    private AgenceRepository agenceRepository;
    private VehiculeRepository vehiculeRepository;
    private VoitureRepository voitureRepository;
    private MotoRepository motoRepository;
    private LocationRepository locationRepository;
    private VehiculeRentalMapper mapper;


    @Override
    public AgenceDTO saveAgence(AgenceDTO dto) {
        log.info("Saving new agence: {}", dto.getNom());
        Agence saved = agenceRepository.save(mapper.fromAgenceDTO(dto));
        return mapper.fromAgence(saved);
    }

    @Override
    public List<AgenceDTO> listAgences() {
        return agenceRepository.findAll().stream()
                .map(mapper::fromAgence)
                .collect(Collectors.toList());
    }

    @Override
    public AgenceDTO getAgence(Long id){
        Agence a = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence not found: " + id));;
        return mapper.fromAgence(a);
    }

    @Override
    public AgenceDTO updateAgence(Long id, AgenceDTO dto) {
        Agence existing = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence not found: " + id));
        existing.setNom(dto.getNom());
        existing.setAdresse(dto.getAdresse());
        existing.setVille(dto.getVille());
        existing.setTelephone(dto.getTelephone());
        return mapper.fromAgence(agenceRepository.save(existing));
    }

    @Override
    public void deleteAgence(Long id) {
        agenceRepository.deleteById(id);
    }

    @Override
    public VoitureDTO saveVoiture(VoitureDTO dto) {
        Voiture v = mapper.fromVoitureDTO(dto);
        if (dto.getAgenceId() != null) {
            Agence agence = agenceRepository.findById(dto.getAgenceId())
                    .orElseThrow(() -> new RuntimeException("Agence not found"));
            v.setAgence(agence);
        }
        if (v.getStatut() == null) v.setStatut(StatutVehicule.DISPONIBLE);
        return mapper.fromVoiture(voitureRepository.save(v));
    }

    @Override
    public MotoDTO saveMoto(MotoDTO dto) {
        Moto m = mapper.fromMotoDTO(dto);
        if (dto.getAgenceId() != null) {
            Agence agence = agenceRepository.findById(dto.getAgenceId())
                    .orElseThrow(() -> new RuntimeException("Agence not found"));
            m.setAgence(agence);
        }
        if (m.getStatut() == null) m.setStatut(StatutVehicule.DISPONIBLE);
        return mapper.fromMoto(motoRepository.save(m));
    }

    @Override
    public List<VehiculeDTO> listVehicules() {
        return vehiculeRepository.findAll().stream()
                .map(mapper::fromVehicule)
                .collect(Collectors.toList());
    }

    @Override
    public VehiculeDTO getVehicule(Long id) {
        Vehicule v = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicule not found: " + id));
        return mapper.fromVehicule(v);
    }

    @Override
    public List<VehiculeDTO> getVehiculesByAgence(Long agenceId) {
        return vehiculeRepository.findByAgenceId(agenceId).stream()
                .map(mapper::fromVehicule)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculeDTO> getVehiculesDisponibles() {
        return vehiculeRepository.findByStatut(StatutVehicule.DISPONIBLE).stream()
                .map(mapper::fromVehicule)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }



    @Override
    public LocationDTO louerVehicule(LouerVehiculeRequest req) {

        Vehicule vehicule = vehiculeRepository.findById(req.getVehiculeId())
                .orElseThrow(() -> new RuntimeException("Vehicule not found"));

        if (vehicule.getStatut() != StatutVehicule.DISPONIBLE) {
            throw new RuntimeException(
                    "Vehicule non disponible. Statut actuel: " + vehicule.getStatut());
        }

        long diffMs = req.getDateFin().getTime() - req.getDateDebut().getTime();
        long days = Math.max(1, TimeUnit.MILLISECONDS.toDays(diffMs));
        double montantTotal = days * vehicule.getPrixParJour();

        Location loc = new Location();
        loc.setDateDebut(req.getDateDebut());
        loc.setDateFin(req.getDateFin());
        loc.setNomClient(req.getNomClient());
        loc.setEmailClient(req.getEmailClient());
        loc.setVehicule(vehicule);
        loc.setMontantTotal(montantTotal);
        loc.setStatut(StatutLocation.EN_COURS);

        vehicule.setStatut(StatutVehicule.LOUE);
        vehiculeRepository.save(vehicule);

        Location saved = locationRepository.save(loc);
        log.info("Vehicule {} louer par {} pour {} jours", vehicule.getId(), req.getNomClient(), days);
        return mapper.fromLocation(saved);
    }

    @Override
    public LocationDTO terminerLocation(Long locationId)  {
        Location loc = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        loc.setStatut(StatutLocation.TERMINEE);
        // Free the vehicle
        if (loc.getVehicule() != null) {
            loc.getVehicule().setStatut(StatutVehicule.DISPONIBLE);
            vehiculeRepository.save(loc.getVehicule());
        }
        return mapper.fromLocation(locationRepository.save(loc));
    }

    @Override
    public LocationDTO annulerLocation(Long locationId) {
        Location loc = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        loc.setStatut(StatutLocation.ANNULEE);
        if (loc.getVehicule() != null) {
            loc.getVehicule().setStatut(StatutVehicule.DISPONIBLE);
            vehiculeRepository.save(loc.getVehicule());
        }
        return mapper.fromLocation(locationRepository.save(loc));
    }

    @Override
    public List<LocationDTO> historiqueByVehicule(Long vehiculeId) {
        return locationRepository.findByVehiculeId(vehiculeId).stream()
                .map(mapper::fromLocation)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDTO> historiqueByClient(String email) {
        return locationRepository.findByEmailClient(email).stream()
                .map(mapper::fromLocation)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDTO> listLocations() {
        return locationRepository.findAll().stream()
                .map(mapper::fromLocation)
                .collect(Collectors.toList());
    }
}
