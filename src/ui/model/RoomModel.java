package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class RoomModel {
	private final SimpleStringProperty roomtype;
	private final SimpleStringProperty roomnum;
	private final  SimpleStringProperty roomprice;
	
	public RoomModel() {
		roomprice = new SimpleStringProperty();
		roomtype = new SimpleStringProperty();
		roomnum = new SimpleStringProperty();
	}
	
	public String getRoomType(){
		return roomtype.get();
	}
	
	public void setRoomType(String roomtype){
		this.roomtype.set(roomtype);
	}
	
	public SimpleStringProperty roomTypeProperty(){
		return roomtype;
	}
	
	public String getRoomPrice(){
		return roomprice.get();
	}
	
	public void setRoomPrice(String roomprice){
		this.roomprice.set(roomprice);
	}
	
	public SimpleStringProperty roomPriceProperty(){
		return roomprice;
	}
	
	public String getRoomNum(){
		return roomnum.get();
	}
	
	public void setRoomNum(String roomnum){
		this.roomnum.set(roomnum);
	}
	
	public SimpleStringProperty roomNumProperty(){
		return roomnum;
	}
}
