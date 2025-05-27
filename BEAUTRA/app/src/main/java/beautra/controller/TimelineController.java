package beautra.controller;

import beautra.model.TimelineItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TimelineController {
    @FXML
    private ListView<TimelineItem> timelineListView;

    public void initialize() {
        // Load data timeline dari TimelineService ke listView
    }
}
