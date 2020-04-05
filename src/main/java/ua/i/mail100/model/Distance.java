package ua.i.mail100.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "distances")
public class Distance extends BaseEntity {
    @ManyToOne(targetEntity = Event.class)
    private Event event;

    @Column(name = "distance_type")
    private DistanceType distanceType;

    @Column(name = "age_distance_type")
    private AgeDistanceType ageDistanceType;

    @Column(name = "length_in_meters")
    private Integer lengthInMeters;

    @Column(name = "date")
    private Long date;

    @Column(name = "comment")
    private String comment;

    public Distance(Integer id, Event event, DistanceType distanceType, AgeDistanceType ageDistanceType,
                    Integer lengthInMeters, Long date, String comment,
                    Long createDate, Long modifyDate, RecordStatus recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.event = event;
        this.distanceType = distanceType;
        this.ageDistanceType = ageDistanceType;
        this.lengthInMeters = lengthInMeters;
        this.date = date;
        this.comment = comment;
    }
}

