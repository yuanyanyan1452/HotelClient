package objects;

import java.util.ArrayList;

import po.ClientPO;
import po.HotelPO;
import po.HotelStrategyPO;
import po.HotelWorkerPO;
import po.OrderPO;
import po.RoomOrderPO;
import po.RoomPO;
import po.WebManagerPO;
import po.WebMarketPO;
import po.WebStrategyPO;
import vo.ClientVO;
import vo.HotelStrategyVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.RoomVO;
import vo.WebManagerVO;
import vo.WebMarketVO;
import vo.WebStrategyVO;

public class ObjectChange {
	public ClientPO changetoclientpo(Client client){
		ClientPO po=new ClientPO();
		po.setclientid(client.clientid);
		po.setclient_name(client.client_name);
		po.setcontact(client.contact);
		po.setcredit(client.credit);
		po.setcredit_record(client.credit_record);
		po.setvipinfo(client.info);
		po.setusername(client.username);
		po.setpassword(client.password);
		return po;
	}
	
	public ClientVO changetoclientvo(Client client){
		ClientVO vo=new ClientVO(0,null,null,0,null,null,null,null);
		vo.setclientid(client.clientid);
		vo.setclient_name(client.client_name);
		vo.setcontact(client.contact);
		vo.setcredit(client.credit);
		vo.setcredit_record(client.credit_record);
		vo.setvipinfo(client.info);
		vo.setusername(client.username);
		vo.setpassword(client.password);
		return vo;
	}
	
	public HotelPO changetohotelpo(Hotel hotel){
		HotelPO po=new HotelPO();
		po.setid(hotel.hotelid);
		po.setname(hotel.name);
		po.setaddress(hotel.address);
		po.setbussiness_address(hotel.bussiness_address);
		po.setintroduction(hotel.introduction);
		po.setservice(hotel.service);
		po.setstar(hotel.star);
		po.setscore(hotel.score);
		po.sethotel_evaluation(hotel.hotel_evaluation);
		po.setbook_clientid(hotel.book_clientid);
		return po;
	}
	
	public HotelVO changetohotelvo(Hotel hotel){
		HotelVO vo=new HotelVO();
		vo.setid(hotel.hotelid);
		vo.setname(hotel.name);
		vo.setaddress(hotel.address);
		vo.setbussiness_address(hotel.bussiness_address);
		vo.setintroduction(hotel.introduction);
		vo.setservice(hotel.service);
		vo.setstar(hotel.star);
		vo.setscore(hotel.score);
		vo.sethotel_evaluation(hotel.hotel_evaluation);
		vo.setbook_clientid(hotel.book_clientid);
		return vo;
	}
	
	public HotelStrategyPO changetohotelstrategypo(HotelStrategy hotelstrategy){
		HotelStrategyPO po=new HotelStrategyPO();
		po.setid(hotelstrategy.hsid);
		po.sethotelid(hotelstrategy.hotelid);
		po.setname(hotelstrategy.name);
		po.setcondition(hotelstrategy.condition);
		po.setstart_time(hotelstrategy.start_time);
		po.setend_time(hotelstrategy.end_time);
		po.setexecuteway(hotelstrategy.executeway);
		po.setsuperposition(hotelstrategy.superposition);
		return po;
	}
	
	public HotelStrategyVO changetohotelstrategyvo(HotelStrategy hotelstrategy){
		HotelStrategyVO vo=new HotelStrategyVO();
		vo.setid(hotelstrategy.hsid);
		vo.sethotelid(hotelstrategy.hotelid);
		vo.setname(hotelstrategy.name);
		vo.setcondition(hotelstrategy.condition);
		vo.setstart_time(hotelstrategy.start_time);
		vo.setend_time(hotelstrategy.end_time);
		vo.setexecuteway(hotelstrategy.executeway);
		vo.setsuperposition(hotelstrategy.superposition);
		return vo;
	}
	
	public HotelWorkerPO changetohotelworkerpo(HotelWorker worker){
		HotelWorkerPO po=new HotelWorkerPO();
		po.sethotelid(worker.hotelid);
		po.setname(worker.name);
		po.setcontact(worker.contact);
		po.setusername(worker.username);
		po.setpassword(worker.password);
		return po;
	}
	
	public HotelWorkerVO changetohotelworkervo(HotelWorker worker){
		HotelWorkerVO vo=new HotelWorkerVO();
		vo.sethotelid(worker.hotelid);
		vo.setname(worker.name);
		vo.setcontact(worker.contact);
		vo.setusername(worker.username);
		vo.setpassword(worker.password);
		return vo;
	}
	
	public WebManagerPO changetowebmanagerpo(Manage manager){
		WebManagerPO po=new WebManagerPO();
		po.setwebmanagerid(manager.webmanagerid);
		po.setname(manager.name);
		po.setcontact(manager.contact);
		po.setusername(manager.username);
		po.setpassword(manager.password);
		return po;
	}
	
