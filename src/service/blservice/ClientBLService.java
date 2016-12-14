package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.Client;
import objects.ResultMessage;
import objects.VIPInfo;
import vo.ClientVO;
import vo.EvaluationVO;
import vo.HotelVO;

public interface ClientBLService extends Remote{
	// 提供给界面调用的接口
	
	public ResultMessage client_login(String username,String password)throws RemoteException ;
	
	public ClientVO client_getclientvo(String username)throws RemoteException;
	
	public ResultMessage client_register(String username,String password)throws RemoteException;
	
	public ResultMessage client_change_password(String username,String oldpassword,String newpassword)throws RemoteException;
	/**
	 * @param clientid
	 * @return 客户获取详细信息
	 */
	public ClientVO client_checkInfo(int clientid)throws RemoteException;

	/**
	 * @param vo
	 * @return 客户更新详细信息
	 */
	public ResultMessage client_updateInfo(ClientVO vo)throws RemoteException;

	/**
	 * @param clientid
	 * @return 客户获取历史酒店列表
	 */
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid)throws RemoteException;

	/**
	 * @param clientid
	 * @return 客户查看信用值
	 */
	public int client_checkCredit(int clientid)throws RemoteException;

	/**
	 * @param clientid
	 * @return 客户获取信用记录
	 */
	public ArrayList<String> client_checkCreditList(int clientid)throws RemoteException;


	/**
	 * @param hotelid
	 * @return 客户获取酒店详细信息
	 */
	public HotelVO client_checkHotelInfo(int hotelid)throws RemoteException;

	/**
	 * @param e
	 * @param clientid
	 * @return 客户评价酒店
	 */
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid,int hotelid)throws RemoteException;

	/**
	 * @param info
	 * @param clientid
	 * @return 客户注册会员
	 */
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid)throws RemoteException;

	//更新客户信用记录
	public ResultMessage client_updateClientCreditList(int clientid,String CreditInfo)throws RemoteException;
	
	// 提供给同层调用的接口

	/**
	 * @param clientId
	 * @param value
	 * @param tag
	 * @return 更新客户信用值
	 */
	public ResultMessage updateClientCredit(int clientId, int value, int tag)throws RemoteException;

	/**
	 * @param clientid
	 * @return 查看客户详细信息
	 */
	public Client checkClientInfo(int clientid)throws RemoteException;

	/**
	 * @param client
	 * @return 更新客户详细信息
	 */
	public ResultMessage updateClientInfo(Client client)throws RemoteException;
}
