package ua.i.mail100.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO extends BaseEntityDTO {
    private Integer distanceId;
    private Integer userId;
    private Integer timeInSeconds;
    private String comment;

    public ResultDTO(Integer id, Integer distanceId, Integer userId, Integer timeInSeconds, String comment,
                     Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.distanceId = distanceId;
        this.userId = userId;
        this.timeInSeconds = timeInSeconds;
        this.comment = comment;
    }
}


