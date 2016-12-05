package vo;

import java.io.Serializable;

public class WebManagerVO implements Serializable{
	String username;
	String password;
	String name;
	String contact;
	
	public WebManagerVO(String u,String p,String n,String c){
		username=u;
		password=p;
		name=n;
		contact=c;
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
