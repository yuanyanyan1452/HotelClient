package ui.view.manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;
import rmi.RemoteHelper;
import ui.model.ClientModel;
import ui.model.HotelModel;
import ui.model.HotelWorkerModel;
import ui.model.MarketModel;
import ui.view.Main;
import vo.WebManagerVO;

public class ManagerOverviewController implements Initializable {
	private Main main;
	
	/*
	 * 此controller持有系统内所有client，hotel，hotelworker和market的列表
	 */
	private ObservableList<ClientModel> clientList = FXCollections.observableArrayList();
	private ArrayList<Integer> clientIDList=new ArrayList<Integer>();
	private ClientModel currentClientModel;
	private ObservableList<HotelModel> hotelList = FXCollections.observableArrayList();
	private ObservableList<HotelWorkerModel> workerList = FXCollections.observableArrayList();
	private ObservableList<MarketModel> marketList = FXCollections.observableArrayList();
	
	@FXML
	private Label clientNumLabel;
	
	@FXML
	private Label hotelNumLabel;
	
	@FXML
	private Label orderNumLabel;
	
	
	@FXML
	private TableView<ClientModel> clientTable;
	
	@FXML
	private TableColumn<ClientModel, String> clientIDColumn;
	
	@FXML
	private TableColumn<ClientModel, String> clientNameColumn;
	
	@FXML
	private TableColumn<ClientModel, String> clientContactColumn;
	
	@FXML
	private TableColumn<ClientModel, String> clientVipColumn;
	
	@FXML
	private TableColumn<ClientModel, String> clientCreditColumn;

	@FXML
	private TextField addClientNameTextField;
	
	@FXML
	private TextField 	addClientContactTextField;
	
	@FXML
	private TextField addClientVIPTextField;
	
	@FXML
	private TextField addClientCreditTextField;
	
	@FXML
	private TextField updateClientIDTextField;
	
	@FXML
	private TextField updateClientNameTextField;
	
	@FXML
	private TextField 	updateClientContactTextField;
	
	@FXML
	private TextField updateClientVIPTextField;
	
	@FXML
	private TextField updateClientCreditTextField;
	
	@FXML
	private TextField deleteClientIDTextField;
	
	@FXML
	private Label deleteClientNameLabel;
	
	@FXML
	private Label deleteClientContactLabel;
	
	@FXML
	private Label deleteClientVIPLabel;
	
	@FXML
	private Label deleteClientCreditLabel;
	
	@FXML
	private TableView<HotelModel> hotelTable;
	
	@FXML
	private TableColumn<HotelModel, String> hotelIDColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelNameColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelAddressColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelBusinessAddressColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelStarColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelScoreColumn;

	@FXML
	private TextField addHotelNameTextField;
	
	@FXML
	private TextField 	addHotelBusinessAddressTextField;
	
	@FXML
	private TextField addHotelAddressTextField;
	
	@FXML
	private TextField addHotelStarTextField;
	
	@FXML
	private TextField addHotelScoreTextField;
	
	@FXML
	private TextField updateHotelIDTextField;
	
	@FXML
	private TextField updateHotelNameTextField;
	
	@FXML
	private TextField 	updateHotelBusinessAddressTextField;
	
	@FXML
	private TextField updateHotelAddressTextField;
	
	@FXML
	private TextField updateHotelStarTextField;
	
	@FXML
	private TextField updateHotelScoreTextField;
	
	@FXML
	private TextField deleteHotelIDTextField;
	
	@FXML
	private Label deleteHotelNameLabel;
	
	@FXML
	private Label deleteHotelBusinessAddressLabel;
	
	@FXML
	private Label deleteHotelAddressLabel;
	
	@FXML
	private Label deleteHotelStarLabel;

	@FXML
	private Label deleteHotelScoreLabel;
	
	@FXML
	private TableView<HotelWorkerModel> hotelWorkerTable;
	
	@FXML
	private TableColumn<HotelWorkerModel, String> workerNameColumn;
	
	@FXML
	private TableColumn<HotelWorkerModel, String> workerHotelColumn;
	
	@FXML
	private TableColumn<HotelWorkerModel, String> workerContactColumn;
	
	@FXML
	private TextField addWorkerIDTextField;
	
	@FXML
	private TextField addWorkerNameTextField;
	
	@FXML
	private TextField addWorkerContactTextField;
	
	@FXML
	private TextField updateWorkerIDTextField;
	
	@FXML
	private TextField updateWorkerNameTextField;
	
	@FXML
	private TextField updateWorkerContactTextField;
	
	@FXML
	private TextField deleteWorkerIDTextField;
	
	@FXML
	private Label deleteWorkerNameLabel;
	
	@FXML
	private Label deleteWorkerContactLabel;
	
	@FXML
	private TableView<MarketModel> marketTable;
	
	@FXML
	private TableColumn<MarketModel, String> marketIDColumn;
	@FXML
	private TableColumn<MarketModel, String> marketNameColumn;
	
	@FXML
	private TableColumn<MarketModel, String> marketContactColumn;
	
	@FXML
	private TextField addMarketNameTextField;
	
	@FXML
	private TextField addMarketContactTextField;
	
	@FXML
	private TextField updateMarketIDTextField;
	
	@FXML
	private TextField updateMarketNameTextField;
	
	@FXML
	private TextField updateMarketContactTextField;
	
	@FXML
	private TextField deleteMarketIDTextField;
	
	@FXML
	private Label deleteMarketNameLabel;
	
