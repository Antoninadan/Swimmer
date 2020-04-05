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
@Table(name = "countries")
public class Country extends BaseEntity {

    @Column(name = "name")
    private String name;

    public Country(Integer id, String name, Long createDate, Long modifyDate, RecordStatus recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.name = name;
    }
}
