package ui.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import objects.OrderState;

public class OrderModel {
	private SimpleStringProperty orderid;
	private SimpleStringProperty clientid;
	private SimpleStringProperty hotelid;
	private SimpleStringProperty state;
	private SimpleStringProperty cancelTime;
	private SimpleStringProperty isExecute;
	private SimpleStringProperty startTime;
	private SimpleStringProperty endTime;
	private SimpleStringProperty latestExecuteTime;
	private SimpleStringProperty roomOrder;
	private SimpleStringProperty price;
	private SimpleStringProperty numOfPeople;
	private SimpleStringProperty haveChild;
	private SimpleStringProperty overTime;
	private SimpleStringProperty punishCredit;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public OrderModel() {

	}

	public String getOrderid() {
		return orderid.get();
	}

	public void setOrderid(int orderid) {
		this.orderid.set(orderid + "");
	}

	public SimpleStringProperty orderidProperty() {
		return orderid;
	}

	public String getClientid() {
		return clientid.get();
	}

	public void setClientid(int clientid) {
		this.clientid.set(clientid + "");
	}

	public SimpleStringProperty clientidProperty() {
		return clientid;
	}

	public String getHotelid() {
		return hotelid.get();
	}

	public void setHotelid(int hotelid) {
		this.hotelid.set(hotelid + "");
	}

	public SimpleStringProperty hotelidProperty() {
		return hotelid;
	}

	public String getState() {
		return state.get();
	}

	public void setState(String state) {
		this.state.set(state);
	}

	public SimpleStringProperty stateProperty() {
		return state;
	}

	public String getCancelTime() {
		return cancelTime.get();
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime.set(format.format(cancelTime));
	}

	public SimpleStringProperty cancelTimeProperty() {
		return cancelTime;
	}

	public String getIsExecute() {
		return isExecute.get();
	}

	public void setIsExecute(boolean isExecute) {
		this.isExecute.set(isExecute ? "是" : "否");
	}
	
	public SimpleStringProperty isExecuteProperty(){
		return isExecute;
	}
	
	public String getStartTime(){
		return startTime.get();
	}
	
	public void setStartTime(Date startTime){
		this.startTime.set(format.format(startTime));
	}
	
	public SimpleStringProperty startTimeProperty(){
		return startTime;
	}
	
	public String getEndTime(){
		return endTime.get();
	}
	
	public void setEndTime(Date endTime){
		this.endTime.set(format.format(endTime));
	}
	
	public SimpleStringProperty endTimeProperty(){
		return endTime;
	}
	
	public String getLatestExecuteTime(){
		return latestExecuteTime.get();
	}
	
	public void setLatestExecuteTime(Date time){
		latestExecuteTime.set(format.format(time));
	}
	
	public SimpleStringProperty latestExecuteTimeProperty(){
		return latestExecuteTime;
	}
	
	public String getRoomOrder(){
		return roomOrder.get();
	}
	
	public void setRoomOrder(String roomorder){
		this.roomOrder.set(roomorder);
	}
	
	public SimpleStringProperty roomOrderProperty(){
		return roomOrder;
	}
	
	public String getPrice(){
		return price.get();
	}
	
	public void setPrice(int price){
		this.price.set(String.valueOf(price));
	}
	
	public SimpleStringProperty priceProperty(){
		return price;
	}
	
	public String getNumOfPeople(){
		return numOfPeople.get();
	}
	
	public void setNumOfPeople(int num){
		numOfPeople.set(String.valueOf(num));
	}
	
	public SimpleStringProperty numOfPeopleProperty(){
		return numOfPeople;
	}
	
	public String getHaveChild(){
		return haveChild.get();
	}
	
	public void setHaveChild(boolean havechild){
		haveChild.set(havechild?"是":"否");
	}
	
	public SimpleStringProperty haveChildProperty(){
		return haveChild;
	}
	
	public String getOverTime(){
		return overTime.get();
	}
	
	public void setOverTime(Date now){
		long overTime;
		try {
			overTime = now.getTime() - format.parse(latestExecuteTime.get()).getTime();
			this.overTime.set(String.valueOf(overTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public SimpleStringProperty overTimeProperty(){
		return overTime;
	}
	
	public String getPunishCredit(){
		return punishCredit.get();
	}
	
	public void setPunishCredit(int punishCredit){
		this.punishCredit.set(String.valueOf(punishCredit));
	}
	
	public SimpleStringProperty punishCreditProperty(){
		return punishCredit;
	}
}
