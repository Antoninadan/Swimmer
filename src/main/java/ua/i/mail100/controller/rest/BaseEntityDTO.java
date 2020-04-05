package ua.i.mail100.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityDTO {
    private Integer id;
    private Long createDate;
    private Long modifyDate;
    private String recordStatus;
}
