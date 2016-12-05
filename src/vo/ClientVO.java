package vo;

import java.io.Serializable;
import java.util.ArrayList;

import objects.VIPInfo;

public class ClientVO implements Serializable {
	int clientid;
	String username;
	String password;
	String client_name;
	String contact;
	int credit;
	ArrayList<String> credit_record;
	VIPInfo info;

	public ClientVO(int clientid,String username,String password, String client_name, String contact, int credit, ArrayList<String> credit_record,
			VIPInfo info) {
		super();
		this.clientid = clientid;
		this.username=username;
		this.password=password;
		this.client_name = client_name;
		this.contact = contact;
		this.credit = credit;
		this.credit_record = credit_record;
		this.info = info;
	}
	public ClientVO(int clientid, String client_name, String contact, int credit, ArrayList<String> credit_record,
			VIPInfo info) {
		super();
		this.clientid = clientid;
		
		this.client_name = client_name;
		this.contact = contact;
		this.credit = credit;
		this.credit_record = credit_record;
		this.info = info;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	
	public String getusername(){
		return username;
	}
	
	public void setusername(String um){
		username=um;;
	}
	
	public String getpassword(){
		return password;
	}
	
	public void setpassword(String pass){
		password=pass;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public ArrayList<String> getCredit_record() {
		return credit_record;
	}

	public void setCredit_record(ArrayList<String> credit_record) {
		this.credit_record = credit_record;
	}

	public VIPInfo getVIPInfo() {
		return info;
	}

	public void setVIPInfo(VIPInfo info) {
		this.info = info;
	}
	
	

}