	@FXML
	private Label deleteMarketContactLabel;
	
	
	@FXML
	private void addClient(){
		int id = clientList.size()+1;
		String name = addClientNameTextField.getText();
		String contact = addClientContactTextField.getText();
		VIPInfo info= null;
		if(!addClientVIPTextField.getText().equals("")){
			String[] vipinfo = addClientVIPTextField.getText().split("//");
			info = new VIPInfo(vipinfo[0].equals("普通会员")? VIPType.NORMAL:VIPType.Enterprise, vipinfo[1]);
		}
		int credit = Integer.parseInt(addClientCreditTextField.getText());
		
		ClientModel client = new ClientModel(id, name, contact, info, credit);
		clientTable.getItems().add(client);
	}
	
	@FXML
	private void updateSearchClient(){
		String id = updateClientIDTextField.getText();
		boolean isSuccess = false;
		
		for (ClientModel client : clientList) {
			if (client.getID().equals(id)) {
				currentClientModel = client;
				updateClientNameTextField.setText(client.getName());
				updateClientContactTextField.setText(client.getContact());
				updateClientVIPTextField.setText(client.getVIPinfo());
				updateClientCreditTextField.setText(client.getCredit());
				
				isSuccess = true;
			}
		}
		if(!isSuccess){
			updateClientNameTextField.setText("");
			updateClientContactTextField.setText("");
			updateClientVIPTextField.setText("");
			updateClientCreditTextField.setText("");
		
		}
	}
	
	@FXML
	private void updateClient(){
		clientList.remove(currentClientModel);
		
		currentClientModel.setName(updateClientNameTextField.getText());
		currentClientModel.setContact(updateClientContactTextField.getText());
		String tempvipinfo = updateClientVIPTextField.getText();
		VIPInfo info = null;
		if(!tempvipinfo.equals("")){
			String[] tempInfos = tempvipinfo.split("//");
			info = new VIPInfo(tempInfos[0]=="普通会员"? VIPType.NORMAL:VIPType.Enterprise,tempInfos[1]);
		}
		currentClientModel.setVIPinfo(info);
		currentClientModel.setCredit(Integer.parseInt(updateClientCreditTextField.getText()));
		
		clientList.add(currentClientModel);
	}
	
	@FXML
	private void deleteSearchClient(){
		String id = deleteClientIDTextField.getText();
		boolean isSuccess = false;
		
		for (ClientModel client : clientList) {
			if (client.getID().equals(id)) {
				currentClientModel = client;
				deleteClientNameLabel.setText(client.getName());
				deleteClientContactLabel.setText(client.getContact());
				deleteClientVIPLabel.setText(client.getVIPinfo());
				deleteClientCreditLabel.setText(client.getCredit());
				
				isSuccess = true;
			}
		}
		if(!isSuccess){
			deleteClientNameLabel.setText("");
			deleteClientContactLabel.setText("");
			deleteClientVIPLabel.setText("");
			deleteClientCreditLabel.setText("");
		
		}
	}
	
	@FXML
	private void deleteClient(){
		if(clientList.contains(currentClientModel)){
			clientList.remove(currentClientModel);
			clientIDList.remove(clientIDList.indexOf(currentClientModel.getID())+1);
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("不存在该客户！");
			alert.setHeaderText("出错了！");
			alert.show();
		}
	}
	
	@FXML
	private void addHotel(){
		//TODO
	}
	
	@FXML
	private void updateHotel(){
		//TODO
	}
	
	@FXML
	private void updateSearchHotel(){
		//TODO
	}
	
	@FXML
	private void deleteSearchHotel(){
		
	}
	
	@FXML
	private void deleteHotel(){
		//TODO
	}
	
	@FXML
	private void addHotelWorker(){
		//TODO
	}
	
	@FXML
	private void updateSearchHotelWorker(){
		
	}
	@FXML
	private void updateHotelWorker(){
		//TODO
	}
	
	@FXML
	private void deleteSearchHotelWorker(){
		
	}
	
	@FXML
	private void deleteHotelWorker(){
		//TODO
	}
	
	@FXML
	private void addMarket(){
		//TODO
	}
	
	@FXML
	private void updateSearchMarket(){
		
	}
	@FXML
	private void updateMarket(){
		//TODO
	}
	
	@FXML
	private void deleteSearchMarket(){
		
	}
	@FXML
	private void deleteMarket(){
		//TODO
	}
	
	public ManagerOverviewController() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main,WebManagerVO vo) {
		this.main = main;
		
		RemoteHelper remoteHelper = RemoteHelper.getInstance();
		
		clientList.add(new ClientModel(1, "张三", "18888888888", new VIPInfo(VIPType.NORMAL, "1999/01/01"), 0));
		clientList.add(new ClientModel(2, "李四", "13333333333",null, 0));
		
		for(ClientModel clientModel:clientList){
			clientIDList.add(Integer.parseInt(clientModel.getID()));
		}
		clientIDColumn.setCellValueFactory(celldata->celldata.getValue().iDProperty());
		clientNameColumn.setCellValueFactory(celldata->celldata.getValue().nameProperty());
		clientContactColumn.setCellValueFactory(celldata->celldata.getValue().contactProperty());
		clientVipColumn.setCellValueFactory(celldata->celldata.getValue().viProperty());
		clientCreditColumn.setCellValueFactory(celldata->celldata.getValue().creditProperty());
		
		clientTable.setItems(clientList);
	}
}
