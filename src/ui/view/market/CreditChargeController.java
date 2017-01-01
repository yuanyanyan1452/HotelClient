package ui.view.market;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.util.AlertUtil;
import ui.util.RecordActionUtil;
import ui.view.Main;
import vo.ClientVO;

public class CreditChargeController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private 
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
			if (currentclientvo.getvipinfo()==null) {
				viptypeLabel.setText("非会员");
			}
			else {
				VIPType viptype=currentclientvo.getvipinfo().getType();
				if(viptype==VIPType.Enterprise){
					viptypeLabel.setText("企业会员");
				}
				else{
					viptypeLabel.setText("普通会员");
				}
			}
			creditLabel.setText(String.valueOf(currentclientvo.getcredit()));
			}
			else{
				AlertUtil.showErrorAlert("对不起，您输入的用户不存在。");
			}
		}
		else{
			AlertUtil.showWarningAlert("对不起，请输入客户的用户名。");
		}
	}
	
	@FXML
	private void chargeCredit(){
		if (creditTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("对不起，请输入信用值额度。");
			return ;
		}
		int charge = Integer.parseInt(creditTextField.getText());
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			//tag=1 充值
			if (helper.getClientBLService().updateClientCredit(currentclientvo.getclientid(), charge, 1)==ResultMessage.Success) {
				
				//更新底层的同时更新控制器持有的vo
				currentclientvo.setcredit(currentclientvo.getcredit()+charge);
				
				//及时更新界面
				creditLabel.setText(currentclientvo.getcredit()+"");
				
				//更新客户的信用记录
				ArrayList< String> record = currentclientvo.getcredit_record();
				
				if (record==null) {
					record = new ArrayList<String>();
				}
				Date date = new Date();
				String nowTime = format.format(date);
				String newRecord = nowTime+",-1,"+RecordActionUtil.getCharge()+","+charge+","+currentclientvo.getcredit();
				record.add(newRecord);
				currentclientvo.setcredit_record(record);
				helper.getClientBLService().client_updateInfo(currentclientvo);
				
				//显示充值成功界面
				AlertUtil.showInformationAlert("充值成功！");
			}
			else {
				AlertUtil.showErrorAlert("充值失败。");
			}
		} catch (RemoteException e) {
			AlertUtil.showErrorAlert("充值失败。与服务器连接异常。");
			e.printStackTrace();
		}
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
