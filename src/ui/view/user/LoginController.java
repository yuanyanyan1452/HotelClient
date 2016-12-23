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
import ui.util.AlertUtil;
import ui.view.Main;
import vo.ClientVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.WebManagerVO;
import vo.WebMarketVO;

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
		main.exitSystem();
	}
	
	@FXML
	public void gotoOverview() {
		RemoteHelper helper =  RemoteHelper.getInstance();
		switch (type) {
		case "client":
			try {
				if(helper.getClientBLService().client_login(usernameTextField.getText(), passwordField.getText())
						==ResultMessage.Success){
					ClientVO clientVO=helper.getClientBLService().client_getclientvo(usernameTextField.getText());
					if(!clientVO.getlogged()){
					main.gotoClientOverview(clientVO);
					clientVO.setlogged(true);
					helper.getClientBLService().client_updateInfo(clientVO);
					}
					else{
						AlertUtil.showWarningAlert("对不起，该账户已被登录");
					}
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
					HotelVO hotelvo=helper.getHotelBLService().hotel_getInfo(hotelworkervo.gethotelid());
					if(!hotelworkervo.getlogged()){
						main.gotoHotelOverview(hotelworkervo,hotelvo);
						hotelworkervo.setlogged(true);
						helper.getManageBLService().manage_updateHotelWorker(hotelworkervo);
					}
					else{
						AlertUtil.showWarningAlert("对不起，该账户已被登录");
					}
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
					WebMarketVO webMarketVO=helper.getManageBLService().webmarket_getvo(usernameTextField.getText());
					if(!webMarketVO.getlogged()){
						main.gotoMarketOverview(webMarketVO);
						webMarketVO.setlogged(true);
						helper.getManageBLService().manage_updateMarketWorker(webMarketVO);
					}
					else {
						AlertUtil.showWarningAlert("对不起，该账户已被登录");
					}
				}
				else{
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
					WebManagerVO webmanagervo=helper.getManageBLService().webmanager_getvo(usernameTextField.getText());
					if(!webmanagervo.getlogged()){
					main.gotoManagerOverview(webmanagervo);
					webmanagervo.setlogged(true);
//					helper.getManageBLService().
					}
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
