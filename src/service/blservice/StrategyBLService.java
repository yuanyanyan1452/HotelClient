
package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.ResultMessage;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;

public interface StrategyBLService extends Remote{

	/**
	 * 酒店制定促销策略
	 * @param strategyvo
	 * @return 
	 * @throws RemoteException
	 */
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo)throws RemoteException;

	/**
	 * 酒店更新促销策略
	 * @param strategyvo
	 * @return 
	 * @throws RemoteException
	 */
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo)throws RemoteException;

	/**
	 * 网站制定促销策略
	 * @param strategyvo
	 * @return 
	 * @throws RemoteException
	 */
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo)throws RemoteException;

	/**
	 * 网站更新促销策略
	 * @param strategyvo
	 * @return 
	 * @throws RemoteException
	 */
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo)throws RemoteException;

	/**
	 * 获得对应酒店的促销策略列表
	 * @param hotelid
	 * @return 
	 * @throws RemoteException
	 */
	public ArrayList<HotelStrategyVO> getHotelStrategy(int hotelid)throws RemoteException;

	/**
	 * 获得网站的促销策略列表
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<WebStrategyVO> getWebStrategy()throws RemoteException;

	/**
	 * 通过名字获得酒店销售策略
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	public HotelStrategyVO gethotelstrategybyname(String name)throws RemoteException;
	
	/**
	 * 通过名字获得网站销售策略
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	public WebStrategyVO getwebstrategybyname(String name)throws RemoteException;

	/**
	 * 删除酒店销售策略
	 * @param hotelstrategyvo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage hotelstrategy_delete(HotelStrategyVO hotelstrategyvo)throws RemoteException;
	
	/**
	 * 删除网站销售策略
	 * @param webstrategyvo
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage webstrategy_delete(WebStrategyVO webstrategyvo)throws RemoteException;
}
