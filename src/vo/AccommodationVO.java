package vo;

import java.io.Serializable;

public class AccommodationVO implements Serializable{
	String roomNumber;
	String check_in;
	String plan_check_out;
	String actual_check_out;
	
	public AccommodationVO(String rN,String checkIn,String plancheckOut,String actualcheckOut){
		roomNumber = rN;
		check_in = checkIn;
		plan_check_out = plancheckOut;
		actual_check_out = actualcheckOut;
	}
	
	public String getRoomNumber(){
		return roomNumber;
	}
	public String getCheckIn(){
		return check_in;
	}
	public String getPlanCheckOut(){
		return plan_check_out;
	}
	public String getActualCheckOut(){
		return actual_check_out;
	}
	public void setRoomNumber(String rm){
		roomNumber=rm;
	}
	public void setCheckIn(String checkin){
		check_in=checkin;
	}
	public void setPlanCheckOut(String plancheckout){
		plan_check_out=plancheckout;
	}
	public void setActualCheckOut(String actualcheckout){
		actual_check_out=actualcheckout;
	}
}
