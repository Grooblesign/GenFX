package application.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event extends AbstractDateModel {
	private IntegerProperty id;
	private IntegerProperty citationId;
	private StringProperty location;
	private StringProperty details;
	private IntegerProperty personId;
	private StringProperty type;
	
	public Event() {
	}
	
	public Event(int id, String type, int personId, String date, String location, String details) {
		this.id = new SimpleIntegerProperty(id);
		this.type= new SimpleStringProperty(type);
		this.personId = new SimpleIntegerProperty(personId);
		this.date = new SimpleStringProperty(date);
		this.location = new SimpleStringProperty(location);
		this.details = new SimpleStringProperty(details);
	}
	
	public IntegerProperty getId() {
		return id;
	}
	public void setId(IntegerProperty id) {
		this.id = id;
	}
	public StringProperty getLocation() {
		return location;
	}
	public void setLocation(StringProperty location) {
		this.location = location;
	}
	public StringProperty getDetails() {
		return details;
	}
	public void setDetails(StringProperty details) {
		this.details = details;
	}
	public StringProperty getType() {
		return type;
	}
	public void setType(StringProperty type) {
		this.type = type;
	}
	public IntegerProperty getPersonId() {
		return personId;
	}
	public void setPersonId(IntegerProperty personId) {
		this.personId = personId;
	}
	public IntegerProperty getCitationId() {
		return citationId;
	}
	public void setCitationId(IntegerProperty citationId) {
		this.citationId = citationId;
	}
}
