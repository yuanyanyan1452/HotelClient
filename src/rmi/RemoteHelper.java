package rmi;

import java.rmi.Remote;

import service.blservice.ClientBLService;
import service.blservice.HotelBLService;
import service.blservice.ManageBLService;
import service.blservice.OrderBLService;
import service.blservice.StrategyBLService;

public class RemoteHelper {
	private Remote remote;
	private static RemoteHelper remoteHelper = new RemoteHelper();
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}
	
	private RemoteHelper() {
		
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public ClientBLService getClientBLService(){
		return (ClientBLService)remote;
	}
	
	public HotelBLService getHotelBLService(){
		return (HotelBLService)remote;
	}

	public ManageBLService getManageBLService() {
		return (ManageBLService)remote;
	}
	
	public OrderBLService getOrderBLService() {
		return (OrderBLService)remote;
	}
	
	public StrategyBLService getStrategyBLService() {
		return (StrategyBLService)remote;
	}
	

}
