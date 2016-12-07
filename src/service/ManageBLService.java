package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import objects.ResultMessage;
import vo.ClientVO;
import vo.HotelWorkerVO;
import vo.WebMarketVO;

public interface ManageBLService extends Remote{
	// �ṩ��������õĽӿ�
	
	public ResultMessage webmanager_login(String username,String password) throws RemoteException;
	
	public ResultMessage webmarket_login(String username,String password) throws RemoteException;
	
	/**
	 * @param clientid
	 * @return �����ͻ�
	 */
	public ClientVO manage_searchClient(int clientid)throws RemoteException;

	/**
	 * @param clientid
	 * @return ���¿ͻ���Ϣ
	 */
	public ResultMessage manage_updateClient(int clientid)throws RemoteException;

	/**
	 * @param hotelid
	 * @return ��ӾƵ�
	 */
	public ResultMessage manage_addHotel(int hotelid)throws RemoteException;

	/**
	 * @param hotelid
	 * @param w
	 * @return ��ӾƵ깤����Ա
	 */
	public ResultMessage manage_addHotelWorker(int hotelid, HotelWorkerVO w)throws RemoteException;

	/**
	 * @param hotelid
	 * @return �����Ƶ깤����Ա
	 */
	public HotelWorkerVO manage_searchHotelWorker(int hotelid)throws RemoteException;

	/**
	 * @param hotelid
	 * @return ���¾Ƶ깤����Ա��Ϣ
	 */
	public ResultMessage manage_updateHotelWorker(int hotelid)throws RemoteException;

	/**
	 * @param mw
	 * @return �����վӪ����Ա
	 */
	public ResultMessage manage_addMarketWorker(WebMarketVO mw)throws RemoteException;

	/**
	 * @param marketWorkerid
	 * @return ������վӪ����Ա
	 */
	public WebMarketVO manage_searchMarketWorker(int marketWorkerid)throws RemoteException;

	/**
	 * @param mw
	 * @return ������վӪ����Ա��Ϣ
	 */
	public ResultMessage manage_updateMarketWorker(WebMarketVO mw)throws RemoteException;

}
