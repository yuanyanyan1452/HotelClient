package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class OrderVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	int clientid;
	int hotelid;
	String state;
	Date cancel_time;
	boolean execute;
	Date start_time;
	Date end_time;
	Date latest_execute_time;
	ArrayList<RoomOrderVO>room_order;
	int price;
	int expect_number_of_people;
	boolean havechild;
	String evaluation;
	
	public OrderVO(){
		id=0;
		clientid=0;
		hotelid=0;
		state=null;
		cancel_time=null;
		execute=false;
		start_time=null;
		end_time=null;
		latest_execute_time=null;
		room_order=null;
		price=0;
		expect_number_of_people=0;
		havechild=false;
		evaluation=null;
	}
	public OrderVO(int i,int cid,int hid,String s,Date cancel,boolean e,Date st,Date et,Date lt,ArrayList<RoomOrderVO>list,int p,int en,boolean child,String eva){
		id=i;
		clientid=cid;
		hotelid=hid;
		state=s;
		cancel_time=cancel;
		execute=e;
		start_time=st;
		end_time=et;
		latest_execute_time=lt;
		room_order=list;
		price=p;
		expect_number_of_people=en;
		havechild=child;
		evaluation =eva;
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
	
	public void setcancel_time(Date cancel){
		cancel_time=cancel;
	}
	public Date getcancel_time(){
		return cancel_time;
	}
	
	public void setexecute(boolean isExecute){
		execute = isExecute;
	}
	public boolean getexecute(){
		return execute;
	}
	
	public void setstart_time(Date start_time){
		this.start_time = start_time;
	}
	public Date getstart_time(){
		return start_time;
	}
	
	public void setend_time(Date end_time){
		this.end_time = end_time;
	}
	public Date getend_time(){
		return end_time;
	}
	
	public void setlatest_execute_time(Date latest_execute_time){
		this.latest_execute_time = latest_execute_time; 
	}
	public Date getlatest_execute_time(){
		return latest_execute_time; 
	}
	
	public void setroom_order(ArrayList<RoomOrderVO> list){
		room_order = list;
	}
	public ArrayList<RoomOrderVO> getroom_order(){
		return room_order;
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
	
	public void setevaluation(String evaluation){
		this.evaluation=evaluation;
	}
	public String getevaluation(){
		return evaluation;
	}
	
}
