package objects;

import vo.WebMarketVO;

public class WebMarket {
	int webmarketid;
	String name;
	String contact;
	String username;
	String password;
	
	public WebMarket(){
		webmarketid = 0;
		name=null;
		contact=null;
		username=null;
		password=null;
	}
	
	public WebMarket(int id,String n,String c,String u,String p){
		webmarketid = id;
		name=n;
		contact=c;
		username=u;
		password=p;
	}

	public int getwebmarketid() {
		return webmarketid;
	}

	public void setwebmarketid(int webmarketid) {
		this.webmarketid = webmarketid;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getcontact() {
		return contact;
	}

	public void setcontact(String contact) {
		this.contact = contact;
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
	
	
	public WebMarketVO changetowebmarketvo(){
		WebMarketVO vo=new WebMarketVO();
		vo.setwebmarketid(this.webmarketid);
		vo.setname(this.name);
		vo.setcontact(this.contact);
		vo.setusername(this.username);
		vo.setpassword(this.password);
		return vo;
	}
}

