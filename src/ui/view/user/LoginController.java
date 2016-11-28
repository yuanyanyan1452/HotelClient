package ui.view.user;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.view.Main;

public class LoginController implements Initializable {
	private String type;
	@FXML
	private Label loginLabel;

	@FXML
	private Label usernameLabel;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button registButton;

	@FXML
	public void gotoOverview() {
		// TODO µÇÂ¼ÑéÖ¤
		switch (type) {
		case "client":
			main.gotoClientOverview();
			break;
		case "hotel":
			main.gotoHotelOverview();
		    break;
		case "market":
			main.gotoMarketOverview();
			break;
		case "manager":
			main.gotoManagerOverview();
		default:
			break;
		}
	}

	@FXML
	public void gotoRegist() {
		main.gotoRegist(type);
	}

	// Reference to the main application.
	private Main main;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public LoginController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	public void setMain(Main main,String type) {
		this.main = main;
		this.type = type;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
