package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.ResultMessage;
import vo.AccommodationVO;
import vo.OrderVO;
import vo.RoomOrderVO;

public interface OrderBLService extends Remote{
	
	/**
	 * 通过客户id获得订单列表
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderVO> findorderByClientid(int clientid)throws RemoteException;

	/**
	 * 通过客户id和订单状态获得订单列表
	 * @param clientid
	 * @param state
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderVO> findorderBy_Clientid_State(int clientid, String state)throws RemoteException;

	/**
	 * 通过客户id和订单执行情况获得订单列表
	 * @param clientid
	 * @param isExecute
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderVO> findorderBy_Clientid_Execute(int clientid, boolean isExecute)throws RemoteException;

	/**
	 * 通过酒店id获得订单列表
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderVO> findorderByHotelid(int hotelid)throws RemoteException;

	/**
	 * 通过酒店id和订单状态获得订单列表
	 * @param hotelid
	 * @param state
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderVO> findorderBy_Hotelid_State(int hotelid, String state)throws RemoteException;

	/**
	 * 通过酒店id和订单执行情况获得订单列表
	 * @param hotelid
	 * @param isExecute
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderVO> findorderBy_Hotelid_Execute(int hotelid, boolean isExecute)throws RemoteException;

	/**
	 * 客户撤销订单
	 * @param orderid
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage order_client_cancel( int orderid)throws RemoteException;

	/**
	 * 客户生成订单
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage order_client_generate(OrderVO vo)throws RemoteException;

	/**
	 * 酒店执行订单
	 * @param orderid
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage order_hotel_execute(int orderid)throws RemoteException;

	/**
	 * 网站营销人员浏览未执行订单
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderVO> order_market_browseUnfilled()throws RemoteException;

	/**
	 * 网站营销人员撤销异常订单
	 * @param orderid
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage order_market_cancelAbnormal(int orderid)throws RemoteException;

	/**
	 * 不使用促销策略计算订单总价
	 * @param roomlist
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public double calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid)throws RemoteException;

	/**
	 * 使用促销策略计算订单总价
	 * @param roomlist
	 * @param hotelid
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public double calculateTotalwithStrategy(ArrayList<RoomOrderVO> roomlist, int hotelid,int clientid)throws RemoteException;

	/**
	 * 通过订单id获取订单
	 * @param orderid
	 * @return
	 * @throws RemoteException
	 */
	public OrderVO order_findbyid(int orderid)throws RemoteException;
		
	/**
	 * 线上订单入住
	 * @param info
	 * @param orderid
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage order_checkin(AccommodationVO info,int orderid)throws RemoteException;
	
	/**
	 * 线上订单退房
	 * @param orderid
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage order_checkout(int orderid)throws RemoteException;
	
	/**
	 * 线下入住
	 * @param hotelid
	 * @param room_order
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage offline_checkin(int hotelid,ArrayList<RoomOrderVO> room_order)throws RemoteException;
	
	/**
	 * 线下退房
	 * @param hotelid
	 * @param room_order
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage offline_checkout(int hotelid,ArrayList<RoomOrderVO> room_order)throws RemoteException;
	
	/**
	 * 获得客户在当前酒店的历史订单
	 * @param clientid
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<OrderVO> get_client_hotel_order(int clientid,int hotelid) throws RemoteException;
	
	/**
	 * 获得生成订单时使用的策略名字
	 * @return
	 * @throws RemoteException
	 */
	public String getstrategyname() throws RemoteException;
}
