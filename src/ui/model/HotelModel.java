package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class HotelModel {
	private final SimpleStringProperty id;
	private final SimpleStringProperty hotelName;
	private final SimpleStringProperty businessAddress;
	private final SimpleStringProperty address;
	private final SimpleStringProperty star;
	private final SimpleStringProperty score;
	private final SimpleStringProperty orderstate;
	private final SimpleStringProperty cheapestRoomPrice;
	
	public HotelModel(){
		id = new SimpleStringProperty();
		hotelName = new SimpleStringProperty();
		businessAddress = new SimpleStringProperty();
		address = new SimpleStringProperty();
		star = new SimpleStringProperty();
		score = new SimpleStringProperty();
		orderstate = new SimpleStringProperty();
		cheapestRoomPrice = new SimpleStringProperty();
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
	public String getHotelName(){
		return this.hotelName.get();
	}
	
	public void setHotelName(String hotelname){
		this.hotelName.set(hotelname);
	}
	
	public SimpleStringProperty hotelNameProperty(){
		return this.hotelName;
	}
	
	public String getBusinessAddress(){
		return this.businessAddress.get();
	}
	
	public void setBusinessAddress(String businessaddress){
		this.businessAddress.set(businessaddress);
	}
	
	public SimpleStringProperty businessAddressProperty(){
		return this.businessAddress;
	}
	
	public String getAddress(){
		return this.address.get();
	}
	
	public void setAddress(String address){
		this.address.set(address);
	}
	
	public SimpleStringProperty addressProperty(){
		return this.address;
	}
	

	
	public SimpleStringProperty starProperty(){
		return this.star;
	}
	

	
	public SimpleStringProperty scoreProperty(){
		return this.score;
	}

	public String getStar() {
		return this.star.get();
	}
	
	public void setStar(String star){
		this.star.set(star);
	}

	public String getScore() {
		return this.score.get();
	}
	
	public void setScore(String score){
		this.score.set(score);
	}
	
	public String getOrderState(){
		return orderstate.get();
	}
	
	public void setOrderState(String orderstate){
		this.orderstate.set(orderstate);
	}
	
	public SimpleStringProperty orderStateProperty(){
		return orderstate;
	}
	
	public String getCheapestRoomPrice(){
		return cheapestRoomPrice.get();
	}
	
	public void setCheapestRoomPrice(int price){
		cheapestRoomPrice.set(price+"");
	}
	
	public SimpleStringProperty cheapestRoomPriceProperty(){
		return cheapestRoomPrice;
	}
}
