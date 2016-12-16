package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.ClientVO;

public class ClientOverviewController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	//单键模式，实现currentclientvo的单一性，保证它的持续更新
	private static ClientOverviewController clientOverviewController;
	private ClientOverviewController(){
		
	}
	
	public static ClientOverviewController getInstance(){
		if (clientOverviewController==null) {
			clientOverviewController = new ClientOverviewController();
		}
		return clientOverviewController;
	}
	
	public void updateVO(ClientVO vo){
		currentclientvo = vo;
	}
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label contactLabel;
	
	@FXML
	private Label vipLabel;

	@FXML
	private void gotoBasicInfo() throws RemoteException {
		this.updateVO();
		main.gotoClientBasicInfo(currentclientvo);
	}

	@FXML
	private void gotoBrowseHotel() throws RemoteException {
		main.gotoClientBrowseHotel();
	}

	@FXML
	private void gotoEnrollVIP() throws RemoteException {
		main.gotoClientEnrollVIP(currentclientvo);
	}

	@FXML
	private void gotoBrowseOrder() throws RemoteException {
		main.gotoClientBrowseOrder();
	}

	@FXML
	private void gotoSearchHotel() throws RemoteException {
		main.gotoClientSearchHotel();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main,ClientVO vo) {
		this.main = main;
		this.currentclientvo = vo;
		nameLabel.setText(vo.getclient_name());
		contactLabel.setText(vo.getcontact());
		if(vo.getvipinfo()==null){
			vipLabel.setText("非会员");
		}
		else {
			vipLabel.setText(vo.getvipinfo().getType()==VIPType.NORMAL? "普通会员":"企业会员");
		}
	}
	
	public void updateVO() throws RemoteException{
		RemoteHelper helper= RemoteHelper.getInstance();
		this.currentclientvo = helper.getClientBLService().client_checkInfo(currentclientvo.getclientid());
		nameLabel.setText(currentclientvo.getclient_name());
		contactLabel.setText(currentclientvo.getcontact());
		if(currentclientvo.getvipinfo()==null){
			vipLabel.setText("非会员");
		}
		else {
			vipLabel.setText(currentclientvo.getvipinfo().getType()==VIPType.NORMAL? "普通会员":"企业会员");
		}
	}
}
