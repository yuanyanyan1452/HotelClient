package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import objects.ResultMessage;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.ClientVO;

public class ClientEnrollVIPController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	RemoteHelper helper= RemoteHelper.getInstance();
	@FXML
	private RadioButton normalButton;
	
	@FXML
	private RadioButton companyButton;
	
	@FXML
	private TextField infoTextField;
	
	@FXML
	private void close(){
		main.closeExtraStage();
	}
	
	@FXML
	private void enroll() throws RemoteException{
		if(currentclientvo.getvipinfo().getInfo()!=null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("null");
			alert.setHeaderText("null");
			alert.setContentText("对不起，您已经是会员了");
			alert.showAndWait();
		}
		else{
			VIPInfo info=new VIPInfo();
			if(normalButton.isSelected()){
				info.setType(VIPType.NORMAL);
				info.setInfo("一级会员"+","+infoTextField.getText());
			}
			else{
				info.setType(VIPType.Enterprise);
				info.setInfo("一级会员"+","+infoTextField.getText());
			}
			currentclientvo.setvipinfo(info);
			ResultMessage result=helper.getClientBLService().client_enrollVIP(info,currentclientvo.getclientid());
			if(result==ResultMessage.Success){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("注册会员成功");
				alert.showAndWait();
			}
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("null");
				alert.setHeaderText("null");
				alert.setContentText("对不起，注册会员失败");
				alert.showAndWait();
			}
		}
	}
	
	@FXML
	private void initialize(){
		
	}
	
	public ClientEnrollVIPController() {
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main,ClientVO vo){
		this.main = main;
		final ToggleGroup group = new ToggleGroup();
		normalButton.setToggleGroup(group);
		companyButton.setToggleGroup(group);
		currentclientvo=vo;
	}
}
