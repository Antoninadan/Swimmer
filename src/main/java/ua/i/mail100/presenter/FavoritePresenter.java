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
public class FavoritePresenter extends BaseEntityDTO {
    private String event;
    private String user;

    public FavoritePresenter(Integer id, String event, String user,
                             Long createDate, Long modifyDate, String recordStatus) {
        super(id, createDate, modifyDate, recordStatus);
        this.event = event;
        this.user = user;
    }
}
