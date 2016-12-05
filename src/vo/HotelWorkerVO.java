package vo;

import java.io.Serializable;

public class HotelWorkerVO implements Serializable{
	int hotelid;
	String username;
	String password;
	String name;
	String contact;
	String hotel;
	
	public HotelWorkerVO(int id,String user,String pa,String n,String c,String h){
		hotelid = id;
		username=user;
		password=pa;
		name=n;
		contact=c;
		hotel=h;
	}
	public int gethotelid(){
		return hotelid;
	}
	public String getusername(){
		return username;
	}
	public void setusername(String um){
		username=um;;
	}
	public String getpassword(){
		return password;
	}
	public void setpassword(String pass){
		password=pass;
	}
	public String getname(){
		return name;
	}
	public void setname(String na) {
		name = na;
	}
	public String getcontact(){
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String gethotel(){
		return hotel;
	}
	public void sethotel(String h){
		hotel=h;
	}
}
