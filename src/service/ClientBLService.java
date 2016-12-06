package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.ResultMessage;
import objects.RoomType;
import vo.ClientVO;
import vo.EvaluationVO;
import vo.HotelVO;
import vo.VIPInfoVO;

public interface ClientBLService extends Remote{
	// �ṩ��������õĽӿ�

	public ResultMessage client_login(String username,String password)throws RemoteException ;
	
	public ResultMessage client_register(String username,String password)throws RemoteException;
	/**
	 * @param clientid
	 * @return �ͻ���ȡ��ϸ��Ϣ
	 */
	public ClientVO client_checkInfo(int clientid) throws RemoteException;

	/**
	 * @param vo
	 * @return �ͻ�������ϸ��Ϣ
	 */
	public ResultMessage client_updateInfo(ClientVO vo)throws RemoteException;

	/**
	 * @param clientid
	 * @return �ͻ���ȡ��ʷ�Ƶ��б�
	 */
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid)throws RemoteException;

	/**
	 * @param clientid
	 * @return �ͻ��鿴����ֵ
	 */
	public int client_checkCredit(int clientid)throws RemoteException;

	/**
	 * @param clientid
	 * @return �ͻ���ȡ���ü�¼
	 */
	public ArrayList<String> client_checkCreditList(int clientid)throws RemoteException;

	/**
	 * @param location
	 * @return �ͻ���ȡ��Ӧ��ַ�ľƵ��б�
	 */
	public ArrayList<HotelVO> client_setLocation(String location)throws RemoteException;

	/**
	 * @param hotelname
	 * @return ���϶�Ӧ�Ƶ����ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(String hotelname)throws RemoteException;

	/**
	 * @param type
	 * @return �з��϶�Ӧ�������͵ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(RoomType type)throws RemoteException;

	/**
	 * @param lowprice
	 * @param highprice
	 * @return �з��϶�Ӧ�۸�����ķ���ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(int lowprice, int highprice)throws RemoteException;

	/**
	 * @param inTime
	 * @param leaveTime
	 * @return �з��϶�Ӧʱ��εķ���ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(String inTime, String leaveTime)throws RemoteException;

	/**
	 * @param star
	 * @return ���϶�Ӧ�Ǽ��ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(int star)throws RemoteException;

	/**
	 * @param lowscore
	 * @param highscore
	 * @return ���϶�Ӧ��������ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(double lowscore, double highscore)throws RemoteException;

	/**
	 * @param hotelid
	 * @return �ͻ���ȡ�Ƶ���ϸ��Ϣ
	 */
	public HotelVO client_checkHotelInfo(int hotelid)throws RemoteException;

	/**
	 * @param e
	 * @param clientid
	 * @return �ͻ����۾Ƶ�
	 */
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid)throws RemoteException;

	/**
	 * @param info
	 * @param clientid
	 * @return �ͻ�ע���Ա
	 */
	public ResultMessage client_enrollVIP(VIPInfoVO info, int clientid)throws RemoteException;

//	// �ṩ��ͬ����õĽӿ�
//
//	/**
//	 * @param clientId
//	 * @param value
//	 * @param tag
//	 * @return ���¿ͻ�����ֵ
//	 */
//	public ResultMessage updateClientCredit(int clientId, int value, int tag);
//
//	/**
//	 * @param clientid
//	 * @return �鿴�ͻ���ϸ��Ϣ
//	 */
//	public Client checkClientInfo(int clientid);
//
//	/**
//	 * @param client
//	 * @return ���¿ͻ���ϸ��Ϣ
//	 */
//	public ResultMessage updateClientInfo(Client client);
}
