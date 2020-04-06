package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.BaseEntityDTO;
import ua.i.mail100.model.BaseEntity;
import ua.i.mail100.model.RecordStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperBaseEntityUtil {

    @Autowired
    ObjectMapper objectMapper;

    public BaseEntity toObject(BaseEntityDTO baseEntityDTO) {
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(baseEntityDTO.getId());
        baseEntity.setCreateDate(baseEntityDTO.getCreateDate());
        baseEntity.setModifyDate(baseEntityDTO.getModifyDate());
        baseEntity.setRecordStatus(RecordStatus.valueOf(baseEntityDTO.getRecordStatus()));
        return baseEntity;
    }

    public BaseEntityDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, BaseEntityDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BaseEntityDTO toDTO(BaseEntity baseEntity) {
        BaseEntityDTO baseEntityDTO = new BaseEntityDTO();
        baseEntityDTO.setId(baseEntity.getId());
        baseEntityDTO.setCreateDate(baseEntity.getCreateDate());
        baseEntityDTO.setModifyDate(baseEntity.getModifyDate());
        baseEntityDTO.setRecordStatus(baseEntity.getRecordStatus().toString());
        return baseEntityDTO;
    }

    public List<BaseEntityDTO> toDTOList(List<BaseEntity> baseEntitys) {
        List<BaseEntityDTO> baseEntityDTOS = new ArrayList<>();
        for (BaseEntity each : baseEntitys) {
            BaseEntityDTO baseEntityDTO = toDTO(each);
            baseEntityDTOS.add(baseEntityDTO);
        }
        return baseEntityDTOS;
    }
}
