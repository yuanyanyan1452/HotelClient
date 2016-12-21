package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.ResultMessage;
import vo.EvaluationVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.RoomVO;

public interface HotelBLService extends Remote{
	// 提供给界面调用的接口
	/**
	 * 酒店工作人员通过用户名密码登录
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage hotelworker_login(String username,String password) throws RemoteException;
	
	/**
	 * 通过用户名获得酒店工作人员的信息
	 * @param username
	 * @return
	 * @throws RemoteException
	 */
	public HotelWorkerVO hotelworker_getvo(String username)throws RemoteException;
	
	/**
	 * 酒店工作人员通过用户名，旧密码，新密码更改密码
	 * @param username
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage hotelworker_change_password(String username,String oldpassword,String newpassword)throws RemoteException;
	
	/**
	 * 通过酒店id获得酒店信息
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public HotelVO hotel_getInfo(int hotelid)throws RemoteException;

	/**
	 * 更新酒店信息
	 * @param hotelvo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage hotel_updateInfo(HotelVO hotelvo)throws RemoteException;

	/**
	 * 酒店录入可用房间
	 * @param room
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage hotel_importRoom(RoomVO room)throws RemoteException;

	/**
	 * 酒店更新可用房间信息
	 * @param room
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage hotel_updateRoom(RoomVO room)throws RemoteException;
	
	/**
	 * 通过地址和商圈搜索酒店
	 * @param address
	 * @param business_address
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> searchHotelBylocation(String address,String business_address)throws RemoteException;

	/**
	 * 通过酒店名搜索酒店
	 * @param list
	 * @param hotelname
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> searchHotelByname(ArrayList<HotelVO> list,String hotelname)throws RemoteException;

	/**
	 * 通过酒店房间搜索酒店
	 * @param list
	 * @param type
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> searchHotelByroom(ArrayList<HotelVO> list,String type)throws RemoteException;

	/**
	 * 通过价格搜索酒店
	 * @param list
	 * @param lowprice
	 * @param highprice
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> searchHotelByprice(ArrayList<HotelVO> list,int lowprice, int highprice)throws RemoteException;

	/**
	 * 通过空房期间搜索酒店
	 * @param list
	 * @param inTime
	 * @param leaveTime
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> searchHotelBytime(ArrayList<HotelVO> list,String inTime, String leaveTime)throws RemoteException;

	/**
	 * 通过星级搜索酒店
	 * @param list
	 * @param star
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> searchHotelBystar(ArrayList<HotelVO> list,String star)throws RemoteException;

	/**
	 * 通过评分搜索酒店
	 * @param list
	 * @param lowscore
	 * @param highscore
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> searchHotelByscore(ArrayList<HotelVO> list,double lowscore, double highscore)throws RemoteException;
	
	/**
	 * 搜索酒店时只搜索自己预订过的酒店
	 * @param hotelvolist
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> searchpreviousHotelList(ArrayList<HotelVO> hotelvolist,int clientid)throws RemoteException;
	
	/**
	 * 客户评价酒店
	 * @param e
	 * @param clientid
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage evalutehotel(EvaluationVO e, int clientid,int hotelid) throws RemoteException;
	
	/**
	 * 通过酒店id获得对应酒店的所有房间
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<RoomVO> getallroom(int hotelid)throws RemoteException;
	
	// 提供给同层调用的接口
//	/**
//	 * 通过酒店id搜索酒店
//	 * @param hotelid
//	 * @return
//	 * @throws RemoteException
//	 */
//	public Hotel searchHotel(int hotelid)throws RemoteException;
//
//	/**
//	 * 通过客户id获得客户之前预订过的酒店列表
//	 * @param clientid
//	 * @return
//	 * @throws RemoteException
//	 */
//	public ArrayList<Hotel> getpreviousHotel(int clientid)throws RemoteException;
//
//	/**
//	 * 添加酒店
//	 * @param hotel
//	 * @return
//	 * @throws RemoteException
//	 */
//	public ResultMessage addHotel(Hotel hotel)throws RemoteException;
//
//	/**
//	 * 添加酒店工作人员
//	 * @param worker
//	 * @return
//	 * @throws RemoteException
//	 */
//	public ResultMessage addHotelWorker(HotelWorker worker)throws RemoteException;
//
//	/**
//	 * 根据酒店id获得对应酒店的酒店工作人员
//	 * @param hotelid
//	 * @return
//	 * @throws RemoteException
//	 */
//	public HotelWorker searchHotelWorker(int hotelid)throws RemoteException;
//
//	/**
//	 * 更新酒店工作人员信息
//	 * @param worker
//	 * @return
//	 * @throws RemoteException
//	 */
//	public ResultMessage updateHotelWokerInfo(HotelWorker worker)throws RemoteException;
//	

}
