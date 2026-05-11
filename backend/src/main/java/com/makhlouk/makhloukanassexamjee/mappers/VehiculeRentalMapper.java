package com.makhlouk.makhloukanassexamjee.mappers;

import com.makhlouk.makhloukanassexamjee.dtos.*;
import com.makhlouk.makhloukanassexamjee.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class VehiculeRentalMapper {


    public AgenceDTO fromAgence(Agence agence) {
        AgenceDTO dto = new AgenceDTO();
        BeanUtils.copyProperties(agence, dto);
        return dto;
    }

    public Agence fromAgenceDTO(AgenceDTO dto) {
        Agence a = new Agence();
        BeanUtils.copyProperties(dto, a);
        return a;
    }


    public VoitureDTO fromVoiture(Voiture v) {
        VoitureDTO dto = new VoitureDTO();
        BeanUtils.copyProperties(v, dto);
        if (v.getAgence() != null) dto.setAgenceId(v.getAgence().getId());
        return dto;
    }

    public Voiture fromVoitureDTO(VoitureDTO dto) {
        Voiture v = new Voiture();
        BeanUtils.copyProperties(dto, v);
        return v;
    }


    public MotoDTO fromMoto(Moto m) {
        MotoDTO dto = new MotoDTO();
        BeanUtils.copyProperties(m, dto);
        if (m.getAgence() != null) dto.setAgenceId(m.getAgence().getId());
        return dto;
    }

    public Moto fromMotoDTO(MotoDTO dto) {
        Moto m = new Moto();
        BeanUtils.copyProperties(dto, m);
        return m;
    }


    public VehiculeDTO fromVehicule(Vehicule vehicule) {
        if (vehicule instanceof Voiture v) return fromVoiture(v);
        if (vehicule instanceof Moto m)    return fromMoto(m);
        throw new IllegalArgumentException("Type de vehicule inconnu: " + vehicule.getClass());
    }


    public LocationDTO fromLocation(Location loc) {
        LocationDTO dto = new LocationDTO();
        BeanUtils.copyProperties(loc, dto);
        if (loc.getVehicule() != null) {
            dto.setVehiculeId(loc.getVehicule().getId());
            dto.setVehiculeMarque(loc.getVehicule().getMarque());
            dto.setVehiculeModele(loc.getVehicule().getModele());
        }
        return dto;
    }

    public Location fromLocationDTO(LocationDTO dto) {
        Location loc = new Location();
        BeanUtils.copyProperties(dto, loc);
        return loc;
    }
}
