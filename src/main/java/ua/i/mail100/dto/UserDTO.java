package ua.i.mail100.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseEntityDTO {
    private String login;
    private String password;
    private String name;
    private String sex;
    private String birthDate;

    public UserDTO(Integer id, String login, String password, String name, String sex, String birthDate,
                   Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.login = login;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }
}