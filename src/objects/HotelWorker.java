package objects;

import java.io.Serializable;

public class HotelWorker implements Serializable{
	int hotelid;
	String name;
	String contact;
	String username;
	String password;
	
	public HotelWorker(){
		hotelid=0;
		name=null;
		contact=null;
		username=null;
		password=null;
	}
	
	public HotelWorker(int id,String n,String c,String u,String p){
		hotelid=id;
		name=n;
		contact=c;
		username=u;
		password=p;
	}
	
	public int gethotelid() {
		return hotelid;
	}
	public void sethotelid(int hotelid) {
		this.hotelid = hotelid;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	public String getname() {
		return name;
	}
	
	public void setcontact(String contact) {
		this.contact = contact;
	}
	public String getcontact() {
		return contact;
	}

	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	
	
}