	public WebManagerVO changetowebmanagervo(Manage manager){
		WebManagerVO vo=new WebManagerVO();
		vo.setwebmanagerid(manager.webmanagerid);
		vo.setname(manager.name);
		vo.setcontact(manager.contact);
		vo.setusername(manager.username);
		vo.setpassword(manager.password);
		return vo;
	}
	
	public RoomOrderPO changetoroomorderpo(RoomOrder roomorder){
		RoomOrderPO po = new RoomOrderPO();
		po.setroom_number(roomorder.room_number);
		po.setroom_type(roomorder.room_type);
		return po;
	}
	
	public RoomOrderVO changetoroomordervo(RoomOrder roomorder){
		RoomOrderVO vo = new RoomOrderVO();
		vo.setroom_number(roomorder.room_number);
		vo.setroom_type(roomorder.room_type);
		return vo;
		
	}
	
	public OrderPO changetoorderpo(Order order){
		OrderPO po=new OrderPO();
		po.setid(order.id);
		po.setclientid(order.clientid);
		po.sethotelid(order.hotelid);
		po.setstate(order.state);
		po.setcancel_time(order.cancel_time);
		po.setexecute(order.execute);
		po.setstart_time(order.start_time);
		po.setend_time(order.end_time);
		po.setlatest_execute_time(order.latest_execute_time);
		ArrayList<RoomOrderPO> list = new ArrayList<RoomOrderPO>();
		for(int i=0;i<order.room_order.size();i++){
			list.add(changetoroomorderpo(order.room_order.get(i)));
		}
		po.setroom_order(list);
		po.setprice(order.price);
		po.setexpect_number_of_people(order.expect_number_of_people);
		po.sethave_child(order.havechild);
		return po;
	}
	
	public OrderVO changetoordervo(Order order){
		OrderVO vo=new OrderVO();
		vo.setid(order.id);
		vo.setclientid(order.clientid);
		vo.sethotelid(order.hotelid);
		vo.setstate(order.state);
		vo.setcancel_time(order.cancel_time);
		vo.setexecute(order.execute);
		vo.setstart_time(order.start_time);
		vo.setend_time(order.end_time);
		vo.setlatest_execute_time(order.latest_execute_time);
		//po转vo
		//vo.setroom_order(order.room_order);
		ArrayList<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		for(int i=0;i<order.room_order.size();i++){
			list.add(changetoroomordervo(order.room_order.get(i)));
		}
		vo.setroom_order(list);
		vo.setprice(order.price);
		vo.setexpect_number_of_people(order.expect_number_of_people);
		vo.sethave_child(order.havechild);
		return vo;
	}
	
	public RoomPO changetoroompo(Room room){
		RoomPO po=new RoomPO();
		po.setid(room.id);
		po.sethotelid(room.hotelid);
		po.setroom_type(room.room_type);
		po.settotal_num(room.total_num);
		po.setavailable_num(room.available_num);
		po.setprice(room.price);
		return po;
	}
	
	public RoomVO changetoroomvo(Room room){
		RoomVO vo=new RoomVO();
		vo.setid(room.id);
		vo.sethotelid(room.hotelid);
		vo.setroom_type(room.room_type);
		vo.settotal_num(room.total_num);
		vo.setavailable_num(room.available_num);
		vo.setprice(room.price);
		return vo;
	}
	
	public WebMarketPO changetowebmarketpo(WebMarket market){
		WebMarketPO po=new WebMarketPO();
		po.setwebmarketid(market.webmarketid);
		po.setname(market.name);
		po.setcontact(market.contact);
		po.setusername(market.username);
		po.setpassword(market.password);
		return po;
	}
	
	public WebMarketVO changetowebmarketvo(WebMarket market){
		WebMarketVO vo=new WebMarketVO();
		vo.setwebmarketid(market.webmarketid);
		vo.setname(market.name);
		vo.setcontact(market.contact);
		vo.setusername(market.username);
		vo.setpassword(market.password);
		return vo;
	}
	
	public WebStrategyPO changetowebstrategypo(WebStrategy webstrategy){
		WebStrategyPO po=new WebStrategyPO();
		po.setid(webstrategy.wsid);
		po.setname(webstrategy.name);
		po.setcondition(webstrategy.condition);
		po.setstart_time(webstrategy.start_time);
		po.setend_time(webstrategy.end_time);
		po.setexecuteway(webstrategy.executeway);
		po.setsuperposition(webstrategy.superposition);
		return po;
	}
	
	public WebStrategyVO changetowebstrategyvo(WebStrategy webstrategy){
		WebStrategyVO vo=new WebStrategyVO();
		vo.setid(webstrategy.wsid);
		vo.setname(webstrategy.name);
		vo.setcondition(webstrategy.condition);
		vo.setstart_time(webstrategy.start_time);
		vo.setend_time(webstrategy.end_time);
		vo.setexecuteway(webstrategy.executeway);
		vo.setsuperposition(webstrategy.superposition);
		return vo;
	}
}
