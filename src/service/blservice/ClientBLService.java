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
	
	/**
	 * 客户使用用户名密码登录
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage client_login(String username,String password)throws RemoteException ;
	
	/**
	 * 通过用户名获取用户信息
	 * @param username
	 * @return
	 * @throws RemoteException
	 */
	public ClientVO client_getclientvo(String username)throws RemoteException;
	
	/**
	 * 客户注册账号
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage client_register(String username,String password)throws RemoteException;
	
	/**
	 * 客户通过用户名，旧密码，新密码修改密码
	 * @param username
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage client_change_password(String username,String oldpassword,String newpassword)throws RemoteException;
	
	/**
	 * 通过客户id获得客户信息
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public ClientVO client_checkInfo(int clientid)throws RemoteException;

	/**
	 * 更新客户信息
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage client_updateInfo(ClientVO vo)throws RemoteException;

	/**
	 * 通过客户id获得客户之前预订过的酒店
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid)throws RemoteException;

	/**
	 * 通过客户id获得信用值信息
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public int client_checkCredit(int clientid)throws RemoteException;

	/**
	 * 通过客户id获得信用值记录
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<String> client_checkCreditList(int clientid)throws RemoteException;

	/**
	 * 通过酒店id获得酒店信息
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public HotelVO client_checkHotelInfo(int hotelid)throws RemoteException;

	/**
	 * 客户评价酒店
	 * @param e
	 * @param clientid
	 * @param hotelid
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid,int hotelid)throws RemoteException;

	/** 
	 * 客户注册会员
	 * @param info
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid)throws RemoteException;

	/**
	 * 更新客户信用记录
	 * @param clientid
	 * @param CreditInfo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage client_updateClientCreditList(int clientid,String CreditInfo)throws RemoteException;
	
	// 提供给同层调用的接口

	/**
	 * 更新客户信用值
	 * @param clientId
	 * @param value
	 * @param tag
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage updateClientCredit(int clientId, int value, int tag)throws RemoteException;

	/**
	 * 通过客户id查看客户信息
	 * @param clientid
	 * @return
	 * @throws RemoteException
	 */
	public Client checkClientInfo(int clientid)throws RemoteException;

	/**
	 * 更新客户信息
	 * @param client
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage updateClientInfo(Client client)throws RemoteException;
}
