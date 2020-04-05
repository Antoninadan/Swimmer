package ua.i.mail100.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseEntityDTO{
    private String login;
    private String name;
    private String sex;
    private String birthDate;
    private String status;

    public UserDTO(Integer id, String login, String name, String sex, String birthDate, String status,
                   Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.login = login;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.status = status;
    }
}