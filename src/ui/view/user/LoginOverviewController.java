package ui.view.user;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.view.Main;

public class LoginOverviewController implements Initializable {
	@FXML
	private Button clientButton;

	@FXML
	private Button hotelButton;

	@FXML
	private Button marketButton;

	@FXML
	private Button managerButton;

	@FXML
	private Label welcomeLabel;

	private Main main;

	@FXML
	public void gotoClientLogin(ActionEvent event) {
		main.gotoClientLogin();
	}
	
	@FXML
	public void gotoHotelWorkerLogin(ActionEvent event) {
		main.gotoHotelOverview();
	}
	
	@FXML
	public void gotoWebMarketLogin(ActionEvent event) {
		main.gotoMarketOverview();
	}
	
	@FXML
	public void gotoWebManagerLogin(ActionEvent event) {
		main.gotoManagerOverview();
	}

	public LoginOverviewController() {

	}

	@FXML
	private void initialize() {

	}

	public void setMain(Main main) {
		this.main = main;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
