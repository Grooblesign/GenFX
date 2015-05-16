package application.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private IntegerProperty id;
	private StringProperty forenames;
	private StringProperty surname;
	private IntegerProperty fatherId;
	private IntegerProperty motherId;

	public Person() {
		id =  new SimpleIntegerProperty(0);
		forenames = new SimpleStringProperty("");;
		surname= new SimpleStringProperty("");
		fatherId =  new SimpleIntegerProperty(0);
		motherId =  new SimpleIntegerProperty(0);
	}
	
	public String getFullname() {
		return (forenames.get() + " " + surname.get()).trim();
	}
	
    public StringProperty getForenames() {
		return forenames;
	}
	public StringProperty getSurname() {
		return surname;
	}

	public void setForenames(StringProperty forenames) {
		this.forenames = forenames;
	}

	public void setSurname(StringProperty surname) {
		this.surname = surname;
	}
	
	public IntegerProperty getId() {
		return id;
	}
	
	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public IntegerProperty getFatherId() {
		return fatherId;
	}

	public void setFatherId(IntegerProperty fatherId) {
		this.fatherId = fatherId;
	}

	public IntegerProperty getMotherId() {
		return motherId;
	}

	public void setMotherId(IntegerProperty motherId) {
		this.motherId = motherId;
	}
}
