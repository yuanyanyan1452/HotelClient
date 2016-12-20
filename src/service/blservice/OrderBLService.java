package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import objects.ResultMessage;
import vo.AccommodationVO;
import vo.OrderVO;
import vo.RoomOrderVO;

public interface OrderBLService extends Remote{
	// 提供给界面调用的接口
	/**
	 * @param clientid
	 * @return 客户浏览订单
	 */
	public ArrayList<OrderVO> order_client_browse(int clientid)throws RemoteException;

	/**
	 * @param clientid
	 * @param state
	 * @return 客户根据订单状态浏览
	 */
	public ArrayList<OrderVO> order_client_browse(int clientid, String state)throws RemoteException;

	/**
	 * @param clientid
	 * @param isExecute
	 * @return 客户根据执行情况浏览
	 */
	public ArrayList<OrderVO> order_client_browse(int clientid, boolean isExecute)throws RemoteException;

	/**
	 * @param hotelid
	 * @return 酒店浏览订单
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid)throws RemoteException;

	/**
	 * @param hotelid
	 * @param state
	 * @return 酒店根据订单状态浏览
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, String state)throws RemoteException;

	/**
	 * @param hotelid
	 * @param isExecute
	 * @return 酒店根据执行情况浏览
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, boolean isExecute)throws RemoteException;

	/**
	 * @param clientid
	 * @param orderid
	 * @return 客户撤销订单
	 */
	public ResultMessage order_client_cancel( int orderid)throws RemoteException;

	/**
	 * @param vo
	 * @return 客户生成订单
	 */
	public ResultMessage order_client_generate(OrderVO vo)throws RemoteException;

	/**
	 * @param orderid
	 * @return 酒店执行订单
	 */
	public ResultMessage order_hotel_execute(int orderid)throws RemoteException;

	/**
	 * @return 网站营销人员浏览未执行订单
	 */
	public ArrayList<OrderVO> order_market_browseUnfilled()throws RemoteException;

	/**
	 * @param orderid
	 * @return 网站营销人员撤销异常订单
	 */
	public ResultMessage order_market_cancelAbnormal(int orderid)throws RemoteException;

	/**
	 * @param type
	 * @param num
	 * @return 计算订单总价（无促销策略）
	 */
	public double calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid)throws RemoteException;

	/**
	 * @param type
	 * @param num
	 * @param list1
	 * @param list2
	 * @return 计算订单总价（有促销策略）
	 */
	public double calculateTotalwithStrategy(ArrayList<RoomOrderVO> roomlist, int hotelid,int clientid)throws RemoteException;

	// 提供给同层调用的接口
	/**
	 * @param leaveTime
	 * @return 更新订单实际离开时间
	 */
	public ResultMessage updateActualLeaveTime(int orderid, Date leaveTime)throws RemoteException;
	
	//入住时更新订单信息
	public ResultMessage updateorder(AccommodationVO info,int orderid)throws RemoteException;
	
	//通过id寻找订单
	public OrderVO order_findbyid(int orderid)throws RemoteException;
	
	public ResultMessage order_checkout(int orderid)throws RemoteException;
	
	public ResultMessage offline_checkin(int hotelid,ArrayList<RoomOrderVO> room_order)throws RemoteException;
	
	public ResultMessage offline_checkout(int hotelid,ArrayList<RoomOrderVO> room_order)throws RemoteException;
	
	public ArrayList<OrderVO> get_client_hotel_order(int clientid,int hotelid) throws RemoteException;
}
