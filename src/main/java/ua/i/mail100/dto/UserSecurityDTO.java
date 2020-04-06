package ua.i.mail100.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityDTO extends BaseEntityDTO {
    private String login;
    private String name;
    private String sex;
    private Long birthDate;

    public UserSecurityDTO(Integer id, String login, String name, String sex, Long birthDate,
                           Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.login = login;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }
}