package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.model.CreditRecordModel;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.ClientVO;

public class ClientBasicInfoController implements Initializable {
	private Main main;
	private ClientVO currentclientvo;
	private ClientOverviewController controller;
	RemoteHelper helper = RemoteHelper.getInstance();

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField contactTextField;

	@FXML
	private Label vipLevelLabel;

	@FXML
	private Label vipLevelNameLabel;

	@FXML
	private Label vipTypeLabel;

	@FXML
	private Label infoNameLabel;

	@FXML
	private Label infoLabel;

	@FXML
	private Label creditLabel;

	@FXML
	private TableView<CreditRecordModel> creditTable;

	@FXML
	private TableColumn<CreditRecordModel, String> timeColumn;

	@FXML
	private TableColumn<CreditRecordModel, String> reasonColumn;

	@FXML
	private TableColumn<CreditRecordModel, String> resultColumn;

	@FXML
	private TableColumn<CreditRecordModel, String> changeColumn;

	@FXML
	private TableColumn<CreditRecordModel, String> orderidColumn;

	@FXML
	private void update() throws RemoteException {
		if (nameTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("姓名不能为空！");
		}
		else if (contactTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("联系方式不能为空！");
		}
		else{
			currentclientvo.setclient_name(nameTextField.getText());
			currentclientvo.setcontact(contactTextField.getText());
			ResultMessage result = helper.getClientBLService().client_updateInfo(currentclientvo);
			if (result == ResultMessage.Success) {
				AlertUtil.showInformationAlert("更新成功！");
				//同步更新主界面的label
				this.controller.updateVO(currentclientvo);
			} else {
				AlertUtil.showErrorAlert("对不起，更新失败。");
			}
		}
		
		
	}

	public ClientBasicInfoController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main, ClientVO clientvo,ClientOverviewController controller) {
		this.main = main;
		currentclientvo = clientvo;
		this.controller = controller;
		//导入名字和联系方式
		nameTextField.setText(clientvo.getclient_name());
		contactTextField.setText(clientvo.getcontact());
		
		//导入会员类型和信息
		if (clientvo.getvipinfo() == null) {
			vipTypeLabel.setText("非会员");
			infoNameLabel.setText("");
			infoLabel.setText("");
			vipLevelNameLabel.setText("");
			vipLevelLabel.setText("");
		} else {
			VIPType viptype = clientvo.getvipinfo().getType();
			if (viptype.equals(VIPType.NORMAL)) {
				vipTypeLabel.setText("普通会员");
				infoNameLabel.setText("生日");
				String[] info = clientvo.getvipinfo().getInfo().split(",");
				infoLabel.setText(info[1]);
				vipLevelLabel.setText(info[0]);
				vipLevelNameLabel.setText("会员等级：");
			} else if (viptype.equals(VIPType.Enterprise)) {
				vipTypeLabel.setText("企业会员");
				infoNameLabel.setText("所属企业：");
				String[] info = clientvo.getvipinfo().getInfo().split(",");
				infoLabel.setText(info[1]);
				vipLevelLabel.setText(info[0]);
				vipLevelNameLabel.setText("会员等级：");
			}
		}
		//导入信用值
		creditLabel.setText(String.valueOf(clientvo.getcredit()));

		//导入信用记录
		timeColumn.setCellValueFactory(celldata -> celldata.getValue().timeProperty());
		reasonColumn.setCellValueFactory(celldata -> celldata.getValue().actionProperty());
		resultColumn.setCellValueFactory(celldata -> celldata.getValue().resultProperty());
		changeColumn.setCellValueFactory(celldata -> celldata.getValue().changeProperty());
		orderidColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		
		try {
			ObservableList<CreditRecordModel> creditRecordList = FXCollections.observableArrayList();
			ArrayList<String> records = helper.getClientBLService().client_checkCreditList(clientvo.getclientid());
			for(int i=0;i<records.size();i++){
				if (!records.get(i).isEmpty()) {
					CreditRecordModel model = new CreditRecordModel(records.get(i));
					creditRecordList.add(model);
				}
			}
			creditTable.setItems(creditRecordList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
}
