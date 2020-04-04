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
    private Integer eventId;
    private String distanceType;
    private String ageDistanceType;
    private Integer lengthInMeters;
    private Long date;
    private String comment;
}

