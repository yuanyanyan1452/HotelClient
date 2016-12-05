package vo;

import java.io.Serializable;

public class OrderVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	int clientid;
	int hotelid;
	String state;
	boolean execute;
	String start_time;
	String end_time;
	String latest_execute_time;
	String room_type;
	int room_number;
	String strategy;
	int price;
	int expect_number_of_people;
	boolean havechild;
	
	public OrderVO(){
		id=0;
		clientid=0;
		hotelid=0;
		state=null;
		execute=false;
		start_time=null;
		end_time=null;
		latest_execute_time=null;
		room_type=null;
		room_number=0;
		strategy=null;
		price=0;
		expect_number_of_people=0;
		havechild=false;
	}
	public OrderVO(int i,int cid,int hid,String s,boolean e,String st,String et,String lt,String rt,int rm,String str,int p,int en,boolean child){
		id=i;
		clientid=cid;
		hotelid=hid;
		state=s;
		execute=e;
		start_time=st;
		end_time=et;
		latest_execute_time=lt;
		room_type=rt;
		room_number=rm;
		strategy=str;
		price=p;
		expect_number_of_people=en;
		havechild=child;
	}
	
	public void setid(int id){
		this.id = id;
	}
	public int getid(){
		return id;
	}
	
	public void setclientid(int id){
		clientid = id;
	}
	public int getclientid(){
		return clientid;
	}
	
	public void sethotelid(int id){
		hotelid = id;
	}
	public int gethotelid(){
		return hotelid;
	}
	
	public void setstate(String state){
		this.state = state;
	}
	public String getstate(){
		return state;
	}
	
	public void setexecute(boolean isExecute){
		execute = isExecute;
	}
	public boolean getexecute(){
		return execute;
	}
	
	public void setstart_time(String start_time){
		this.start_time = start_time;
	}
	public String getstart_time(){
		return start_time;
	}
	
	public void setend_time(String end_time){
		this.end_time = end_time;
	}
	public String getend_time(){
		return end_time;
	}
	
	public void setlatest_execute_time(String latest_execute_time){
		this.latest_execute_time = latest_execute_time; 
	}
	public String getlatest_execute_time(){
		return latest_execute_time; 
	}
	
	public void setroom_type(String type){
		room_type = type;
	}
	public String getroom_type(){
		return room_type;
	}
	
	public void setroom_number(int number){
		room_number = number;
	}
	public int getroom_number(){
		return room_number;
	}
	
	public void setstrategy(String strategy){
		this.strategy = strategy;
	}
	public String getstrategy(){
		return strategy;
	}
	
	public void setprice(int price){
		this.price = price;
	}
	public int getprice(){
		return price;
	}
	
	public void setexpect_number_of_people(int numOfPeople){
		expect_number_of_people = numOfPeople;
	}
	public int getexpect_number_of_people(){
		return expect_number_of_people;
	}
	
	public void sethave_child(boolean child){
		havechild=child;
	}
	public boolean gethave_child(){
		return havechild;
	}
}
