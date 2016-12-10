package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import objects.Client;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.model.ClientModel;
import ui.view.Main;
import vo.ClientVO;
import vo.VOChange;

public class ClientOverviewController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	RemoteHelper helper= RemoteHelper.getInstance();
	
	@FXML
	private void initialize() {

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

	public ClientOverviewController() {

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
		this.currentclientvo = helper.getClientBLService().client_checkInfo(currentclientvo.getclientid());
	}
}
