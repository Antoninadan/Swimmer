package ua.i.mail100.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "logo")
    private Byte[] logo;

    // https://stackoverflow.com/questions/9164103/show-image-from-db-using-spring-mvc

    public Franchise(Integer id, String name, Byte[] logo) {
        super(id);
        this.name = name;
        this.logo = logo;
    }
}