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
	public void back(){
		main.gotoLogin(type);
	}
	
	@FXML
	public void changepassword() throws RemoteException{
		if(newpasswordTextField1.getText()==newpasswordTextField2.getText()){
		ResultMessage result=helper.getClientBLService().client_change_password(usernameTextField.getText(), oldpasswordTextField.getText(), newpasswordTextField1.getText());
		if(result==ResultMessage.Success){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("更新密码成功");
			alert.showAndWait();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("对不起，更新密码失败，可能是用户名或密码输入错误或网络原因");
			alert.showAndWait();
		}
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("对不起，您两次输入的新密码不一致");
			alert.showAndWait();
		}
	}
	public UpdatePasswordController() {
		// TODO 
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO 
		
	}
	
	public void setMain(Main main,String type){
		this.main = main;
		this.type = type;
	}
}
