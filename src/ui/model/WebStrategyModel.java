package ui.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import vo.WebStrategyVO;

public class WebStrategyModel {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty name;
	private final SimpleStringProperty startTime;
	private final SimpleStringProperty endTime;
	private final SimpleStringProperty discount;
	private final SimpleStringProperty condition;
	private final SimpleStringProperty superpositon;
	
	public WebStrategyModel(int id,String name,String startTime,String endTime,String discount,String condition,String superposition){
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.startTime = new SimpleStringProperty(startTime);
		this.endTime = new SimpleStringProperty(endTime);
		this.discount = new SimpleStringProperty(discount);
		this.condition = new SimpleStringProperty(condition);
		this.superpositon = new SimpleStringProperty(superposition);
	}
	
	public int getID(){
		return id.get();
	}
	
	public void setID(int id){
		this.id.set(id);
	}
	
	public SimpleIntegerProperty idProperty(){
		return id;
	}
	
	public String getName(){
		return name.get();
	}
	
	public void setName(String name){
		this.name.set(name);
	}
	
	public SimpleStringProperty nameProperty(){
		return name;
	}
	
	public String getStartTime(){
		return startTime.get();
	}
	
	public void setStartTime(String startTime){
		this.startTime.set(startTime);
	}
	
	public SimpleStringProperty startTimeProperty(){
		return startTime;
	}
	
	public String getEndTime(){
		return endTime.get();
	}
	
	public void setEndTime(String endTime){
		this.endTime.set(endTime);
	}
	
	public SimpleStringProperty endTimeProperty(){
		return endTime;
	}
	
	public String getDiscount(){
		return discount.get();
	}
	
	public void setDiscount(String discount){
		this.discount.set(discount);
	}
	
	public SimpleStringProperty discountProperty(){
		return discount;
	}
	
	public String getCondition(){
		return condition.get();
	}
	
	public void setCondition(String condition){
		this.condition.set(condition);
	}
	
	public SimpleStringProperty conditionProperty(){
		return condition;
	}
	
	public String getSuperposition(){
		return superpositon.get();
	}
	
	public void setSuperposition(String superposition){
		superpositon.set(superposition);
	}
	
	public SimpleStringProperty superpostionProperty(){
		return superpositon;
	}
	
	public WebStrategyVO changeToVO(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		WebStrategyVO vo = new WebStrategyVO();
		Date time;
		vo.setid(id.get());
		vo.setname(name.get());
		try {
			time = format.parse(startTime.get());
			vo.setstart_time(time);
			time = format.parse(endTime.get());
			vo.setend_time(time);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		vo.setexecuteway(discount.get());
		vo.setcondition(condition.get());
		vo.setsuperposition(superpositon.get().equals("是")? true:false);
		
		return vo;
	}
}
