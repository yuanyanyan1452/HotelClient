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
	public void clientgotoLogin(ActionEvent event) {
		main.gotoLogin("client");
	}

	@FXML
	public void hotelgotoLogin(ActionEvent event) {
		main.gotoLogin("hotel");
	}
	@FXML
	public void marketgotoLogin(ActionEvent event) {
		main.gotoLogin("market");
	}
	@FXML
	public void managergotoLogin(ActionEvent event) {
		main.gotoLogin("manager");
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
