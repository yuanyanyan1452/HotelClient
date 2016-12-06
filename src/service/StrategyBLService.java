package service;

import java.util.ArrayList;

import objects.HotelStrategy;
import objects.ResultMessage;
import objects.WebStrategy;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;

public interface StrategyBLService {
	// �ṩ��������õĽӿ�

	/**
	 * @param input
	 * @return �Ƶ���Ӵ�������
	 */
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo);

	/**
	 * @param strategy
	 * @return �Ƶ���´�������
	 */
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo);

	/**
	 * @param strategy
	 * @return ��վӪ����Ա��Ӵ�������
	 */
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo);

	/**
	 * @param strategy
	 * @return ��վӪ����Ա���´�������
	 */
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo);

	// �ṩ��ͬ����õĽӿ�
	/**
	 * @param hotelid
	 * @param clientid
	 * @return �õ���Ӧ�ͻ����õĶ�Ӧ�Ƶ�Ĵ��������б�
	 */
	public ArrayList<HotelStrategy> getStrategy(int hotelid, int clientid);

	/**
	 * @param clientid
	 * @return �õ���Ӧ�ͻ�ʹ�õ���վ�Ĵ��������б�
	 */
	public ArrayList<WebStrategy> getStrategy(int clientid);
}
