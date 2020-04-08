package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.FranchiseDTO;
import ua.i.mail100.model.Franchise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperFranchiseUtil {
    @Autowired
    ObjectMapper objectMapper;

    public Franchise toObject(FranchiseDTO franchiseDTO) {
        Franchise franchise = new Franchise();
        franchise.setId(franchiseDTO.getId());
        franchise.setName(franchiseDTO.getName());
        franchise.setLogo(franchiseDTO.getLogo());
        return franchise;
    }

    public FranchiseDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, FranchiseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FranchiseDTO toDTO(Franchise franchise) {
        FranchiseDTO franchiseDTO = new FranchiseDTO();
        franchiseDTO.setId(franchise.getId());
        franchiseDTO.setName(franchise.getName());
        franchiseDTO.setLogo(franchise.getLogo());
        franchiseDTO.setCreateDate(franchise.getCreateDate());
        franchiseDTO.setModifyDate(franchise.getModifyDate());
        franchiseDTO.setRecordStatus(franchise.getRecordStatus().toString());
        return franchiseDTO;
    }

    public List<FranchiseDTO> toDTOList(List<Franchise> franchises) {
        List<FranchiseDTO> franchiseDTOs = new ArrayList<>();
        for (Franchise each : franchises) {
            FranchiseDTO franchiseDTO = toDTO(each);
            franchiseDTOs.add(franchiseDTO);
        }
        return franchiseDTOs;
    }

}
