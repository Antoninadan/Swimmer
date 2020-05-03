package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.i.mail100.config.FileConfig;
import ua.i.mail100.dto.FranchiseDTO;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.service.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MapperFranchiseUtil {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileService fileService;

    @Autowired
    FileConfig fileConfig;

    public Franchise toObject(FranchiseDTO franchiseDTO) {
        Franchise franchise = new Franchise();
        franchise.setId(franchiseDTO.getId());
        franchise.setName(franchiseDTO.getName());
        franchise.setPath(franchiseDTO.getPath());
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
        franchiseDTO.setPath(franchise.getPath());
//        if(franchise.getPath() != null) {
//        MultipartFile multipartFile = fileService.convertToMultipartFile(
//                fileConfig.FILE_ROOT_PATH,
//                franchise.getPath());
//        franchiseDTO.setFile(multipartFile);}
        franchiseDTO.setCreateDate(franchise.getCreateDate());
        franchiseDTO.setModifyDate(franchise.getModifyDate());
        franchiseDTO.setRecordStatus(franchise.getRecordStatus().toString());
        return franchiseDTO;
    }

    public List<FranchiseDTO> toDTOList(List<Franchise> franchises) {
        Collections.sort(franchises);
        List<FranchiseDTO> franchiseDTOs = new ArrayList<>();
        for (Franchise each : franchises) {
            FranchiseDTO franchiseDTO = toDTO(each);
            franchiseDTOs.add(franchiseDTO);
        }
        return franchiseDTOs;
    }

}
