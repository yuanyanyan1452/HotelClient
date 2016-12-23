package ui.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;

public class OrderModel {
	private final SimpleStringProperty orderid;
	private final SimpleStringProperty clientid;
	private final SimpleStringProperty hotelid;
	private final SimpleStringProperty hotelname;
	private final SimpleStringProperty state;
	private final SimpleStringProperty cancelTime;
	private final SimpleStringProperty isExecute;
	private final SimpleStringProperty startTime;
	private final SimpleStringProperty endTime;
	private final SimpleStringProperty latestExecuteTime;
	private final SimpleStringProperty roomOrder;
	private final SimpleStringProperty roomtype;
	private final SimpleStringProperty roomnumber;
	private final SimpleStringProperty price;
	private final SimpleStringProperty numOfPeople;
	private final SimpleStringProperty haveChild;
	private final SimpleStringProperty overTime;
	private final SimpleStringProperty punishCredit;
	private final SimpleStringProperty predictLeaveTime;
	private static final String ISEXECUTE = "是";
	private static final String NOEXECUTE = "否";
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public OrderModel() {
		orderid=new SimpleStringProperty();
		clientid = new SimpleStringProperty();
		hotelid = new SimpleStringProperty();
		hotelname=new SimpleStringProperty();
		state = new SimpleStringProperty();
		cancelTime = new SimpleStringProperty();
		isExecute = new SimpleStringProperty();
		startTime = new SimpleStringProperty();
		endTime = new SimpleStringProperty();
		latestExecuteTime = new SimpleStringProperty();
		roomOrder = new SimpleStringProperty();
		roomtype = new SimpleStringProperty();
		roomnumber = new SimpleStringProperty();
		price = new SimpleStringProperty();
		numOfPeople = new SimpleStringProperty();
		haveChild = new SimpleStringProperty();
		overTime = new SimpleStringProperty();
		punishCredit = new SimpleStringProperty();
		predictLeaveTime = new SimpleStringProperty();
	}

	public String getOrderid() {
		return this.orderid.get();
	}

	public void setOrderid(int orderid) {
		this.orderid.set(orderid+"");
	}

	public SimpleStringProperty orderidProperty() {
		return this.orderid;
	}

	public String getClientid() {
		return this.clientid.get();
	}

	public void setClientid(int clientid) {
		this.clientid.set(clientid+"");
	}

	public SimpleStringProperty clientidProperty() {
		return this.clientid;
	}

	public String getHotelid() {
		return this.hotelid.get();
	}

	public void setHotelid(int hotelid) {
		this.hotelid.set(hotelid+"");
	}

	public SimpleStringProperty hotelidProperty() {
		return this.hotelid;
	}

	public String getHotelname() {
		return this.hotelname.get();
	}

	public void setHotelname(String hotelname) {
		this.hotelname.set(hotelname);
	}

	public SimpleStringProperty hotelnameProperty() {
		return this.hotelname;
	}
	
	public String getState() {
		return this.state.get();
	}

	public void setState(String state) {
		this.state.set(state);
	}

	public SimpleStringProperty stateProperty() {
		return this.state;
	}

	public String getCancelTime() {
		return this.cancelTime.get();
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime.set(format.format(cancelTime));
	}

	public SimpleStringProperty cancelTimeProperty() {
		return this.cancelTime;
	}

	public String getIsExecute() {
		return this.isExecute.get();
	}

	public void setIsExecute(boolean isExecute) {
		this.isExecute.set(isExecute ? ISEXECUTE :NOEXECUTE);
	}
	
	public SimpleStringProperty isExecuteProperty(){
		return this.isExecute;
	}
	
	public String getStartTime(){
		return this.startTime.get();
	}
	
	public void setStartTime(Date startTime){
		if(startTime!=null)
			this.startTime.set(format.format(startTime));
	}
	
	public SimpleStringProperty startTimeProperty(){
		return this.startTime;
	}
	
	public String getEndTime(){
		return this.endTime.get();
	}
	
	public void setEndTime(Date endTime){
		if(endTime!=null)
			this.endTime.set(format.format(endTime));
	}
	
	public SimpleStringProperty endTimeProperty(){
		return endTime;
	}
	
	public String getLatestExecuteTime(){
		return latestExecuteTime.get();
	}
	
	public void setLatestExecuteTime(Date time){
		if(time!=null)
			this.latestExecuteTime.set(format.format(time));
	}
	
	public SimpleStringProperty latestExecuteTimeProperty(){
		return this.latestExecuteTime;
	}
	
	public String getRoomOrder(){
		return this.roomOrder.get();
	}
	
	public void setRoomOrder(String roomorder){
		this.roomOrder.set(roomorder);
	}
	
	public SimpleStringProperty roomOrderProperty(){
		return this.roomOrder;
	}
	
	public String getRoomType(){
		return this.roomtype.get();
	}
	
	public void setRoomType(String roomtype){
		this.roomtype.set(roomtype);
	}
	
	public SimpleStringProperty roomtypeProperty(){
		return this.roomtype;
	}
	
	public String getRoomNumber(){
		return this.roomnumber.get();
	}
	
	public void setRoomNumber(String roomnumber){
		this.roomnumber.set(roomnumber);
	}
	
	public SimpleStringProperty roomnumberProperty(){
		return this.roomnumber;
	}
	
	
	public String getPrice(){
		return this.price.get();
	}
	
	public void setPrice(int price){
		this.price.set(price+"");
	}
	
	public SimpleStringProperty priceProperty(){
		return this.price;
	}
	
	public String getNumOfPeople(){
		return this.numOfPeople.get();
	}
	
	public void setNumOfPeople(int num){
		this.numOfPeople.set(num+"");
	}
	
	public SimpleStringProperty numOfPeopleProperty(){
		return this.numOfPeople;
	}
	
	public String getHaveChild(){
		return this.haveChild.get();
	}
	
	public void setHaveChild(boolean havechild){
		this.haveChild.set(havechild?"是":"否");
	}
	
	public SimpleStringProperty haveChildProperty(){
		return this.haveChild;
	}
	
	public String getOverTime(){
		return this.overTime.get();
	}
	
	public void setOverTime(Date now){
		try {
			if (now.before(format.parse(latestExecuteTime.get()))) {
				this.overTime.set("未逾期");
			}
			else if (this.isExecute.get().equals(ISEXECUTE)) {
				this.overTime.set("已入住");
			}
			else{
				long overTime;
				overTime = (now.getTime() - format.parse(latestExecuteTime.get()).getTime())/(1000*3600);
				this.overTime.set(overTime+"");
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public SimpleStringProperty overTimeProperty(){
		return this.overTime;
	}
	
	public String getPunishCredit(){
		return this.punishCredit.get();
	}
	
	public void setPunishCredit(int punishCredit){
		this.punishCredit.set(punishCredit+"");
	}
	
	public SimpleStringProperty punishCreditProperty(){
		return this.punishCredit;
	}
	
	public String getPredictLeaveTime(){
		return predictLeaveTime.get();
	}
	
	public void setPredictLeaveTime(Date time){
		if (time!=null) {
			predictLeaveTime.set(format.format(time));
		}
	}
	
	public SimpleStringProperty predictLeaveTimeProperty(){
		return predictLeaveTime;
	}
	
}
