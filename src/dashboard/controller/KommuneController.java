package dashboard.controller;

import dashboard.model.KeyIndicator;
import dashboard.model.Kommune;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.util.HashMap;

public class KommuneController {

    @FXML
    private  GridPane kpiGridPane;

    @FXML
    private ListView<Kommune> kommunerListView;

    @FXML
    Label selectionLabel;

    private KeyIndicator keyIndicator;
    private ObservableList<Kommune> kommuner;

    public KommuneController() {
        setKommuneData();
    }

    private void setKommuneData() {
        kommuner = FXCollections.observableArrayList(new Kommune("Høje Taastrup", 129),
                new Kommune("Albertslund", 66),
                new Kommune("Hvidøvre", 125),
                new Kommune("Ishøj", 53),
                new Kommune("Greve", 90),
                new Kommune("Rødovre", 63),
                new Kommune("Brøndby", 53)
        );
    }

    public void initialize() {
        String[] kpiKeys = {"PCR-prøver", "Antigenprøver", "Førstegangstestede (PCR)",
                "Bekræftede tilfælde", "Dødsfald", "Nye indlæggelser", "Overstået infektion"};
        Integer[] kpiValues = {155057, 328445, 5944, 563, 3, 24, 763};

        setKeyIndicator(kpiKeys, kpiValues);
        setKpiGridPaneData(keyIndicator, kpiKeys);

        setKommunerListView();

    }

    private void setKommunerListView() {
        kommunerListView.setItems(kommuner);

        kommunerListView.setCellFactory(new Callback<ListView<Kommune>, ListCell<Kommune>>() {
            @Override
            public ListCell<Kommune> call(ListView<Kommune> param) {
                ListCell<Kommune> cell = new ListCell<Kommune>() {
                    @Override
                    public void updateItem(Kommune kommune, boolean empty) {
                        super.updateItem(kommune, empty);
                        if (kommune != null) {
                            setText(kommune.getConfirmedCases() + " " + kommune.getName());
                        }
                    }
                }; // ListCell
                return cell;
            }
        });


        kommunerListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Kommune>() {
                    public void changed(ObservableValue<? extends Kommune> ov,
                                        Kommune oldValue, Kommune newValue) {
                        selectionLabel.setText(newValue.getName() + ": " +
                                newValue.getConfirmedCases());
                    }
                });

    }

    private void setKeyIndicator(String[] keys, Integer[] values) {
        HashMap<String, Integer> kpiMap = new HashMap<>();
        for(int i = 0; i < keys.length; i++) {
            kpiMap.put(keys[i], values[i]);
        }
        keyIndicator = new KeyIndicator(kpiMap);
    }

    private void setKpiGridPaneData(KeyIndicator keyIndicator, String[] order) {
        for(int col = 0; col < order.length; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100/order.length);
            columnConstraints.setHalignment(HPos.CENTER);
            kpiGridPane.getColumnConstraints().add(columnConstraints);
            kpiGridPane.add(new Label(order[col]), col, 0);
            kpiGridPane.add(new Label(keyIndicator.get(order[col]).toString()), col, 1);

        }
    }
}
