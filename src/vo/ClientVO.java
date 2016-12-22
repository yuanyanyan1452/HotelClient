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
	boolean logged;

	
	public ClientVO(int clientid, String client_name, String contact, int credit, ArrayList<String> credit_record,
			VIPInfo info,String username,String password) {
		super();
		this.clientid = clientid;
		this.client_name = client_name;
		this.contact = contact;
		this.credit = credit;
		this.credit_record = credit_record;
		this.info = info;
		this.username=username;
		this.password=password;
		this.logged=false;
	}

	public int getclientid() {
		return clientid;
	}

	public void setclientid(int clientid) {
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

	public String getclient_name() {
		return client_name;
	}

	public void setclient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getcontact() {
		return contact;
	}

	public void setcontact(String contact) {
		this.contact = contact;
	}

	public int getcredit() {
		return credit;
	}

	public void setcredit(int credit) {
		this.credit = credit;
	}

	public ArrayList<String> getcredit_record() {
		return credit_record;
	}

	public void setcredit_record(ArrayList<String> credit_record) {
		this.credit_record = credit_record;
	}

	public VIPInfo getvipinfo() {
		return info;
	}

	public void setvipinfo(VIPInfo info) {
		this.info = info;
	}
	
	public boolean getlogged() {
		return logged;
	}

	public void setlogged(boolean logged) {
		this.logged = logged;
	}

}
