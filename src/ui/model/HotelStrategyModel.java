package ui.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HotelStrategyModel {
	private final SimpleIntegerProperty id;
	private final SimpleIntegerProperty hotelid;
	private final SimpleStringProperty name;
	private final SimpleStringProperty startTime;
	private final SimpleStringProperty endTime;
	private final SimpleStringProperty discount;
	private final SimpleStringProperty condition;
	private final SimpleStringProperty superpositon;

	public HotelStrategyModel(int id, int hotelid, String name, String startTime, String endTime, String discount,
			String condition, boolean superposition) {
		this.id = new SimpleIntegerProperty(id);
		this.hotelid = new SimpleIntegerProperty(hotelid);
		this.name = new SimpleStringProperty(name);
		this.startTime = new SimpleStringProperty(startTime);
		this.endTime = new SimpleStringProperty(endTime);
		this.discount = new SimpleStringProperty(discount);
		this.condition = new SimpleStringProperty(condition);
		this.superpositon = new SimpleStringProperty(superposition?"是":"否");
	}

	public int getID() {
		return id.get();
	}

	public void setID(int id) {
		this.id.set(id);
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public int getHotelID() {
		return hotelid.get();
	}

	public void setHotelID(int hotelid) {
		this.hotelid.set(hotelid);
	}

	public SimpleIntegerProperty hotelidProperty() {
		return hotelid;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public String getStartTime() {
		return startTime.get();
	}

	public void setStartTime(String startTime) {
		this.startTime.set(startTime);
	}

	public SimpleStringProperty startTimeProperty() {
		return startTime;
	}

	public String getEndTime() {
		return endTime.get();
	}

	public void setEndTime(String endTime) {
		this.endTime.set(endTime);
	}

	public SimpleStringProperty endTimeProperty() {
		return endTime;
	}

	public String getDiscount() {
		return discount.get();
	}

	public void setDiscount(String discount) {
		this.discount.set(discount);
	}

	public SimpleStringProperty discountProperty() {
		return discount;
	}

	public String getCondition() {
		return condition.get();
	}

	public void setCondition(String condition) {
		this.condition.set(condition);
	}

	public SimpleStringProperty conditionProperty() {
		return condition;
	}

	public String getSuperposition() {
		return superpositon.get();
	}

	public void setSuperposition(String superposition) {
		superpositon.set(superposition);
	}

	public SimpleStringProperty superpostionProperty() {
		return superpositon;
	}
}
