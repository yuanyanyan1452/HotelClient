package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelVO implements Serializable{
	int hotelid ;
	String address;
	String bussiness_address;
	String name;
	String introduction;
	String service;
	int score; 
	String evaluation;
	String star;
	int min_price;
	ArrayList<Integer> book_clientid;
	
	public HotelVO(){
		hotelid=0;
		address=null;
		bussiness_address=null;
		name=null;
		introduction=null;
		service=null;
		score=0;
		evaluation=null;
		star=null;
		min_price=0;
		book_clientid=null;
	}
	
	public HotelVO(int id,String a,String ba,String na,String in,String s,int sc,String e,String m,int mp,ArrayList<Integer>book){
		hotelid = id;
		address=a;
		bussiness_address=ba;
		name=na;
		introduction=in;
		service=s;
		score=sc;
		evaluation=e;
		star=m;
		min_price=mp;
		book_clientid=book;
	}
	
	
	
	
	public void setid(int id){
		hotelid = id;
	}
	public int getid(){
		return hotelid;
	}
	
	public void setaddress(String addr){
		address = addr;
	}
	public String getaddress(){
		return address;
	}
	
	public void setbussiness_address(String bussi_addr){
		bussiness_address = bussi_addr;
	}
	public String getbussiness_address(){
		return bussiness_address;
	}
	
	public void setname(String name){
		this.name = name;
	}
	public String getname(){
		return name;
	}
	
	public void setintroduction(String intro){
		introduction = intro;
	}
	public String getintroduction(){
		return introduction;
	}
	
	public void setservice(String service){
		this.service = service;
	}
	public String getservice(){
		return service;
	}
	
	public void setscore(int m){
		score=m;
	}
	public int getcore(){
		return score;
	}
	
	public void setevaluation(String evalu){
		evaluation = evalu;
	}
	public String getevaluation(){
		return evaluation;
	}
	
	
	public void setstar(String m){
		star=m;
	}
	public String getstar(){
		return star;
	}
	
	public void setmin_price(int m){
		min_price=m;
	}
	public int getmin_price(){
		return min_price;
	}
	
	public void setbook_clientid(ArrayList<Integer>book){
		book_clientid=book;
	}
	public ArrayList<Integer> getbook_clientid(){
		return book_clientid;
	}
}
