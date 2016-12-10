package objects;

import java.util.Date;

import vo.HotelStrategyVO;

public class HotelStrategy {

	private static final long serialVersionUID = 1L;
	int hsid;
	int hotelid;
	String name;
	String condition;
	Date start_time;
	Date end_time;
	String executeway;
	boolean superposition;
	
	public HotelStrategy(){
		hsid=0;
		hotelid=0;
		name=null;
		condition=null;
		start_time=null;
		end_time=null;
		executeway=null;
		superposition=false;
	}
	
	public HotelStrategy(int id,int hid,String n,String con,Date st,Date et,String ew,boolean iss){
		hsid=id;
		hotelid=hid;
		name=n;
		condition=con;
		start_time=st;
		end_time=et;
		executeway=ew;
		superposition=iss;
	}
	
	public void setid(int id){
		hsid = id;
	}
	public int getid(){
		return hsid;
	}
	
	public void sethotelid(int id){
		hotelid = id;
	}
	public int gethotelid(){
		return hotelid;
	}
	
	public void setname(String name){
		this.name = name;
	}
	public String getname(){
		return name;
	}
	
	public void setcondition(String condition){
		this.condition = condition;
	}
	public String getcondition(){
		return condition;
	}
	
	public void setstart_time(Date start_time){
		this.start_time=start_time;
	}
	public Date getstart_time(){
		return start_time;
	}
	
	public void setend_time(Date end_time){
		this.end_time=end_time;
	}
	public Date getend_time(){
		return end_time;
	}
	
	public void setexecuteway(String executeway){
		this.executeway=executeway;
	}
	public String getexecuteway(){
		return executeway;
	}
	
	public void setsuperposition(boolean superposition){
		this.superposition=superposition;
	}
	public boolean getsuperposition(){
		return superposition;
	}

	
	public HotelStrategyVO changetohotelstrategyvo(){
		HotelStrategyVO vo=new HotelStrategyVO();
		vo.setid(this.hsid);
		vo.sethotelid(this.hotelid);
		vo.setname(this.name);
		vo.setcondition(this.condition);
		vo.setstart_time(this.start_time);
		vo.setend_time(this.end_time);
		vo.setexecuteway(this.executeway);
		vo.setsuperposition(this.superposition);
		return vo;
	}
}
