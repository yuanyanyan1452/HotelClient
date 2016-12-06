package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.OrderState;
import objects.ResultMessage;
import objects.RoomType;
import vo.HotelStrategyVO;
import vo.OrderVO;
import vo.WebStrategyVO;

public interface OrderBLService extends Remote{
	// �ṩ��������õĽӿ�
	/**
	 * @param clientid
	 * @return �ͻ��������
	 */
	public ArrayList<OrderVO> order_client_browse(int clientid)throws RemoteException;

	/**
	 * @param clientid
	 * @param state
	 * @return �ͻ����ݶ���״̬���
	 */
	public ArrayList<OrderVO> order_client_browse(int clientid, String state)throws RemoteException;

	/**
	 * @param clientid
	 * @param isExecute
	 * @return �ͻ�����ִ��������
	 */
	public ArrayList<OrderVO> order_client_browse(int clientid, boolean isExecute)throws RemoteException;

	/**
	 * @param hotelid
	 * @return �Ƶ��������
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid)throws RemoteException;

	/**
	 * @param hotelid
	 * @param state
	 * @return �Ƶ���ݶ���״̬���
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, String state)throws RemoteException;

	/**
	 * @param hotelid
	 * @param isExecute
	 * @return �Ƶ����ִ��������
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, boolean isExecute)throws RemoteException;

	/**
	 * @param clientid
	 * @param orderid
	 * @return �ͻ���������
	 */
	public ResultMessage order_client_cancel(int clientid, int orderid)throws RemoteException;

	/**
	 * @param vo
	 * @return �ͻ����ɶ���
	 */
	public ResultMessage order_client_generate(OrderVO vo)throws RemoteException;

	/**
	 * @param orderid
	 * @return �Ƶ�ִ�ж���
	 */
	public ResultMessage order_hotel_execute(int orderid)throws RemoteException;

	/**
	 * @return ��վӪ����Ա���δִ�ж���
	 */
	public ArrayList<OrderVO> order_market_browseUnfilled()throws RemoteException;

	/**
	 * @param orderid
	 * @return ��վӪ����Ա�����쳣����
	 */
	public ResultMessage order_market_cancelAbnormal(int orderid)throws RemoteException;

	/**
	 * @param type
	 * @param num
	 * @return ���㶩���ܼۣ��޴������ԣ�
	 */
	public int calculateTotalwithoutStrategy(RoomType type, int num)throws RemoteException;

	/**
	 * @param type
	 * @param num
	 * @param list1
	 * @param list2
	 * @return ���㶩���ܼۣ��д������ԣ�
	 */
	public int calculateTotalwithStrategy(RoomType type, int num, ArrayList<HotelStrategyVO> list1,
			ArrayList<WebStrategyVO> list2)throws RemoteException;

	// �ṩ��ͬ����õĽӿ�
	/**
	 * @param leaveTime
	 * @return ���¶���ʵ���뿪ʱ��
	 */
	public ResultMessage updateActualLeaveTime(int orderid, String leaveTime)throws RemoteException;
}
