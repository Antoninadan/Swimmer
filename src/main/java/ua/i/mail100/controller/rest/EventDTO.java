package ua.i.mail100.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO{
    private Integer id;
    private Integer franchiseId;
    private String organizer;
    private String name;
    private Long dateFrom;
    private Long dateTo;
    private Integer countryId;
    private String venue;
    private String url;
    private String comment;
}
