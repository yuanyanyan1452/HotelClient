package ui.model;

import javafx.beans.property.SimpleStringProperty;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;

public class ClientModel {
	private final SimpleStringProperty clientid;
	private final SimpleStringProperty clientName;
	private final SimpleStringProperty contact;
	private final SimpleStringProperty vipType;
	private final SimpleStringProperty vipInfo;
	private final SimpleStringProperty credit;
//	private final SimpleStringProperty username; 
	public ClientModel() {
		clientid = new SimpleStringProperty();
		clientName  = new SimpleStringProperty();
		contact = new SimpleStringProperty();
		vipType = new SimpleStringProperty();
		vipInfo = new SimpleStringProperty();
		credit = new SimpleStringProperty();
//		username = new SimpleStringProperty();
	}

	public String getID() {
		return clientid.get();
	}

	public void setID(int clientid) {
		this.clientid.set(clientid + "");
	}

	public SimpleStringProperty iDProperty() {
		return clientid;
	}

	public String getName() {
		return clientName.get();
	}

	public void setName(String name) {
		this.clientName.set(name);
	}

	public SimpleStringProperty nameProperty() {
		return clientName;
	}

	public String getContact() {
		return contact.get();
	}

	public void setContact(String contact) {
		this.contact.set(contact);
	}

	public SimpleStringProperty contactProperty() {
		return contact;
	}

	public String getVIPType() {
		return vipType.get();
	}

	public void setVIPtype(String type) {
		vipType.set(type);
	}

	public SimpleStringProperty vipTypeProperty() {
		return vipType;
	}

	public String getVipInfo(){
		return vipInfo.get();
	}
	
	public void setVipInfo(String vipInfo){
		this.vipInfo.set(vipInfo);
	}
	
	public SimpleStringProperty vipInfoProperty(){
		return vipInfo;
	}
	
	public String getCredit() {
		return credit.get();
	}

	public void setCredit(int credit) {
		this.credit.set(credit + "");
	}

	public SimpleStringProperty creditProperty() {
		return credit;
	}

//	public String getUsername(){
//		return username.get();
//	}
//	
//	public void setUsername(String username){
//		this.username.set(username);
//	}
}
