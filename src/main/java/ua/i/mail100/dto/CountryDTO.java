package ua.i.mail100.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.i.mail100.model.BaseEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO extends BaseEntityDTO {
    private String name;

    public CountryDTO(Integer id, String name,
                        Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.name = name;
    }
}
