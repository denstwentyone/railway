package db.entities;

import java.io.Serializable;

public class Ticket implements Serializable {
    
    private Long trainId;
    private Long userId;
    
    public Ticket(Long trainId, Long userId) {
        this.trainId = trainId;
        this.userId = userId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public Long getUserId() {
        return userId;
    }

}
