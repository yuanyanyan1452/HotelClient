package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.ResultMessage;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;

public interface StrategyBLService extends Remote{
	// 提供给界面调用的接口

	/**
	 * @param input
	 * @return 酒店添加促销策略
	 */
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo)throws RemoteException;

	/**
	 * @param strategy
	 * @return 酒店更新促销策略
	 */
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo)throws RemoteException;

	/**
	 * @param strategy
	 * @return 网站营销人员添加促销策略
	 */
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo)throws RemoteException;

	/**
	 * @param strategy
	 * @return 网站营销人员更新促销策略
	 */
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo)throws RemoteException;

	/**
	 * @param hotelid
	 * @param clientid
	 * @return 得到对应客户适用的对应酒店的促销策略列表
	 */
	public ArrayList<HotelStrategyVO> getHotelStrategy(int hotelid)throws RemoteException;

	/**
	 * @param clientid
	 * @return 得到对应客户使用的网站的促销策略列表
	 */
	public ArrayList<WebStrategyVO> getWebStrategy(int clientid)throws RemoteException;

	public HotelStrategyVO gethotelstrategybyname(String name)throws RemoteException;
	
	public WebStrategyVO getwebstrategybyname(String name)throws RemoteException;

	public ResultMessage hotelstrategy_delete(HotelStrategyVO hotelstrategyvo)throws RemoteException;
	
	public ResultMessage webstrategy_delete(WebStrategyVO webstrategyvo)throws RemoteException;

}