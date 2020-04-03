package ua.i.mail100.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.i.mail100.model.AgeDistanceType;
import ua.i.mail100.model.DistanceType;
import ua.i.mail100.model.Event;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DistanceDTO {
    private Integer id;
    private Integer eventId;
    private String distanceType;
    private String ageDistanceType;
    private Long date;
    private String comment;
}

