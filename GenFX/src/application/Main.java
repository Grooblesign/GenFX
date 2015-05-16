package application;
	
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.models.CensusEntry;
import application.models.Event;
import application.models.Person;
import application.service.PostgresService;
import application.util.DateComparator;
import application.views.PersonListViewController;

public class Main extends Application {
	private ObservableList<CensusEntry> censusData = FXCollections.observableArrayList();
	private ObservableList<Event> eventData = FXCollections.observableArrayList();
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	private Stage _primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		_primaryStage = primaryStage;
		
		try {
			PostgresService dbService = new PostgresService();

			List<Person> persons = dbService.findAllPerson();
			List<Event> events = dbService.findAllEvents();
			List<CensusEntry> censusEntries = dbService.findAllCensusEntries();

			Collections.sort(censusEntries, new DateComparator());
			
			events.stream()
				.filter(e -> e.getType().get().equals("Marriage"))
					.forEach(e -> e.setDetails(new SimpleStringProperty(
						persons.stream()
							.filter(p -> p.getId().get() == Integer.parseInt(e.getDetails().get()))
								.findFirst().get().getFullname())));
			
			persons.stream()
				.filter(p -> p.getFatherId().get() > 0)
					.forEach(p -> events.add(
							new Event(p.getId().get(), "Child", p.getFatherId().get(), "", "", String.format("%s", p.getId().get()))));
			
			persons.stream()
				.filter(p -> p.getMotherId().get() > 0)
					.forEach(p -> events.add(
							new Event(p.getId().get(), "Child", p.getMotherId().get(), "", "", String.format("%s", p.getId().get()))));
			
			for (Event event : events) {
				if (event.getType().get().equals("Child")) {
					Optional<Event> birth = events.stream()
							.filter(e -> e.getPersonId().get() == Integer.parseInt(event.getDetails().get()))
							.filter(e -> e.getType().get().equals("Birth"))
							.findFirst();
					
					if (birth.isPresent()) {
						event.setDate(birth.get().getDate());
						event.setLocation(birth.get().getLocation());
					}

					Optional<Person> child = persons.stream()
							.filter(p -> p.getId().get() == Integer.parseInt(event.getDetails().get()))
							.findFirst();
					
					if (child.isPresent()) {
						event.setDetails(new SimpleStringProperty(child.get().getFullname()));
					}
				}
			}

			Collections.sort(events, new DateComparator());

			for (CensusEntry censusEntry : censusEntries) {
				censusData.add(censusEntry);
			}
			
			for (Event event : events) {
				eventData.add(event);
			}

			for (Person person : persons) {
				personData.add(person);
			}
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("views/RootLayout.fxml"));

	        BorderPane root = loader.load();

	        FXMLLoader loader2 = new FXMLLoader();
	        loader2.setLocation(Main.class.getResource("views/PersonListView.fxml"));
	        AnchorPane personListview = (AnchorPane) loader2.load();			

	        PersonListViewController controller = loader2.getController();
	        controller.setMainApp(this);
	        
	        root.setCenter(personListview);
	        
	        Scene scene = new Scene(root,1200,600);

			primaryStage.setTitle("Geneaology FX");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    
    
    public ObservableList<Event> getEventData() {
        return eventData;
    }	

    public ObservableList<CensusEntry> getCensusData() {
        return censusData;
    }	
    
    public Stage getPrimaryStage() {
    	return _primaryStage;
    }
}
