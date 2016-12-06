package service;

import java.rmi.Remote;

import objects.ResultMessage;
import vo.ClientVO;
import vo.HotelWorkerVO;
import vo.WebMarketVO;

public interface ManageBLService extends Remote{
	// �ṩ��������õĽӿ�
	/**
	 * @param clientid
	 * @return �����ͻ�
	 */
	public ClientVO manage_searchClient(int clientid);

	/**
	 * @param clientid
	 * @return ���¿ͻ���Ϣ
	 */
	public ResultMessage manage_updateClient(int clientid);

	/**
	 * @param hotelid
	 * @return ��ӾƵ�
	 */
	public ResultMessage manage_addHotel(int hotelid);

	/**
	 * @param hotelid
	 * @param w
	 * @return ��ӾƵ깤����Ա
	 */
	public ResultMessage manage_addHotelWorker(int hotelid, HotelWorkerVO w);

	/**
	 * @param hotelid
	 * @return �����Ƶ깤����Ա
	 */
	public HotelWorkerVO manage_searchHotelWorker(int hotelid);

	/**
	 * @param hotelid
	 * @return ���¾Ƶ깤����Ա��Ϣ
	 */
	public ResultMessage manage_updateHotelWorker(int hotelid);

	/**
	 * @param mw
	 * @return �����վӪ����Ա
	 */
	public ResultMessage manage_addMarketWorker(WebMarketVO mw);

	/**
	 * @param marketWorkerid
	 * @return ������վӪ����Ա
	 */
	public WebMarketVO manage_searchMarketWorker(int marketWorkerid);

	/**
	 * @param mw
	 * @return ������վӪ����Ա��Ϣ
	 */
	public ResultMessage manage_updateMarketWorker(WebMarketVO mw);

}
