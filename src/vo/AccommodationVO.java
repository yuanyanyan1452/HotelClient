package vo;

import java.io.Serializable;
import java.util.Date;

public class AccommodationVO implements Serializable{
	String roomNumber;
	Date check_in;
	Date plan_check_out;
	Date actual_check_out;
	
	public AccommodationVO(String rN,Date checkIn,Date plancheckOut,Date actualcheckOut){
		roomNumber = rN;
		check_in = checkIn;
		plan_check_out = plancheckOut;
		actual_check_out = actualcheckOut;
	}
	
	public String getRoomNumber(){
		return roomNumber;
	}
	public Date getCheckIn(){
		return check_in;
	}
	public Date getPlanCheckOut(){
		return plan_check_out;
	}
	public Date getActualCheckOut(){
		return actual_check_out;
	}
	public void setRoomNumber(String rm){
		roomNumber=rm;
	}
	public void setCheckIn(Date checkin){
		check_in=checkin;
	}
	public void setPlanCheckOut(Date plancheckout){
		plan_check_out=plancheckout;
	}
	public void setActualCheckOut(Date actualcheckout){
		actual_check_out=actualcheckout;
	}
}
