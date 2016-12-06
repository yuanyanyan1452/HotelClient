package service;

import java.rmi.Remote;
import java.util.ArrayList;

import objects.ResultMessage;
import objects.RoomType;
import vo.ClientVO;
import vo.EvaluationVO;
import vo.HotelVO;
import vo.VIPInfoVO;

public interface ClientBLService extends Remote{
	// �ṩ��������õĽӿ�

	/**
	 * @param clientid
	 * @return �ͻ���ȡ��ϸ��Ϣ
	 */
	public ClientVO client_checkInfo(int clientid);

	/**
	 * @param vo
	 * @return �ͻ�������ϸ��Ϣ
	 */
	public ResultMessage client_updateInfo(ClientVO vo);

	/**
	 * @param clientid
	 * @return �ͻ���ȡ��ʷ�Ƶ��б�
	 */
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid);

	/**
	 * @param clientid
	 * @return �ͻ��鿴����ֵ
	 */
	public int client_checkCredit(int clientid);

	/**
	 * @param clientid
	 * @return �ͻ���ȡ���ü�¼
	 */
	public ArrayList<String> client_checkCreditList(int clientid);

	/**
	 * @param location
	 * @return �ͻ���ȡ��Ӧ��ַ�ľƵ��б�
	 */
	public ArrayList<HotelVO> client_setLocation(String location);

	/**
	 * @param hotelname
	 * @return ���϶�Ӧ�Ƶ����ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(String hotelname);

	/**
	 * @param type
	 * @return �з��϶�Ӧ�������͵ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(RoomType type);

	/**
	 * @param lowprice
	 * @param highprice
	 * @return �з��϶�Ӧ�۸�����ķ���ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(int lowprice, int highprice);

	/**
	 * @param inTime
	 * @param leaveTime
	 * @return �з��϶�Ӧʱ��εķ���ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(String inTime, String leaveTime);

	/**
	 * @param star
	 * @return ���϶�Ӧ�Ǽ��ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(int star);

	/**
	 * @param lowscore
	 * @param highscore
	 * @return ���϶�Ӧ��������ľƵ��б�
	 */
	public ArrayList<HotelVO> client_searchHotel(double lowscore, double highscore);

	/**
	 * @param hotelid
	 * @return �ͻ���ȡ�Ƶ���ϸ��Ϣ
	 */
	public HotelVO client_checkHotelInfo(int hotelid);

	/**
	 * @param e
	 * @param clientid
	 * @return �ͻ����۾Ƶ�
	 */
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid);

	/**
	 * @param info
	 * @param clientid
	 * @return �ͻ�ע���Ա
	 */
	public ResultMessage client_enrollVIP(VIPInfoVO info, int clientid);

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
