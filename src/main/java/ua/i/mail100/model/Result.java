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
@Table(name = "results")
public class Result extends BaseEntity{

    @ManyToOne(targetEntity = Distance.class)
    private Distance distance;


@ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "time_in_seconds")
    private Integer timeInSeconds;


    @Column(name = "last_edit_date")
    private Long lastEditDate;

    @Column(name = "comment")
    private String comment;

    public Result(Integer id, Distance distance, User user, Integer timeInSeconds, Long lastEditDate, String comment) {
        super(id);
        this.distance = distance;
        this.user = user;
        this.timeInSeconds = timeInSeconds;
        this.lastEditDate = lastEditDate;
        this.comment = comment;
    }
}

