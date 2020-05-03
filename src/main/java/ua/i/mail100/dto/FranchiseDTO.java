package ua.i.mail100.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseDTO extends BaseEntityDTO {
    private String name;
    private String path;
//    private MultipartFile file;

    public FranchiseDTO(Integer id, String name, String path, /*MultipartFile file,*/
                        Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.name = name;
        this.path = path;
//        this.file = file;
    }
}


