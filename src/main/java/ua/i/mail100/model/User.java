package ua.i.mail100.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private Sex sex;

    @Column(name = "birth_date")
    private Long birthDate;

    @Column(name = "status")
    private UserStatus userStatus;

    public User(Integer id, String login, String password, String name, Sex sex,
                Long birthDate, UserStatus userStatus,
                Long createDate, Long modifyDate, RecordStatus recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.login = login;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.userStatus = userStatus;
    }
}
