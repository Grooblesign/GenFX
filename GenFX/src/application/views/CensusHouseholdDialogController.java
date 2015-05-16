package application.views;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import application.models.CensusEntry;

public class CensusHouseholdDialogController {

	@FXML
    private TableView<CensusEntry> censusHouseholdTable;
    @FXML
    private TableColumn<CensusEntry, String> nameColumn;
    @FXML
    private TableColumn<CensusEntry, String> relationshipToHeadColumn;
    @FXML
    private TableColumn<CensusEntry, String> conditionColumn;
    @FXML
    private TableColumn<CensusEntry, String> ageColumn;
    @FXML
    private TableColumn<CensusEntry, String> occupationColumn;
    @FXML
    private TableColumn<CensusEntry, String> birthplaceColumn;

    private FilteredList<CensusEntry> censusEntries;

    public void setCensusEntries(FilteredList<CensusEntry> censusEntries) {
		this.censusEntries = censusEntries;
        censusHouseholdTable.setItems(censusEntries);
        censusHouseholdTable.getSelectionModel().selectFirst();
	}

	@FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        relationshipToHeadColumn.setCellValueFactory(cellData -> cellData.getValue().getRelationshipToHead());
        conditionColumn.setCellValueFactory(cellData -> cellData.getValue().getCondition());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().getAge());
        occupationColumn.setCellValueFactory(cellData -> cellData.getValue().getOccupation());
        birthplaceColumn.setCellValueFactory(cellData -> cellData.getValue().getBirthplace());

        censusHouseholdTable.setRowFactory( tv -> {
            TableRow<CensusEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    CensusEntry rowData = row.getItem();
                    // showCensusHousehold(rowData);
                }
            });
            return row ;
        });    
    }
}
