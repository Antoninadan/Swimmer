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
@Table(name = "events")
public class Event extends BaseEntity {
    @ManyToOne(targetEntity = Franchise.class)
    private Franchise franchise;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "name")
    private String name;

    @Column(name = "data_from")
    private Long dateFrom;

    @Column(name = "data_to")
    private Long dateTo;

    @ManyToOne(targetEntity = Country.class)
    private Country country;

    @Column(name = "venue")
    private String venue;

    @Column(name = "url")
    private String url;

    @Column(name = "comment")
    private String comment;

    public Event(Integer id, Franchise franchise, String organizer, String name, Long dateFrom,
                 Long dateTo, Country country, String venue, String url, String comment,
                 Long createDate, Long modifyDate, RecordStatus recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.franchise = franchise;
        this.organizer = organizer;
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.country = country;
        this.venue = venue;
        this.url = url;
        this.comment = comment;
    }
}
