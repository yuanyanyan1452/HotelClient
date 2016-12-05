package ui.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HotelModel {
	private final SimpleStringProperty hotelName;
	private final SimpleStringProperty businessAddress;
	private final SimpleStringProperty address;
	private final SimpleStringProperty star;
	private final SimpleStringProperty score;
	
	public HotelModel(){
		this(null, null, null, null,null);
	}
	
	public HotelModel(String hotelname,String businessaddress,String address,String star,String score){
		this.hotelName =new SimpleStringProperty(hotelname);
		this.businessAddress = new SimpleStringProperty(businessaddress);
		this.address = new SimpleStringProperty(address);
		this.star = new SimpleStringProperty(star);
		this.score = new SimpleStringProperty(score);
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

	public String getScore() {
		return this.score.get();
	}
}
