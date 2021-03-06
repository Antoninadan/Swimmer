package ua.i.mail100.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO extends BaseEntityDTO {
    private Integer franchiseId;
    private String organizer;
    private String name;
    private String dateFrom;
    private String dateTo;
    private Integer countryId;
    private String venue;
    private String url;
    private String comment;
    private List<DistanceDTO> distances;

    public EventDTO(Integer id, Integer franchiseId, String organizer, String name, String dateFrom, String dateTo,
                    Integer countryId, String venue, String url, String comment,
                    Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.franchiseId = franchiseId;
        this.organizer = organizer;
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.countryId = countryId;
        this.venue = venue;
        this.url = url;
        this.comment = comment;
    }
}
