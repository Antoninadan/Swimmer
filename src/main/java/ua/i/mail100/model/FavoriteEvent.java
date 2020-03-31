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
@Table(name = "favorite_events")
public class FavoriteEvent extends BaseEntity{

    @ManyToOne(targetEntity = Event.class)
    private Event event;

    @ManyToOne(targetEntity = User.class)
    private User user;

    public FavoriteEvent(Integer id, Event event, User user) {
        super(id);
        this.event = event;
        this.user = user;
    }
}

