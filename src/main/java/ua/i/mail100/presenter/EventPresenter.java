package ua.i.mail100.presenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.i.mail100.dto.BaseEntityDTO;
import ua.i.mail100.dto.DistanceDTO;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventPresenter extends BaseEntityDTO {
    private String franchise;
    private String organizer;
    private String name;
    private String dateFrom;
    private String dateTo;
    private String country;
    private String venue;
    private String url;
    private String comment;
//    private List<DistanceDTO> distances;

    public EventPresenter(Integer id, String franchise, String organizer, String name, String dateFrom, String dateTo,
                          String country, String venue, String url, String comment,
                          Long createDate, Long modifyDate, String recordStatus) {
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
