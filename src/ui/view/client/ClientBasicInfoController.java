package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.ClientVO;

public class ClientBasicInfoController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	RemoteHelper helper=RemoteHelper.getInstance();
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextField contactTextField;

	@FXML
	private Label vipLevelLabel;
	
	
	@FXML
	private Label vipTypeLabel;
	
	@FXML
	private Label infoNameLabel;
	
	@FXML
	private Label infoLabel;
	
	@FXML
	private Label creditLabel;
	
	@FXML
	private TableView<String> creditTable;
	
	@FXML
	private TableColumn< String, String> timeColumn;
	
	@FXML
	private TableColumn< String, String> reasonColumn;
	
	@FXML
	private TableColumn< String, String> resultColumn;
	
	@FXML
	private TableColumn<String, String> changeColumn;
	
	@FXML
	private TableColumn<String, String> orderidColumn;

	@FXML
	private void update() throws RemoteException {
		currentclientvo.setclient_name(nameTextField.getText());
		currentclientvo.setcontact(contactTextField.getText());
		ResultMessage result=helper.getClientBLService().client_updateInfo(currentclientvo);
		if(result==ResultMessage.Success){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("更新成功");
			alert.showAndWait();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("null");
			alert.setHeaderText("null");
			alert.setContentText("对不起，更新失败");
			alert.showAndWait();
		}
		// TODO
	}

	@FXML
	private void initialize() {

	}

	public ClientBasicInfoController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main,ClientVO clientvo) {
		this.main = main;
		currentclientvo=clientvo;
		VIPType viptype=currentclientvo.getvipinfo().getType();
		if(viptype==VIPType.NORMAL){
			vipTypeLabel.setText("普通会员");
			infoNameLabel.setText("生日");
			String [] info=currentclientvo.getvipinfo().getInfo().split(",");
			infoLabel.setText(info[1]);
			creditLabel.setText(String.valueOf(currentclientvo.getcredit()));
			
			//信用记录列表初始化
		}
		
		
	}
}
