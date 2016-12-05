package vo;

import java.io.Serializable;

public class WebMarketVO implements Serializable{
	int webmarketid;
	String username;
	String password;
	String name;
	String contact;
	
	public WebMarketVO(int id,String um,String pa,String n,String c){
		webmarketid = id;
		username=um;
		password=pa;
		name=n;
		contact=c;
	}
	public int getid(){
		return webmarketid;
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
}
