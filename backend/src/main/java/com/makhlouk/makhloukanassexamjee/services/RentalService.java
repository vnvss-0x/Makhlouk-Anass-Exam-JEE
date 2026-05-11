package com.makhlouk.makhloukanassexamjee.services;

import com.makhlouk.makhloukanassexamjee.dtos.*;

import java.util.List;


public interface RentalService {

    AgenceDTO saveAgence(AgenceDTO agenceDTO);
    List<AgenceDTO> listAgences();
    AgenceDTO getAgence(Long id);
    AgenceDTO updateAgence(Long id, AgenceDTO dto);
    void deleteAgence(Long id);

    VoitureDTO saveVoiture(VoitureDTO dto);
    MotoDTO    saveMoto(MotoDTO dto);
    List<VehiculeDTO> listVehicules();
    VehiculeDTO getVehicule(Long id);
    List<VehiculeDTO> getVehiculesByAgence(Long agenceId);
    List<VehiculeDTO> getVehiculesDisponibles();
    void deleteVehicule(Long id);

    LocationDTO louerVehicule(LouerVehiculeRequest request);
    LocationDTO terminerLocation(Long locationId);
    LocationDTO annulerLocation(Long locationId) ;
    List<LocationDTO> historiqueByVehicule(Long vehiculeId);
    List<LocationDTO> historiqueByClient(String email);
    List<LocationDTO> listLocations();
}
