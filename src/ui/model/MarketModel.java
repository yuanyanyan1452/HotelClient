package ui.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MarketModel {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty name;
	private final SimpleStringProperty contact;
	
	public MarketModel(int id,String name,String contact){
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.contact=new SimpleStringProperty(contact);
	}
	
	public int getID(){
		return id.get();
	}
	
	public void setID(int id){
		this.id.set(id);
	}
	
	public SimpleIntegerProperty idProperty(){
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
		return name.get();
	}
	
	public void setContact(String contact){
		this.name.set(contact);
	}
	
	public SimpleStringProperty contactProperty(){
		return contact;
	}
}
