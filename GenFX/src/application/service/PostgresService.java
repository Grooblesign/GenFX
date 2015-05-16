package application.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import application.models.CensusEntry;
import application.models.Event;
import application.models.Person;
import application.util.PostgresConnectionFactory;

public class PostgresService {

	public List<Event> findAllEvents() throws SQLException {

		List<Event> events = new ArrayList<Event>();

		Connection connection = null;

		connection = PostgresConnectionFactory.getConnection();

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM event ORDER BY id");
		while (rs.next()) {
			Event event = new Event();

			event.setCitationId(new SimpleIntegerProperty(rs.getInt("citationid")));
			event.setDate(new SimpleStringProperty(rs.getString("date").trim()));
			event.setDetails(new SimpleStringProperty(rs.getString("details").trim()));
			event.setId(new SimpleIntegerProperty(rs.getInt("id")));
			event.setLocation(new SimpleStringProperty(rs.getString("location").trim()));
			event.setPersonId(new SimpleIntegerProperty(rs.getInt("personid")));
			event.setType(new SimpleStringProperty(rs.getString("type").trim()));

			events.add(event);
		}
		rs.close();
		st.close();

		st = connection.createStatement();
		rs = st.executeQuery("SELECT * FROM marriage");
		while (rs.next()) {
			Event event = new Event();
			
			// event.setCitationId(new SimpleIntegerProperty(rs.getInt("citationid")));
			event.setDate(new SimpleStringProperty(rs.getString("date").trim()));
			event.setDetails(new SimpleStringProperty(String.format("%s", rs.getInt("wifeid"))));
			event.setId(new SimpleIntegerProperty(rs.getInt("id")));
			event.setLocation(new SimpleStringProperty(rs.getString("location").trim()));
			event.setPersonId(new SimpleIntegerProperty(rs.getInt("husbandid")));
			event.setType(new SimpleStringProperty("Marriage"));

			events.add(event);

			event = new Event();

			// event.setCitationId(new SimpleIntegerProperty(rs.getInt("citationid")));
			event.setDate(new SimpleStringProperty(rs.getString("date").trim()));
			event.setDetails(new SimpleStringProperty(String.format("%s", rs.getInt("husbandid"))));
			event.setId(new SimpleIntegerProperty(rs.getInt("id")));
			event.setLocation(new SimpleStringProperty(rs.getString("location").trim()));
			event.setPersonId(new SimpleIntegerProperty(rs.getInt("wifeid")));
			event.setType(new SimpleStringProperty("Marriage"));
			
			events.add(event);
		}
		rs.close();
		st.close();
			
		PostgresConnectionFactory.closeConnection(connection);

		return events;
	}

	public List<CensusEntry> findAllCensusEntries() throws SQLException {
		List<CensusEntry> censusEntries = new ArrayList<CensusEntry>();

		Connection connection = PostgresConnectionFactory.getConnection();

		Statement st = connection.createStatement();
		ResultSet rs = st
				.executeQuery("SELECT * FROM censushouseholdperson as chp, censushousehold, census WHERE censusid=census.id AND censushouseholdid=censushousehold.id");

		while (rs.next()) {
			CensusEntry censusEntry = new CensusEntry();

			censusEntry.setAddress(new SimpleStringProperty(rs.getString("address")));
			censusEntry.setAge(new SimpleStringProperty(rs.getString("age")));
			censusEntry.setBirthplace(new SimpleStringProperty(rs.getString("birthplace")));
			censusEntry.setCensusHouseholdId(new SimpleIntegerProperty(rs.getInt("censushouseholdid")));
			censusEntry.setCondition(new SimpleStringProperty(rs.getString("status")));
			censusEntry.setDate(new SimpleStringProperty(rs.getString("date")));
			censusEntry.setCensusTitle(new SimpleStringProperty(rs.getString("title")));
			censusEntry.setName(new SimpleStringProperty(rs.getString("name")));
			censusEntry.setOccupation(new SimpleStringProperty(rs.getString("occupation")));
			censusEntry.setPersonId(new SimpleIntegerProperty(rs.getInt("personid")));
			censusEntry.setRelationshipToHead(new SimpleStringProperty(rs.getString("relationshiptohead")));
			
			censusEntries.add(censusEntry); 
		}
		rs.close();
		st.close();

		PostgresConnectionFactory.closeConnection(connection);
		
		return censusEntries;
	}

	public List<Person> findAllPerson() throws SQLException {

		List<Person> persons = new ArrayList<Person>();

		Connection connection = null;

		connection = PostgresConnectionFactory.getConnection();

		Statement st = connection.createStatement();

		ResultSet rs = st
				.executeQuery("SELECT forenames, id, surname, fatherid, motherid FROM person ORDER BY surname, forenames");

		while (rs.next()) {
			Person person = new Person();

			person.setFatherId(new SimpleIntegerProperty(rs.getInt("fatherid")));
			person.setId(new SimpleIntegerProperty(rs.getInt("id")));
			person.setMotherId(new SimpleIntegerProperty(rs.getInt("motherid")));
			person.setSurname(new SimpleStringProperty(rs.getString("surname")));
			person.setForenames(new SimpleStringProperty(rs
					.getString("forenames")));

			persons.add(person);
		}
		rs.close();
		st.close();

		PostgresConnectionFactory.closeConnection(connection);

		return persons;

	}
}
