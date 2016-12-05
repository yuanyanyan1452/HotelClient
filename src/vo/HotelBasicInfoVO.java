package vo;

import java.io.Serializable;

public class HotelBasicInfoVO implements Serializable{
	int hotelid ;
	String name;
	int score; 
	int star;
	int min_price;
	public HotelBasicInfoVO(int id,String na,int sc,int m,int mp){
		hotelid = id;
		name=na;
		score=sc;
		star=m;
		min_price=mp;
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
	public void setscore(int m){
		score=m;
	}
	public int getcore(){
		return score;
	}
	public void setstar(int m){
		star=m;
	}
	public int getstar(){
		return star;
	}
	
	public void setmin_price(int m){
		min_price=m;
	}
	public int getmin_price(){
		return min_price;
	}
	
}
