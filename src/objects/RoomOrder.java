package objects;

public class RoomOrder {
	String room_type;
	int room_number;
	
	public RoomOrder(){
		room_type=null;
		room_number=0;
	}
	
	public RoomOrder(String t,int n){
		room_type=t;
		room_number=n;
	}
	
	public void setroom_type(String t){
		room_type=t;
	}
	public String getroom_type(){
		return room_type;
	}
	
	public void setroom_number(int n){
		room_number=n;
	}
	public int getroom_number(){
		return room_number;
	}
}
