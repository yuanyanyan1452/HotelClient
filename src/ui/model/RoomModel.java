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
	
	public void setRoomPrice(int roomprice){
		this.roomprice.set(String.valueOf(roomprice));
	}
	
	public SimpleStringProperty roomPriceProperty(){
		return roomprice;
	}
	
	public String getRoomNum(){
		return roomnum.get();
	}
	
	public void setRoomNum(int roomnum){
		this.roomnum.set(String.valueOf(roomnum));
	}
	
	public SimpleStringProperty roomNumProperty(){
		return roomnum;
	}
}
