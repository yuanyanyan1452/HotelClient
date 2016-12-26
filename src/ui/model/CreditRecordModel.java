package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class CreditRecordModel {

	private final SimpleStringProperty time;
	private final SimpleStringProperty orderid;
	private final SimpleStringProperty action;
	private final SimpleStringProperty change;
	private final SimpleStringProperty result;
	
	public CreditRecordModel(String record){
		if (record!=null&&!record.isEmpty()) {
			record = record.replace('，', ',');
			String[] records = record.split(",");
			time = new SimpleStringProperty(records[0].split("'")[0]);
			orderid = new SimpleStringProperty(records[1]);
			action = new SimpleStringProperty(records[2]);
			change = new SimpleStringProperty(records[3]);
			result = new SimpleStringProperty(records[4]);
		}
		else{
			time = new SimpleStringProperty();
			orderid = new SimpleStringProperty();
			action = new SimpleStringProperty();
			change = new SimpleStringProperty();
			result = new SimpleStringProperty();
		}
		
	}
	
	public SimpleStringProperty timeProperty(){
		return time;
	}
	
	public SimpleStringProperty orderidProperty(){
		return orderid;
	}
	
	public SimpleStringProperty actionProperty(){
		return action;
	}
	
	public SimpleStringProperty changeProperty(){
		return change;
	}
	
	public SimpleStringProperty resultProperty(){
		return result;
	}
}
