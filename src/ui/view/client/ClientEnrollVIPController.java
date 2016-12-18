package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import objects.ResultMessage;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.ClientVO;

public class ClientEnrollVIPController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	private ClientOverviewController controller;
	RemoteHelper helper= RemoteHelper.getInstance();
	@FXML
	private RadioButton normalButton;
	
	@FXML
	private RadioButton companyButton;
	
	@FXML
	private Label typeLabel;
	
	@FXML
	private DatePicker birthday;
	
	@FXML
	private TextField infoTextField;
	
	
	@FXML
	private void enroll() throws RemoteException{
		if(currentclientvo.getvipinfo()!=null){
			AlertUtil.showErrorAlert("您已经是会员啦！");
		}
		else{
			VIPInfo info=new VIPInfo();
			if(normalButton.isSelected()){
				info.setType(VIPType.NORMAL);
				LocalDate date = birthday.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
				String birth = formatter.format(date);
				info.setInfo("一级会员"+","+birth);
			}
			else{
				info.setType(VIPType.Enterprise);
				info.setInfo("一级会员"+","+infoTextField.getText());
			}
			currentclientvo.setvipinfo(info);
			ResultMessage result=helper.getClientBLService().client_enrollVIP(info,currentclientvo.getclientid());
			if(result==ResultMessage.Success){
				controller.updateVO(currentclientvo);
				AlertUtil.showInformationAlert("恭喜您成为我们的会员<(￣︶￣)>");
			}
			else{
				AlertUtil.showErrorAlert("注册会员失败(°ー°〃)");
			}
		}
	}
	
	public ClientEnrollVIPController() {
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main,ClientVO vo,ClientOverviewController controller){
		this.controller = controller;
		this.main = main;
		birthday.setVisible(false);
		infoTextField.setVisible(false);
		final ToggleGroup group = new ToggleGroup();
		normalButton.setToggleGroup(group);
		companyButton.setToggleGroup(group);
		currentclientvo=vo;
		
		normalButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				typeLabel.setText("生日");
				birthday.setVisible(true);
				infoTextField.setVisible(false);
			}
		});
		companyButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				typeLabel.setText("所属企业");
				birthday.setVisible(false);
				infoTextField.setVisible(true);
			}
		});
	}
}
