package vo;

import java.io.Serializable;

public class WebStrategyVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int wsid;
	String name;
	String condition;
	String start_time;
	String end_time;
	String executeway;
	boolean superposition;
	
	public WebStrategyVO(){
		wsid=0;
		name=null;
		condition=null;
		start_time=null;
		end_time=null;
		executeway=null;
		superposition=false;
	}
	
	public WebStrategyVO(int id,String n,String con,String st,String et,String ew,boolean iss){
		wsid=id;
		name=n;
		condition=con;
		start_time=st;
		end_time=et;
		executeway=ew;
		superposition=iss;
	}

	public void setid(int id){
		wsid = id;
	}
	public int getid(){
		return wsid;
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
	
	public void setstart_time(String start_time){
		this.start_time=start_time;
	}
	public String getstart_time(){
		return start_time;
	}
	
	public void setend_time(String end_time){
		this.end_time=end_time;
	}
	public String getend_time(){
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
}
