package application.models;

import javafx.beans.property.StringProperty;

public class AbstractDateModel {
	protected StringProperty date;

	public StringProperty getDate() {
		return date;
	}
	public void setDate(StringProperty date) {
		this.date = date;
	}
}
