package beautra.model;

import java.util.Date;

public class TimelineItem {
    private int id;
    private int userId;
    private String activityType;
    private String description;
    private Date timestamp;
    public TimelineItem(int id, int userId, String activityType, String description, Date timestamp) {
        this.id = id;
        this.userId = userId;
        this.activityType = activityType;
        this.description = description;
        this.timestamp = timestamp;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getActivityType() {
            return activityType;
        }

        public void setActivityType(String activityType) {
            this.activityType = activityType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }
}
