package ui.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MarketModel {
	private final SimpleStringProperty id;
	private final SimpleStringProperty name;
	private final SimpleStringProperty contact;
	
	public MarketModel(){
		this.id = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
		this.contact=new SimpleStringProperty();
	}
	
	public String getID(){
		return id.get();
	}
	
	public void setID(int id){
		this.id.set(String.valueOf(id));
	}
	
	public SimpleStringProperty idProperty(){
		return id;
	}
	
	public String getName(){
		return name.get();
	}
	
	public void setName(String name){
		this.name.set(name);
	}
	
	public SimpleStringProperty nameProperty(){
		return name;
	}
	
	public String getContact(){
		return contact.get();
	}
	
	public void setContact(String contact){
		this.contact.set(contact);
	}
	
	public SimpleStringProperty contactProperty(){
		return contact;
	}
}
