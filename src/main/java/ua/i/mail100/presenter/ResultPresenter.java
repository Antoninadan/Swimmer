package ua.i.mail100.presenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.i.mail100.dto.BaseEntityDTO;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultPresenter extends BaseEntityDTO {
    private String event;
    private String distance;
    private String timeInSecond;
    private String comment;

    public ResultPresenter(Integer id, String event, String distance, String timeInSecond, String comment,
                           Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.event = event;
        this.distance = distance;
        this.timeInSecond = timeInSecond;
        this.comment = comment;
    }
}
