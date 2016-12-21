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

	public void updateVO(ClientVO vo) {
		currentclientvo = vo;
		nameLabel.setText(vo.getclient_name());
		contactLabel.setText(vo.getcontact());
		if (vo.getvipinfo() == null) {
			vipLabel.setText("非会员");
		} else {
			vipLabel.setText(vo.getvipinfo().getType() == VIPType.NORMAL ? "普通会员" : "企业会员");
		}
		//客户完善基本信息后，恢复正常功能
		tipLabel.setText("您可以。。。");
		searchhotelButton.setDisable(false);
		browsehotelButton.setDisable(false);
		browseorderButton.setDisable(false);
		enrollvipButton.setDisable(false);
	}

	@FXML
	private Button basicinfoButton;

	@FXML
	private Button searchhotelButton;

	@FXML
	private Button browsehotelButton;

	@FXML
	private Button browseorderButton;

	@FXML
	private Button enrollvipButton;

	@FXML
	private Button exitButton;

	@FXML
	private Label tipLabel;

	@FXML
	private Label nameLabel;

	@FXML
	private Label contactLabel;

	@FXML
	private Label vipLabel;

	@FXML
	private void exit() {
		main.exitSystem();
	}

	@FXML
	private void gotoBasicInfo() throws RemoteException {
		main.gotoClientBasicInfo(currentclientvo, this);
	}

	@FXML
	private void gotoBrowseHotel() throws RemoteException {
		main.gotoClientBrowseHotel(currentclientvo);
	}

	@FXML
	private void gotoEnrollVIP() throws RemoteException {
		main.gotoClientEnrollVIP(currentclientvo, this);
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

	public ClientOverviewController() {

	}

	public void setMain(Main main, ClientVO vo) {
		this.main = main;
		this.currentclientvo = vo;
		// 如果是刚注册的用户，只开放维护基本信息按钮
		if (currentclientvo.getclient_name()==null || currentclientvo.getcontact()==null
				||currentclientvo.getclient_name().isEmpty()||currentclientvo.getcontact().isEmpty()) {
			tipLabel.setText("您是刚注册的客户，请先完善您的基本信息~");
			basicinfoButton.setDisable(false);
			searchhotelButton.setDisable(true);
			browsehotelButton.setDisable(true);
			browseorderButton.setDisable(true);
			enrollvipButton.setDisable(true);
		} else {
			nameLabel.setText(vo.getclient_name());
			contactLabel.setText(vo.getcontact());
			if (vo.getvipinfo() == null) {
				vipLabel.setText("非会员");
				enrollvipButton.setDisable(false);
			} else {
				vipLabel.setText(vo.getvipinfo().getType() == VIPType.NORMAL ? "普通会员" : "企业会员");
				// 会员无法再注册会员
				enrollvipButton.setDisable(true);
			}
		}

	}

}
