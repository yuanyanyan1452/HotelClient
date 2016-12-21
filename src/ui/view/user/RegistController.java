package ui.view.user;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.ClientVO;

public class RegistController implements Initializable {
	private Main main;
	private String type;

	RemoteHelper helper = RemoteHelper.getInstance();
	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField1;

	@FXML
	private TextField passwordTextField2;

	@FXML
	private ImageView yesImage;
	
	@FXML
	private ImageView noImage;
	
	@FXML
	private Label noLabel;
	
	@FXML
	private Button registButton;
	
	public RegistController() {

	}

	public void setMain(Main main, String type) {
		this.main = main;
		this.type = type;
		yesImage.setVisible(false);
		noImage.setVisible(false);
		noLabel.setVisible(false);
		registButton.setDisable(true);
		usernameTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				RemoteHelper helper = RemoteHelper.getInstance();
				try {
					if (usernameTextField.getText().isEmpty()) {
						yesImage.setVisible(false);
						noImage.setVisible(false);
						noLabel.setVisible(false);
						registButton.setDisable(true);
					}
					ClientVO vo = helper.getClientBLService().client_getclientvo(usernameTextField.getText());
					if (vo.getclientid()==0) {
						yesImage.setVisible(true);
						noImage.setVisible(false);
						noLabel.setVisible(false);
						registButton.setDisable(false);
					}
					else{
						yesImage.setVisible(false);
						noImage.setVisible(true);
						noLabel.setVisible(true);
						registButton.setDisable(true);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	private void cancelRegist(ActionEvent event) {
		main.gotoLogin(type);
	}

	@FXML
	private void register() throws RemoteException {
		if (usernameTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("账号不可以为空！");
		}
		else if (passwordTextField1.getText().isEmpty()) {
			AlertUtil.showWarningAlert("密码不可以为空！");
		}
		else if (passwordTextField1.getText().equals(passwordTextField2.getText())) {
			ResultMessage result = helper.getClientBLService().client_register(usernameTextField.getText(),
					passwordTextField1.getText());
			if (result == ResultMessage.Success) {
				AlertUtil.showInformationAlert("注册成功！");
				main.gotoLogin("client");
			} else {
				AlertUtil.showErrorAlert("对不起，注册失败，可能是用户名冲突或者网络问题。");
			}
		}
		else {
			AlertUtil.showWarningAlert("对不起，您两次输入的密码不一致。");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
