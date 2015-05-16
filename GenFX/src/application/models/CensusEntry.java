package application.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class CensusEntry extends AbstractDateModel {
	private StringProperty address;
	private StringProperty age;
	private StringProperty birthplace;
	private IntegerProperty censusHouseholdId;
	private StringProperty censusTitle;
	private StringProperty condition;
	private StringProperty name;
	private StringProperty occupation;
	private IntegerProperty personId;
	private StringProperty relationshipToHead;
	
	public StringProperty getAddress() {
		return address;
	}
	public void setAddress(StringProperty address) {
		this.address = address;
	}
	public IntegerProperty getCensusHouseholdId() {
		return censusHouseholdId;
	}
	public void setCensusHouseholdId(IntegerProperty censusHouseholdId) {
		this.censusHouseholdId = censusHouseholdId;
	}
	public StringProperty getCensusTitle() {
		return censusTitle;
	}
	public void setCensusTitle(StringProperty censusTitle) {
		this.censusTitle = censusTitle;
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public IntegerProperty getPersonId() {
		return personId;
	}
	public void setPersonId(IntegerProperty personId) {
		this.personId = personId;
	}
	public StringProperty getAge() {
		return age;
	}
	public void setAge(StringProperty age) {
		this.age = age;
	}
	public StringProperty getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(StringProperty birthplace) {
		this.birthplace = birthplace;
	}
	public StringProperty getCondition() {
		return condition;
	}
	public void setCondition(StringProperty condition) {
		this.condition = condition;
	}
	public StringProperty getOccupation() {
		return occupation;
	}
	public void setOccupation(StringProperty occupation) {
		this.occupation = occupation;
	}
	public StringProperty getRelationshipToHead() {
		return relationshipToHead;
	}
	public void setRelationshipToHead(StringProperty relationshipToHead) {
		this.relationshipToHead = relationshipToHead;
	}
}
