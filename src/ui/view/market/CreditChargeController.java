package ui.view.market;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.ClientVO;

public class CreditChargeController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	RemoteHelper helper=RemoteHelper.getInstance();

	@FXML
	private TextField clientUserNameTextField;
	
	@FXML
	private TextField creditTextField;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label contactLabel;
	
	@FXML
	private Label viptypeLabel;
	
	@FXML
	private Label creditLabel;
	
	@FXML
	private void searchClient() throws RemoteException{
		if(clientUserNameTextField.getText()!=null){
			currentclientvo=helper.getClientBLService().client_getclientvo(clientUserNameTextField.getText());
			if(currentclientvo.getclient_name()!=null){
			nameLabel.setText(currentclientvo.getclient_name());
			contactLabel.setText(currentclientvo.getcontact());
			VIPType viptype=currentclientvo.getvipinfo().getType();
			if(viptype==VIPType.Enterprise){
				viptypeLabel.setText("企业会员");
			}
			else{
				viptypeLabel.setText("普通会员");
			}
			creditLabel.setText(String.valueOf(currentclientvo.getcredit()));
			}
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("对不起，你输入的用户不存在");
				alert.showAndWait();
			}
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("对不起，请输入客户用户名");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void chargeCredit(){
		//TODO
		
	}
	
	public CreditChargeController() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
