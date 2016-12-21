package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.ResultMessage;
import vo.ClientVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.WebManagerVO;
import vo.WebMarketVO;

public interface ManageBLService extends Remote {

	/**
	 * 网站管理人员通过用户名密码登录
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage webmanager_login(String username, String password) throws RemoteException;

	/**
	 * 网站营销人员通过用户名密码登录
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage webmarket_login(String username, String password) throws RemoteException;

	/**
	 * 网站营销人员通过用户名，旧密码，新密码更改密码
	 * @param username
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage webmarket_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException;

	/**
	 * 网站管理人员通过用户名，旧密码，新密码更改密码
	 * @param username
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage webmanager_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException;

	/**
	 * 通过用户名获得网站营销人员的信息
	 * @param username
	 * @return
	 * @throws RemoteException
	 */
	public WebMarketVO webmarket_getvo(String username) throws RemoteException;

	/**
	 * 通过用户名获得网站管理人员的信息
	 * @param username
	 * @return
	 * @throws RemoteException
	 */
	public WebManagerVO webmanager_getvo(String username) throws RemoteException;

	/**
	 * 获得所有客户的信息
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<ClientVO> getallclientvo() throws RemoteException;

	/**
	 * 获得所有酒店的信息
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> getallhotelvo() throws RemoteException;

	/**
	 * 获得所有酒店工作人员的信息
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelWorkerVO> getallhotelworkervo() throws RemoteException;

	/**
	 * 获得所有网站营销人员的信息
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<WebMarketVO> getallwebmarketvo() throws RemoteException;

	/**
	 * 获得订单总数
	 * @return
	 * @throws RemoteException
	 */
	public int getordernumber() throws RemoteException;

	/**
	 * 通过客户id搜索客户信息
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public ClientVO manage_searchClientByClientid(int clientid) throws RemoteException;

	/**
	 * 更该客户信息
	 * @param clientvo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage manage_updateClient(ClientVO clientvo) throws RemoteException;

	/**
	 * 添加酒店
	 * @param hotelvo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage manage_addHotel(HotelVO hotelvo) throws RemoteException;

	/**
	 * 添加酒店工作人员
	 * @param hotelworkervo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage manage_addHotelWorker(HotelWorkerVO hotelworkervo) throws RemoteException;

	/**
	 * 通过酒店工作人员id获得酒店工作人员信息
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public HotelWorkerVO manage_searchHotelWorkerByHotelid(int hotelid) throws RemoteException;

	/**
	 * 更新酒店工作人员信息
	 * @param hotelworkervo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage manage_updateHotelWorker(HotelWorkerVO hotelworkervo) throws RemoteException;

	/**
	 * 添加网站营销人员
	 * @param webmarketvo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage manage_addMarketWorker(WebMarketVO webmarketvo) throws RemoteException;

	/**
	 * 通过网站营销人员id获得网站营销人员信息
	 * @param marketWorkerid
	 * @return
	 * @throws RemoteException
	 */
	public WebMarketVO manage_searchMarketWorkerByWebmarketid(int marketWorkerid) throws RemoteException;

	/**
	 * 更新网站营销人员信息
	 * @param webmarketvo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage manage_updateMarketWorker(WebMarketVO webmarketvo) throws RemoteException;
}
