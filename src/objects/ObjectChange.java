package objects;

import java.util.ArrayList;

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
		vo.setlogged(client.logged);
		return vo;
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
	
	
	
	public HotelWorkerVO changetohotelworkervo(HotelWorker worker){
		HotelWorkerVO vo=new HotelWorkerVO();
		vo.sethotelid(worker.hotelid);
		vo.setname(worker.name);
		vo.setcontact(worker.contact);
		vo.setusername(worker.username);
		vo.setpassword(worker.password);
		vo.setlogged(worker.logged);
		return vo;
	}
	

	
	public WebManagerVO changetowebmanagervo(Manage manager){
		WebManagerVO vo=new WebManagerVO();
		vo.setwebmanagerid(manager.webmanagerid);
		vo.setname(manager.name);
		vo.setcontact(manager.contact);
		vo.setusername(manager.username);
		vo.setpassword(manager.password);
		vo.setlogged(manager.logged);
		return vo;
	}
	
	
	public RoomOrderVO changetoroomordervo(RoomOrder roomorder){
		RoomOrderVO vo = new RoomOrderVO();
		vo.setroom_number(roomorder.room_number);
		vo.setroom_type(roomorder.room_type);
		return vo;
		
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
		vo.setevaluation(order.evaluation);
		return vo;
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
	
	
	public WebMarketVO changetowebmarketvo(WebMarket market){
		WebMarketVO vo=new WebMarketVO();
		vo.setwebmarketid(market.webmarketid);
		vo.setname(market.name);
		vo.setcontact(market.contact);
		vo.setusername(market.username);
		vo.setpassword(market.password);
		vo.setlogged(market.logged);
		return vo;
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
