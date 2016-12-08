package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.model.ClientModel;
import ui.view.Main;
import vo.ClientVO;

public class ClientOverviewController implements Initializable {
	private Main main;
	private ClientVO currentClient;

	@FXML
	private void initialize() {

	}

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

	public void setMain(Main main) {
		this.main = main;
	}
}
