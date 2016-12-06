package service;

import java.rmi.Remote;
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
	public ArrayList<OrderVO> order_client_browse(int clientid);

	/**
	 * @param clientid
	 * @param state
	 * @return �ͻ����ݶ���״̬���
	 */
	public ArrayList<OrderVO> order_client_browse(int clientid, OrderState state);

	/**
	 * @param clientid
	 * @param isExecute
	 * @return �ͻ�����ִ��������
	 */
	public ArrayList<OrderVO> order_client_browse(int clientid, boolean isExecute);

	/**
	 * @param hotelid
	 * @return �Ƶ��������
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid);

	/**
	 * @param hotelid
	 * @param state
	 * @return �Ƶ���ݶ���״̬���
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, OrderState state);

	/**
	 * @param hotelid
	 * @param isExecute
	 * @return �Ƶ����ִ��������
	 */
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, boolean isExecute);

	/**
	 * @param clientid
	 * @param orderid
	 * @return �ͻ���������
	 */
	public ResultMessage order_client_cancel(int clientid, int orderid);

	/**
	 * @param vo
	 * @return �ͻ����ɶ���
	 */
	public ResultMessage order_client_generate(OrderVO vo);

	/**
	 * @param orderid
	 * @return �Ƶ�ִ�ж���
	 */
	public ResultMessage order_hotel_execute(int orderid);

	/**
	 * @return ��վӪ����Ա���δִ�ж���
	 */
	public ArrayList<OrderVO> order_market_browseUnfilled();

	/**
	 * @param orderid
	 * @return ��վӪ����Ա�����쳣����
	 */
	public ResultMessage order_market_cancelAbnormal(int orderid);

	/**
	 * @param type
	 * @param num
	 * @return ���㶩���ܼۣ��޴������ԣ�
	 */
	public int calculateTotalwithoutStrategy(RoomType type, int num);

	/**
	 * @param type
	 * @param num
	 * @param list1
	 * @param list2
	 * @return ���㶩���ܼۣ��д������ԣ�
	 */
	public int calculateTotalwithStrategy(RoomType type, int num, ArrayList<HotelStrategyVO> list1,
			ArrayList<WebStrategyVO> list2);

	// �ṩ��ͬ����õĽӿ�
	/**
	 * @param leaveTime
	 * @return ���¶���ʵ���뿪ʱ��
	 */
	public ResultMessage updateActualLeaveTime(int orderid, String leaveTime);
}
