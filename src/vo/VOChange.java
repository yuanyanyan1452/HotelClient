package vo;

import java.io.Serializable;
import java.util.ArrayList;

import objects.Client;
import objects.Hotel;
import objects.HotelStrategy;
import objects.HotelWorker;
import objects.Manage;
import objects.Order;
import objects.RoomOrder;
import objects.VIPInfo;
import objects.WebMarket;
import objects.WebStrategy;
import vo.ClientVO;
import vo.HotelStrategyVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.RoomVO;
import vo.VIPInfoVO;
import vo.VIPInfoVO.VIPType;
import vo.WebManagerVO;
import vo.WebMarketVO;
import vo.WebStrategyVO;

public class VOChange implements Serializable{

	public static Client clientvo_to_client(ClientVO clientvo) {
		Client client = new Client();
		client.setclientid(clientvo.getclientid());
		client.setusername(clientvo.getusername());
		client.setpassword(clientvo.getpassword());
		client.setclient_name(clientvo.getclient_name());
		client.setcontact(clientvo.getcontact());
		client.setcredit(clientvo.getcredit());
		client.setcredit_record(clientvo.getcredit_record());
		client.setvipinfo(clientvo.getvipinfo());
		client.setlogged(clientvo.getlogged());
		return client;
	}

	public static  Hotel hotelvo_to_hotel(HotelVO hotelvo) {
		Hotel hotel = new Hotel();
		hotel.setid(hotelvo.getid());
		hotel.setname(hotelvo.getname());
		hotel.setaddress(hotelvo.getaddress());
		hotel.setbussiness_address(hotelvo.getbussiness_address());
		hotel.setintroduction(hotelvo.getintroduction());
		hotel.setservice(hotelvo.getservice());
		hotel.setstar(hotelvo.getstar());
		hotel.setevaluation(hotelvo.gethotel_evaluation());
		hotel.setbook_clientid(hotelvo.getbook_clientid());
		hotel.setbook_clientid(hotelvo.getbook_clientid());
		// 评价,评分没写
		return hotel;

	}

	public static Order ordervo_to_order(OrderVO ordervo) {
		Order order = new Order();
		order.setid(ordervo.getid());
		order.setclientid(ordervo.getclientid());
		order.sethotelid(ordervo.gethotelid());
		order.setstate(ordervo.getstate());
		order.setcancel_time(ordervo.getcancel_time());
		order.setexecute(ordervo.getexecute());
		order.setstart_time(ordervo.getstart_time());
		order.setend_time(ordervo.getend_time());
		order.setlatest_execute_time(ordervo.getlatest_execute_time());
		ArrayList<RoomOrder> roomorder_list = new ArrayList<RoomOrder>();
		ArrayList<RoomOrderVO> roomordervo_list = ordervo.getroom_order();
		for (int i = 0; i < roomordervo_list.size(); i++) {
			RoomOrder roomorder = roomordervo_to_roomorder(roomordervo_list.get(i));
			roomorder_list.add(roomorder);
		}
		order.setroom_order(roomorder_list);
		order.setprice(ordervo.getprice());
		order.setexpect_number_of_people(ordervo.getexpect_number_of_people());
		order.sethave_child(ordervo.gethave_child());
		order.setevaluation(ordervo.getevaluation());

		return order;
	}

	public static HotelStrategy hotelstrategyvo_to_hotelstrategy(HotelStrategyVO vo) {
		HotelStrategy hs = new HotelStrategy();
		hs.setid(vo.getid());
		hs.sethotelid(vo.gethotelid());
		hs.setname(vo.getname());
		hs.setcondition(vo.getcondition());
		hs.setstart_time(vo.getstart_time());
		hs.setend_time(vo.getend_time());
		hs.setexecuteway(vo.getexecuteway());
		hs.setsuperposition(vo.getsuperposition());
		return hs;
	}

	public static WebStrategy webstrategyvo_to_webstrategy(WebStrategyVO vo) {
		WebStrategy ws = new WebStrategy();
		ws.setid(vo.getid());
		ws.setname(vo.getname());
		ws.setcondition(vo.getcondition());
		ws.setstart_time(vo.getstart_time());
		ws.setend_time(vo.getend_time());
		ws.setexecuteway(vo.getexecuteway());
		ws.setsuperposition(vo.getsuperposition());
		return ws;
	}

	public static HotelWorker hotelworkervo_to_hotelworker(HotelWorkerVO vo) {
		HotelWorker hw = new HotelWorker();
		hw.sethotelid(vo.gethotelid());
		hw.setusername(vo.getusername());
		hw.setpassword(vo.getpassword());
		hw.setname(vo.getname());
		hw.setcontact(vo.getcontact());
		hw.setlogged(vo.getlogged());
		return hw;
	}

	public static Manage managervo_to_manager(WebManagerVO vo) {
		Manage wm = new Manage();
		wm.setwebmanagerid(vo.getwebmanagerid());
		wm.setusername(vo.getusername());
		wm.setpassword(vo.getpassword());
		wm.setname(vo.getname());
		wm.setcontact(vo.getcontact());
		wm.setlogged(vo.getlogged());
		return wm;
	}

	public static WebMarket marketvo_to_market(WebMarketVO vo) {
		WebMarket wm = new WebMarket();
		wm.setwebmarketid(vo.getwebmarketid());
		wm.setusername(vo.getusername());
		wm.setpassword(vo.getpassword());
		wm.setname(vo.getname());
		wm.setcontact(vo.getcontact());
		wm.setlogged(vo.getlogged());
		return wm;
	}

	public static RoomOrder roomordervo_to_roomorder(RoomOrderVO vo) {
		// TODO Auto-generated method stub
		RoomOrder order = new RoomOrder();
		order.setroom_type(vo.getroom_type());
		order.setroom_number(vo.getroom_number());
		return order;
	}

	public static VIPInfo vipinfovo_tovioinfo(VIPInfoVO vo) {
		VIPInfo vipinfo = new VIPInfo();
		if (vo.getType() == VIPType.Enterprise) {
			vipinfo.setType(objects.VIPInfo.VIPType.Enterprise);
		} else {
			vipinfo.setType(objects.VIPInfo.VIPType.NORMAL);
		}
		vipinfo.setInfo(vo.getInfo());
		return vipinfo;
	}

}
