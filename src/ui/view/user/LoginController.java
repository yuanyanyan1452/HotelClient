package ui.view.user;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.WebManagerVO;

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
	private Button updatepasswordButton;
	
	@FXML
	public void backtooverview(){
		main.backtoMain();
	}
	
	@FXML
	public void exit(){
		main.getPrimaryStage().close();
	}
	
	@FXML
	public void gotoOverview() {
		RemoteHelper helper =  RemoteHelper.getInstance();
		switch (type) {
		case "client":
			try {
				if(helper.getClientBLService().client_login(usernameTextField.getText(), passwordField.getText())
						==ResultMessage.Success){
					main.gotoClientOverview(helper.getClientBLService().client_getclientvo(usernameTextField.getText()));
				}
				else{
					warning();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case "hotel":
			try {
				if(helper.getHotelBLService().hotelworker_login(usernameTextField.getText(), passwordField.getText())
						==ResultMessage.Success){
					HotelWorkerVO hotelworkervo=helper.getHotelBLService().hotelworker_getvo(usernameTextField.getText());
					HotelVO hotelvo=helper.getHotelBLService().hotel_checkInfo(hotelworkervo.gethotelid());
					main.gotoHotelOverview(hotelworkervo,hotelvo);
				}
				else {
					warning();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		    break;
		case "market":
			try {
				if(helper.getManageBLService().webmarket_login(usernameTextField.getText(), passwordField.getText())
						==ResultMessage.Success){
					main.gotoMarketOverview(helper.getManageBLService().webmarket_getvo(usernameTextField.getText()));
				}
				else {
					warning();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case "manager":
			try {
				if(helper.getManageBLService().webmanager_login(usernameTextField.getText(), passwordField.getText())
						==ResultMessage.Success){
//					ClientVO clientvo=
//					HotelWorkerVO hotelworkervo=
//					HotelVO hotelvo=
//					WebManagerVO webmanagervo=
					WebManagerVO webmanagervo=helper.getManageBLService().webmanager_getvo(usernameTextField.getText());
					main.gotoManagerOverview(webmanagervo);
				}
				else {
					warning();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		default:
			break;
		}
	}

	public void warning(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("错误");
		alert.setHeaderText("登录失败");
		alert.setContentText("用户名或密码错误！");
		alert.show();
	}
	@FXML
	public void gotoRegist() {
		main.gotoRegist(type);
	}
	
	@FXML
	public void gotoUpdatePassword(){
		main.gotoUpdatePassword(type);
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

	public Button getRegistButton() {
		return registButton;
	}
}
