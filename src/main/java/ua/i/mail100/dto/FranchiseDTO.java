package ua.i.mail100.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseDTO extends BaseEntityDTO {
    private String name;
    private Byte[] logo;
    private String path;

    public FranchiseDTO(Integer id, String name, Byte[] logo, String path,
                        Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.name = name;
        this.logo = logo;
        this.path = path;
    }
}


