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
	private void clientgotoLogin(ActionEvent event) {
		main.gotoLogin("client");
	}

	@FXML
	private void hotelgotoLogin(ActionEvent event) {
		main.gotoLogin("hotel");
	}
	@FXML
	private void marketgotoLogin(ActionEvent event) {
		main.gotoLogin("market");
	}
	@FXML
	private void managergotoLogin(ActionEvent event) {
		main.gotoLogin("manager");
	}
	@FXML
	private void exit(){
		main.exitSystem();
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
