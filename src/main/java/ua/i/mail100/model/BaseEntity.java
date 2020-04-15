package ua.i.mail100.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Comparable<BaseEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "create_date", nullable = false)
    private Long createDate;

    @Column(name = "modify_date", nullable = false)
    private Long modifyDate;

    @Column(name = "record_status", nullable = false)
    private RecordStatus recordStatus;

    @Override
    public int compareTo(BaseEntity baseEntity) {
        return this.id.compareTo(baseEntity.id);
    }
}
