package objects;

import java.util.ArrayList;

public class Hotel {
	int hotelid ;
	String name;
	String address;
	String bussiness_address;
	String introduction;
	String service;
	String star;
	String score;
	ArrayList<String>hotel_evaluation;
	ArrayList<Integer> book_clientid;
	
	public Hotel(){
		hotelid=0;
		address=null;
		bussiness_address=null;
		name=null;
		introduction=null;
		service=null;
		star=null;
		score=null;
		hotel_evaluation=null;
		book_clientid=null;
	}
	
	public Hotel(int id,String na,String a,String ba,String in,String s,String star,String score,ArrayList<String>e,ArrayList<Integer>book){
		hotelid = id;
		name=na;
		address=a;
		bussiness_address=ba;
		introduction=in;
		service=s;
		this.star=star;
		this.score=score;
		hotel_evaluation=e;
		book_clientid=book;
	}
	
	public void setid(int id){
		hotelid = id;
	}
	public int getid(){
		return hotelid;
	}
	
	public void setname(String name){
		this.name = name;
	}
	public String getname(){
		return name;
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
	
	public void setstar(String star){
		this.star = star;
	}
	public String getstar(){
		return star;
	}
	
	public void setscore(String score){
		this.score = score;
	}
	public String getscore(){
		return score;
	}
	
	public void setevaluation(ArrayList<String> evalu){
		hotel_evaluation = evalu;
	}
	public ArrayList<String> getevaluation(){
		return hotel_evaluation;
	}

	public void setbook_clientid(ArrayList<Integer>book){
		book_clientid=book;
	}
	public ArrayList<Integer> getbook_clientid(){
		return book_clientid;
	}

}
