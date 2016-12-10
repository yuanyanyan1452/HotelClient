package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import objects.Client;
import objects.VIPInfo.VIPType;
import ui.model.ClientModel;
import ui.view.Main;
import vo.ClientVO;
import vo.VOChange;

public class ClientOverviewController implements Initializable {
	private Main main;
	private Client currentClient;

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
	private void gotoBasicInfo() {
		main.gotoClientBasicInfo();
	}

	@FXML
	private void gotoBrowseHotel() {
		main.gotoClientBrowseHotel();
	}

	@FXML
	private void gotoEnrollVIP() {
		main.gotoClientEnrollVIP();
	}

	@FXML
	private void gotoBrowseOrder() {
		main.gotoClientBrowseOrder();
	}

	@FXML
	private void gotoSearchHotel() {
		main.gotoClientSearchHotel();
	}

	public ClientOverviewController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main,ClientVO vo) {
		this.main = main;
		this.currentClient = VOChange.clientvo_to_client(vo);
		nameLabel.setText(currentClient.getclient_name());
		contactLabel.setText(currentClient.getcontact());
		if(currentClient.getvipinfo()==null){
			vipLabel.setText("非会员");
		}
		else {
			vipLabel.setText(currentClient.getvipinfo().getType()==VIPType.NORMAL? "普通会员":"企业会员");
		}
	}
}
