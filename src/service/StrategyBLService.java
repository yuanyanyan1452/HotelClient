package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.ResultMessage;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;

public interface StrategyBLService extends Remote{
	// �ṩ��������õĽӿ�

	/**
	 * @param input
	 * @return �Ƶ���Ӵ�������
	 */
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo)throws RemoteException;

	/**
	 * @param strategy
	 * @return �Ƶ���´�������
	 */
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo)throws RemoteException;

	/**
	 * @param strategy
	 * @return ��վӪ����Ա��Ӵ�������
	 */
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo)throws RemoteException;

	/**
	 * @param strategy
	 * @return ��վӪ����Ա���´�������
	 */
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo)throws RemoteException;

	/**
	 * @param hotelid
	 * @param clientid
	 * @return �õ���Ӧ�ͻ����õĶ�Ӧ�Ƶ�Ĵ��������б�
	 */
	public ArrayList<HotelStrategyVO> getStrategy(int hotelid, int clientid);

	/**
	 * @param clientid
	 * @return �õ���Ӧ�ͻ�ʹ�õ���վ�Ĵ��������б�
	 */
	public ArrayList<WebStrategyVO> getStrategy(int clientid);
}
