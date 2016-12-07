package vo;



public class WebManagerVO {
	int webmanagerid;
	String name;
	String contact;
	String username;
	String password;
	
	public WebManagerVO(){
		webmanagerid=0;
		name=null;
		contact=null;
		username=null;
		password=null;
	}
	
	public WebManagerVO(int i,String n,String c,String u,String p){
		webmanagerid = i;
		name=n;
		contact=c;
		username=u;
		password=p;
//		id=i;
	}
	public int getwebmanagerid() {
		return webmanagerid;
	}
	public void setwebmanagerid(int webmanagerid) {
		this.webmanagerid = webmanagerid;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	public String getname(){
		return name;
	}
	
	
	public void setcontact(String contact) {
		this.contact = contact;
	}
	public String getcontact(){
		return contact;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	public String getusername() {
		return username;
	}
	
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
}
