package ua.i.mail100.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEventDTO extends BaseEntityDTO {
    private Integer eventId;
    private Integer userId;

    public FavoriteEventDTO(Integer id, Integer eventId, Integer userId,
                            Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.eventId = eventId;
        this.userId = userId;
    }
}

