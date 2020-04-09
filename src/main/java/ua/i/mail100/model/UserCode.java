package ua.i.mail100.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_codes")
public class UserCode extends BaseEntity {
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "code")
    private String code;

    @Column(name = "deadline")
    private Long deadline;

    public UserCode(Integer id, User user, String code, Long deadline,
                    Long createDate, Long modifyDate, RecordStatus recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.user = user;
        this.code = code;
        this.deadline = deadline;
    }
}

