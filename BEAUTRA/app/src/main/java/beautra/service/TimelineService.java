package beautra.service;

import beautra.model.TimelineItem;
import beautra.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimelineService {
    public List<TimelineItem> getTimelineByUserId(int userId) {
        List<TimelineItem> timelineItems = new ArrayList<>();
        String sql = "SELECT * FROM timeline WHERE user_id = ? ORDER BY timestamp DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TimelineItem item = new TimelineItem(userId, userId, sql, sql, null);
                item.setId(rs.getInt("id"));
                item.setUserId(userId);
                item.setActivityType(rs.getString("activity_type"));
                item.setDescription(rs.getString("description"));
                item.setTimestamp(rs.getTimestamp("timestamp"));
                timelineItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timelineItems;
    }
}
