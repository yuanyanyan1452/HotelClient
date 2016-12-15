package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class HotelWorkerModel {

	private final SimpleStringProperty hotelid;
	private final SimpleStringProperty name;
	private final SimpleStringProperty contact;
	
	public HotelWorkerModel() {
		hotelid = new SimpleStringProperty();
		name = new SimpleStringProperty();
		contact = new SimpleStringProperty();
	}
	
	public String getHotelid(){
		return hotelid.get();
	}
	
	public void setHotelid(int id){
		hotelid.set(String.valueOf(id));
	}
	
	public SimpleStringProperty hotelidProperty(){
		return hotelid;
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
