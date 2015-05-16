package application.views;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import application.Main;
import application.models.CensusEntry;
import application.models.Event;
import application.models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PersonListViewController {
	@FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> forenamesColumn;
    @FXML
    private TableColumn<Person, String> surnameColumn;

	@FXML
    private TableView<Event> eventTable;
    @FXML
    private TableColumn<Event, String> typeColumn;
    @FXML
    private TableColumn<Event, String> dateColumn;
    @FXML
    private TableColumn<Event, String> locationColumn;
    @FXML
    private TableColumn<Event, String> detailsColumn;
    
	@FXML
    private TableView<CensusEntry> censusTable;
    @FXML
    private TableColumn<CensusEntry, String> censusColumn;
    @FXML
    private TableColumn<CensusEntry, String> addressColumn;
	
    @FXML
    private Button personButton;
    @FXML
    private Button fatherButton;
    @FXML
    private Button motherButton;
    @FXML
    private Button paternalGrandfatherButton;
    @FXML
    private Button paternalGrandmotherButton;
    @FXML
    private Button maternalGrandfatherButton;
    @FXML
    private Button maternalGrandmotherButton;

    // Reference to the main application.
    private Main mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonListViewController() {
    }    
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        forenamesColumn.setCellValueFactory(cellData -> cellData.getValue().getForenames());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().getSurname());
        
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getType());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().getLocation());
        detailsColumn.setCellValueFactory(cellData -> cellData.getValue().getDetails());
        
        censusColumn.setCellValueFactory(cellData -> cellData.getValue().getCensusTitle());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        
        showPerson(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPerson(newValue));      
        
        censusTable.setRowFactory( tv -> {
            TableRow<CensusEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    CensusEntry rowData = row.getItem();
                    showCensusHousehold(rowData);
                }
            });
            return row ;
        });    
    }
    
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
        eventTable.setItems(mainApp.getEventData());
        censusTable.setItems(mainApp.getCensusData());
        
        personTable.getSelectionModel().selectFirst();
    }
    
    private void showPerson(Person person) {
    	ObservableList<Event> events = null;
    	if (mainApp == null) {
    		events = FXCollections.observableArrayList();
    	} else {
        	events = mainApp.getEventData();
    	}
    	
    	ObservableList<CensusEntry> censusEntries = null;
    	if (mainApp == null) {
    		censusEntries = FXCollections.observableArrayList();
    	} else {
    		censusEntries = mainApp.getCensusData();
    	}
    	
    	personButton.setText("");
        fatherButton.setText("");
        motherButton.setText("");
        paternalGrandfatherButton.setText("");
        paternalGrandmotherButton.setText("");
        maternalGrandfatherButton.setText("");
        maternalGrandmotherButton.setText("");
        
        if (person != null) {
            FilteredList<Event> filteredEvents = new FilteredList<>(events, e -> true);
            filteredEvents.setPredicate(e -> e.getPersonId().get() == person.getId().get());
        	eventTable.setItems(filteredEvents);

            FilteredList<CensusEntry> filteredCensusEntries = new FilteredList<>(censusEntries, c -> true);
            filteredCensusEntries.setPredicate(c -> c.getPersonId().get() == person.getId().get());
            
        	censusTable.setItems(filteredCensusEntries);
        	
        	personButton.setText(person.getFullname());

        	ObservableList<Person> persons = null;
        	if (mainApp != null) {
            	persons = mainApp.getPersonData();
        	}
        	
        	Person father = new Person();
        	Person mother = new Person();
        	Person paternalGrandfather = new Person();
        	Person paternalGrandmother = new Person();
        	Person maternalGrandfather = new Person();
        	Person maternalGrandmother = new Person();
        	
        	if (persons != null) {
	        	if (person.getFatherId().get() > 0) {
	        		father = persons.stream().filter(p -> p.getId().get() == person.getFatherId().get()).findFirst().get(); 
	        	}
	        	if (person.getMotherId().get() > 0) {
	        		mother = persons.stream().filter(p -> p.getId().get() == person.getMotherId().get()).findFirst().get(); 
	        	}

	        	if (father != null) {
	        		final Person test = father;
		        	if (father.getFatherId().get() > 0) {
		        		paternalGrandfather = persons.stream().filter(p -> p.getId().get() == test.getFatherId().get()).findFirst().get(); 
		        	}
		        	
		        	if (father.getMotherId().get() > 0) {
		        		paternalGrandmother = persons.stream().filter(p -> p.getId().get() == test.getMotherId().get()).findFirst().get(); 
		        	}
	        	}
	        	
	        	if (mother != null) {
	        		final Person test = mother;
		        	if (mother.getFatherId().get() > 0) {
		        		maternalGrandfather = persons.stream().filter(p -> p.getId().get() == test.getFatherId().get()).findFirst().get(); 
		        	}
		        	
		        	if (mother.getMotherId().get() > 0) {
		        		maternalGrandmother = persons.stream().filter(p -> p.getId().get() == test.getMotherId().get()).findFirst().get(); 
		        	}
	        	}
        	}
        	
            fatherButton.setText(father.getFullname());
            motherButton.setText(mother.getFullname());
            paternalGrandfatherButton.setText(paternalGrandfather.getFullname());
            paternalGrandmotherButton.setText(paternalGrandmother.getFullname());
            maternalGrandfatherButton.setText(maternalGrandfather.getFullname());
            maternalGrandmotherButton.setText(maternalGrandmother.getFullname());
        } else {
            FilteredList<Event> filteredEvents = new FilteredList<>(events, e -> false);
        	eventTable.setItems(filteredEvents);
        }
    }
    
    private void showCensusHousehold(CensusEntry censusEntry) {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/CensusHouseholdDialog.fxml"));
        
        try {
        	AnchorPane page = (AnchorPane) loader.load();    	
        
        	FilteredList<CensusEntry> filteredCensusEntries = new FilteredList<>(mainApp.getCensusData(), c -> true);
            filteredCensusEntries.setPredicate(c -> c.getCensusHouseholdId().get() == censusEntry.getCensusHouseholdId().get());
            
            CensusHouseholdDialogController controller = loader.getController();
            // controller.setDialogStage(dialogStage);
            controller.setCensusEntries(filteredCensusEntries);
            
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Census Household");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(mainApp.getPrimaryStage());
	        
	        Scene scene = new Scene(page);
	        
	        dialogStage.setScene(scene);
            
	        dialogStage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
        
//    	Alert alert = new Alert(AlertType.WARNING);
//        alert.initOwner(mainApp.getPrimaryStage());
//        alert.setTitle("Census Household");
//        alert.setHeaderText("The census household window will be shown");
//        alert.setContentText("More text");
//
//        alert.showAndWait();    	
    }
}
