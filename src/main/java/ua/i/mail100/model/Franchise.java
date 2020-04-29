package ua.i.mail100.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "franchises")
public class Franchise extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;


    // https://stackoverflow.com/questions/9164103/show-image-from-db-using-spring-mvc

    public Franchise(Integer id, String name, String path,
                     Long createDate, Long modifyDate, RecordStatus recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.name = name;
        this.path = path;
    }
}
