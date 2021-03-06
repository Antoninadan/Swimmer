package ua.i.mail100.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DistanceDTO extends BaseEntityDTO {
    private Integer eventId;
    private String distanceType;
    private String ageDistanceType;
    private Integer lengthInMeters;
    private String date;
    private String comment;

    public DistanceDTO(Integer id, Integer eventId, String distanceType, String ageDistanceType,
                       Integer lengthInMeters, String date, String comment,
                       Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.eventId = eventId;
        this.distanceType = distanceType;
        this.ageDistanceType = ageDistanceType;
        this.lengthInMeters = lengthInMeters;
        this.date = date;
        this.comment = comment;
    }
}

