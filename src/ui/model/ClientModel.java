package ui.model;

import javafx.beans.property.SimpleStringProperty;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;

public class ClientModel {
	private final SimpleStringProperty clientid;
	private final SimpleStringProperty clientName;
	private final SimpleStringProperty contact;
	private final SimpleStringProperty vipinfo;
	private final SimpleStringProperty credit;

	public ClientModel(int clientid, String clientName, String contact, VIPInfo info, int credit) {
		this.clientid = new SimpleStringProperty(clientid + "");
		this.clientName = new SimpleStringProperty(clientName);
		this.contact = new SimpleStringProperty(contact);
		String vipinfo = "非会员";
		if (info != null) {
			if (info.getType().equals(VIPType.NORMAL)) {
				vipinfo = "普通会员";
			} else {
				vipinfo = "企业会员";
			}
			vipinfo += "//";
			vipinfo += info.getInfo();
		}
		this.vipinfo = new SimpleStringProperty(vipinfo);
		this.credit = new SimpleStringProperty(credit + "");
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

	public String getVIPinfo() {
		return vipinfo.get();
	}

	public void setVIPinfo(VIPInfo info) {
		if (info == null) {
			this.vipinfo.set("非会员");
		}
		String type = info.getType() == VIPType.NORMAL ? "普通会员" : "企业会员";
		this.vipinfo.set(type + "//" + info.getInfo());
	}

	public SimpleStringProperty viProperty() {
		return vipinfo;
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

}
