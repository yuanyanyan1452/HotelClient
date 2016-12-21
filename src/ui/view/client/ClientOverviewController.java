package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.ClientVO;

public class ClientOverviewController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	
	
	
	public void updateVO(ClientVO vo){
		currentclientvo = vo;
		nameLabel.setText(vo.getclient_name());
		contactLabel.setText(vo.getcontact());
		if (vo.getvipinfo()==null) {
			vipLabel.setText("非会员");
		}
		else
			vipLabel.setText(vo.getvipinfo().getType()==VIPType.NORMAL?"普通会员":"企业会员");
	}
	@FXML
	private Button exitButton;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label contactLabel;
	
	@FXML
	private Label vipLabel;

	@FXML
	private void exit(){
		main.exitSystem();
	}
	
	@FXML
	private void gotoBasicInfo() throws RemoteException {
		main.gotoClientBasicInfo(currentclientvo,this);
	}

	@FXML
	private void gotoBrowseHotel() throws RemoteException {
		main.gotoClientBrowseHotel(currentclientvo);
	}

	@FXML
	private void gotoEnrollVIP() throws RemoteException {
		main.gotoClientEnrollVIP(currentclientvo,this);
	}

	@FXML
	private void gotoBrowseOrder() throws RemoteException {
		main.gotoClientBrowseOrder(currentclientvo.getclientid());
	}

	@FXML
	private void gotoSearchHotel() throws RemoteException {
		main.gotoClientSearchHotel(currentclientvo);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public ClientOverviewController(){
		
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
	
//	public void updateVO() throws RemoteException{
//		RemoteHelper helper= RemoteHelper.getInstance();
//		this.currentclientvo = helper.getClientBLService().client_checkInfo(currentclientvo.getclientid());
//		nameLabel.setText(currentclientvo.getclient_name());
//		contactLabel.setText(currentclientvo.getcontact());
//		if(currentclientvo.getvipinfo()==null){
//			vipLabel.setText("非会员");
//		}
//		else {
//			vipLabel.setText(currentclientvo.getvipinfo().getType()==VIPType.NORMAL? "普通会员":"企业会员");
//		}
//	}
}
