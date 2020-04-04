package ua.i.mail100.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private Integer id;
    private Integer distanceId;
    private Integer userId;
    private Integer timeInSeconds;
    private Long lastEditDate;
    private String comment;
}


