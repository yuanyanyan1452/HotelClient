package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.Hotel;
import objects.HotelWorker;
import objects.ResultMessage;
import vo.AccommodationVO;
import vo.EvaluationVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.RoomVO;

public interface HotelBLService extends Remote{
	// 提供给界面调用的接口
	
	public ResultMessage hotelworker_login(String username,String password) throws RemoteException;
	
	public HotelWorkerVO hotelworker_getvo(String username)throws RemoteException;
	
	public ResultMessage hotelworker_change_password(String username,String oldpassword,String newpassword)throws RemoteException;
	
	/**
	 * @param hotelid
	 * @return 酒店查看详细信息
	 */
	public HotelVO hotel_checkInfo(int hotelid)throws RemoteException;

	/**
	 * @param vo
	 * @return 酒店更新详细信息
	 */
	public ResultMessage hotel_updateInfo(HotelVO vo)throws RemoteException;

	/**
	 * @param room
	 * @return 酒店录入可用房间
	 */
	public ResultMessage hotel_importRoom(RoomVO room)throws RemoteException;

	/**
	 * @param info
	 * @return 酒店更新入住信息
	 */
	public ResultMessage hotel_updateAccomodation(AccommodationVO info,int orderid)throws RemoteException;
	/**
	 * @param location
	 * @return 客户获取对应地址的酒店列表
	 */
	public ArrayList<HotelVO> searchHotelBylocation(String address,String business_address)throws RemoteException;

	/**
	 * @param hotelname
	 * @return 符合对应酒店名的酒店列表
	 */
	public ArrayList<HotelVO> searchHotelByname(ArrayList<HotelVO> list,String hotelname)throws RemoteException;

	/**
	 * @param type
	 * @return 有符合对应房间类型的酒店列表
	 */
	public ArrayList<HotelVO> searchHotelByroom(ArrayList<HotelVO> list,String type)throws RemoteException;

	/**
	 * @param lowprice
	 * @param highprice
	 * @return 有符合对应价格区间的房间的酒店列表
	 */
	public ArrayList<HotelVO> searchHotelByprice(ArrayList<HotelVO> list,int lowprice, int highprice)throws RemoteException;

	/**
	 * @param inTime
	 * @param leaveTime
	 * @return 有符合对应时间段的房间的酒店列表
	 */
	public ArrayList<HotelVO> searchHotelBytime(ArrayList<HotelVO> list,String inTime, String leaveTime)throws RemoteException;

	/**
	 * @param star
	 * @return 符合对应星级的酒店列表
	 */
	public ArrayList<HotelVO> searchHotelBystar(ArrayList<HotelVO> list,String star)throws RemoteException;

	/**
	 * @param lowscore
	 * @param highscore
	 * @return 符合对应评分区间的酒店列表
	 */
	public ArrayList<HotelVO> searchHotelByscore(ArrayList<HotelVO> list,double lowscore, double highscore)throws RemoteException;
	// 提供给同层调用的接口
	/**
	 * @param hotelid
	 * @return 返回对应酒店
	 */
	public Hotel searchHotel(int hotelid)throws RemoteException;

	/**
	 * @param clientid
	 * @return 返回对应客户预定过的酒店列表
	 */
	public ArrayList<Hotel> previousHotel(int clientid)throws RemoteException;

	/**
	 * @param hotel
	 * @return 添加酒店
	 */
	public ResultMessage addHotel(Hotel hotel)throws RemoteException;

	/**
	 * @param worker
	 * @return 添加酒店工作人员
	 */
	public ResultMessage addHotelWorker(HotelWorker worker)throws RemoteException;

	/**
	 * @param hotelid
	 * @return 返回对应酒店的工作人员
	 */
	public HotelWorker searchHotelWorker(int hotelid)throws RemoteException;

	/**
	 * @param hotelid
	 * @param worker
	 * @return 更新对应酒店的工作人员信息
	 */
	public ResultMessage updateHotelWokerInfo(HotelWorker worker)throws RemoteException;
	
	//评价酒店
	public ResultMessage evalutehotel(EvaluationVO e, int clientid,int hotelid) throws RemoteException;
}
