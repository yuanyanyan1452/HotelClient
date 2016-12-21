package ui.view.user;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.util.AlertUtil;
import ui.view.Main;

public class UpdatePasswordController implements Initializable{
	private Main main;
	private String type;
	
	RemoteHelper helper=RemoteHelper.getInstance();
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private TextField oldpasswordTextField;
	
	@FXML
	private TextField newpasswordTextField1;
	
	@FXML
	private TextField newpasswordTextField2;
	
	@FXML
	private void back(){
		main.gotoLogin(type);
	}
	
	@FXML
	private void exit(){
		main.exitSystem();
	}
	
	@FXML
	private void changepassword() throws RemoteException{
		if(newpasswordTextField1.getText().equals(newpasswordTextField2.getText())){
		switch(type){
		case "client":
		ResultMessage result1=helper.getClientBLService().client_change_password(usernameTextField.getText(), oldpasswordTextField.getText(), newpasswordTextField1.getText());
		if(result1==ResultMessage.Success){
			AlertUtil.showInformationAlert("更新密码成功！");
			main.gotoLogin(type);
		}
		else{
			AlertUtil.showErrorAlert("对不起。更新密码失败。/n可能是由于用户名不存在或旧密码输入错误。");
		}
			break;
		case "hotel":
			ResultMessage result2=helper.getHotelBLService().hotelworker_change_password(usernameTextField.getText(), oldpasswordTextField.getText(), newpasswordTextField1.getText());
			if(result2==ResultMessage.Success){
				AlertUtil.showInformationAlert("更新密码成功！");
				main.gotoLogin(type);
			}
			else{
				AlertUtil.showErrorAlert("对不起。更新密码失败。/n可能是由于用户名不存在或旧密码输入错误。");
			}
			break;
		case "market":
			ResultMessage result3=helper.getManageBLService().webmarket_change_password(usernameTextField.getText(), oldpasswordTextField.getText(), newpasswordTextField1.getText());
			if(result3==ResultMessage.Success){
				AlertUtil.showInformationAlert("更新密码成功！");
				main.gotoLogin(type);
			}
			else{
				AlertUtil.showErrorAlert("对不起。更新密码失败。/n可能是由于用户名不存在或旧密码输入错误。");
			}
			break;
		case "manager":
			ResultMessage result4=helper.getManageBLService().webmanager_change_password(usernameTextField.getText(), oldpasswordTextField.getText(), newpasswordTextField1.getText());
			if(result4==ResultMessage.Success){
				AlertUtil.showInformationAlert("更新密码成功！");
				main.gotoLogin(type);
			}
			else{
				AlertUtil.showErrorAlert("对不起。更新密码失败。/n可能是由于用户名不存在或旧密码输入错误。");
			}
		}
		}
		else{
			AlertUtil.showWarningAlert("两次输入的新密码不一致。");
		}
	}
	public UpdatePasswordController() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setMain(Main main,String type){
		this.main = main;
		this.type = type;
	}
}
