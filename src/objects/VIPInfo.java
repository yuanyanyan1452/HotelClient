package objects;

import java.io.Serializable;

public class VIPInfo implements Serializable{
	public enum VIPType {
		NORMAL, Enterprise
	};

	VIPType type;
	String info;

	public VIPInfo(VIPType type, String info) {
		this.type = type;
		this.info = info;
	}

	public VIPInfo() {
		// TODO Auto-generated constructor stub
	}

	public VIPType getType() {
		return type;
	}

	public void setType(VIPType type) {
		this.type = type;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
