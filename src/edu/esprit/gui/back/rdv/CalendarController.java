package edu.esprit.gui.back.rdv;

import edu.esprit.entities.Rdv;
import edu.esprit.services.RdvService;
import edu.esprit.utils.FullCalendarView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    @FXML
    public Pane calendarPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FullCalendarView fullCalendarView = new FullCalendarView(YearMonth.now());
        List<Rdv> listRdv = RdvService.getInstance().getAll();

        if (!listRdv.isEmpty()) {
            for (Rdv rdv : listRdv) {
                fullCalendarView.highlightDays(rdv.getDebut().toLocalDate(), rdv.getFin().toLocalDate());
            }
        }

        fullCalendarView.populateCalendar(YearMonth.now());
        calendarPane.getChildren().add(fullCalendarView.getView());
    }
}
