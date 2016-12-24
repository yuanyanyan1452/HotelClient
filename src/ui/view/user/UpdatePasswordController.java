package ui.view.user;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.util.AlertUtil;
import ui.view.Main;

public class UpdatePasswordController implements Initializable {
	private Main main;
	private String type;

	RemoteHelper helper = RemoteHelper.getInstance();

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField oldpasswordTextField;

	@FXML
	private TextField newpasswordTextField1;

	@FXML
	private TextField newpasswordTextField2;

	@FXML
	private Button changeButton;

	@FXML
	private void back() {
		main.gotoLogin(type);
	}

	@FXML
	private void exit() {
		main.exitSystem();
	}

	@FXML
	private void changepassword() throws RemoteException {
		if (newpasswordTextField1.getText().equals(newpasswordTextField2.getText())) {
			ResultMessage message;
			switch (type) {
			case "client":
				message = helper.getClientBLService().client_change_password(usernameTextField.getText(),
						oldpasswordTextField.getText(), newpasswordTextField1.getText());

				break;
			case "hotel":
				message = helper.getHotelBLService().hotelworker_change_password(usernameTextField.getText(),
						oldpasswordTextField.getText(), newpasswordTextField1.getText());
				break;
			case "market":
				message = helper.getManageBLService().webmarket_change_password(usernameTextField.getText(),
						oldpasswordTextField.getText(), newpasswordTextField1.getText());
				break;
			case "manager":
				message = helper.getManageBLService().webmanager_change_password(usernameTextField.getText(),
						oldpasswordTextField.getText(), newpasswordTextField1.getText());
				break;
			default:
				message = ResultMessage.Fail;
			}
			if (message == ResultMessage.Success) {
				AlertUtil.showInformationAlert("更新密码成功！");
				main.gotoLogin(type);
			} else {
				AlertUtil.showErrorAlert("对不起。更新密码失败。/n可能是由于用户名不存在或旧密码输入错误。");
			}
		} else {
			AlertUtil.showWarningAlert("两次输入的新密码不一致。");
		}
	}

	public UpdatePasswordController() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setMain(Main main, String type) {
		this.main = main;
		this.type = type;
		changeButton.setDisable(true);
		usernameTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (usernameTextField.getText().isEmpty() || oldpasswordTextField.getText().isEmpty()
						|| newpasswordTextField1.getText().isEmpty() || newpasswordTextField2.getText().isEmpty()) {
					changeButton.setDisable(true);
				} else
					changeButton.setDisable(false);
			}
		});
		oldpasswordTextField.setOnKeyReleased(usernameTextField.getOnKeyReleased());
		newpasswordTextField1.setOnKeyReleased(usernameTextField.getOnKeyReleased());
		newpasswordTextField2.setOnKeyReleased(usernameTextField.getOnKeyReleased());
	}
}
