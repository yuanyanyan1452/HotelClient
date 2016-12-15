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

public interface ManageBLService extends Remote{
	// 提供给界面调用的接口
	
		public ResultMessage webmanager_login(String username,String password) throws RemoteException;
		
		public ResultMessage webmarket_login(String username,String password) throws RemoteException;
		
		public ResultMessage webmarket_change_password(String username,String oldpassword,String newpassword)throws RemoteException;
		
		public ResultMessage webmanager_change_password(String username,String oldpassword,String newpassword)throws RemoteException;
		
		public WebMarketVO webmarket_getvo(String username)throws RemoteException;
		
		public WebManagerVO webmanager_getvo(String username)throws RemoteException;
		
		public ArrayList<ClientVO> getallclientvo() throws RemoteException;
		
		public ArrayList<HotelVO> getallhotelvo() throws RemoteException;
		
		public ArrayList<HotelWorkerVO> getallhotelworkervo() throws RemoteException;
		
		public ArrayList<WebMarketVO> getallwebmarketvo() throws RemoteException;
		
		/**
		 * @param clientid
		 * @return 搜索客户
		 */
		public ClientVO manage_searchClient(int clientid)throws RemoteException;

		/**
		 * @param clientid
		 * @return 更新客户信息
		 */
		public ResultMessage manage_updateClient(ClientVO clientvo)throws RemoteException;

		/**
		 * @param hotelid
		 * @return 添加酒店
		 */
		public ResultMessage manage_addHotel(HotelVO hotelvo)throws RemoteException;

		/**
		 * @param hotelid
		 * @param w
		 * @return 添加酒店工作人员
		 */
		public ResultMessage manage_addHotelWorker(HotelWorkerVO w)throws RemoteException;

		/**
		 * @param hotelid
		 * @return 搜索酒店工作人员
		 */
		public HotelWorkerVO manage_searchHotelWorker(int hotelid)throws RemoteException;

		/**
		 * @param hotelid
		 * @return 更新酒店工作人员信息
		 */
		public ResultMessage manage_updateHotelWorker(HotelWorkerVO w)throws RemoteException;

		/**
		 * @param mw
		 * @return 添加网站营销人员
		 */
		public ResultMessage manage_addMarketWorker(WebMarketVO mw)throws RemoteException;

		/**
		 * @param marketWorkerid
		 * @return 搜索网站营销人员
		 */
		public WebMarketVO manage_searchMarketWorker(int marketWorkerid)throws RemoteException;

		/**
		 * @param mw
		 * @return 更新网站营销人员信息
		 */
		public ResultMessage manage_updateMarketWorker(WebMarketVO mw)throws RemoteException;
}
