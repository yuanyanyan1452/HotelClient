package ui.view.manager;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
	//实时刷新
	private boolean able;
	class Updatable {
		void update(double moment){
			timeLabel.setText(format.format(Calendar.getInstance().getTime()));
//			clientNumLabel.setText(remoteHelper.getClientBLService().);
//			hotelNumLabel.setText(remoteHelper.getHotelBLService().);
//			orderNumLabel.setText(remoteHelper.getOrderBLService().);
		}
	}
	/*
	 * 此controller持有系统内所有client，hotel，hotelworker和market的列表
	 */
	private ObservableList<ClientModel> clientList = FXCollections.observableArrayList();
	private ClientModel currentClientModel;
	private ObservableList<HotelModel> hotelList = FXCollections.observableArrayList();
	private HotelModel currentHotelModel;
	private ObservableList<HotelWorkerModel> workerList = FXCollections.observableArrayList();
	private HotelWorkerModel currentHotelWorkerModel;
	private ObservableList<MarketModel> marketList = FXCollections.observableArrayList();
	private MarketModel currentMarketModel;
	private SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@FXML
	private Label timeLabel;
	
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
	private TextField addWorkerUsernameTextField;
	
	@FXML
	private TextField addWorkerPasswordTextField;
	
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
	private TextField addMarketUsernameTextField;
	
	@FXML
	private TextField addMarketPasswordTextField;
	
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

	//开始刷新
	public final synchronized void advance(Updatable renderer){
		if (able) {
			return;  //防止启动多个线程
		}
		able = true;
		new Thread(() -> {
			Semaphore semaphore = new Semaphore(1);
			while(able){
				try{
					long now = System.nanoTime();
					double moment = now * 0.000000001;
					semaphore.acquire();
					Platform.runLater(()->{
						renderer.update(moment);
						semaphore.release();
						});
				}catch(InterruptedException e){
					e.printStackTrace();
				};
			}
		}).start();
		
	}
	//结束进程
	public void misfire(){
		able = false;
	}
	public void setMain(Main main,WebManagerVO vo,Stage stage) {
		this.main = main;
		
		RemoteHelper remoteHelper = RemoteHelper.getInstance();
		
		
		
		
		clientList.add(new ClientModel(1, "张三", "18888888888", new VIPInfo(VIPType.NORMAL, "1999/01/01"), 0));
		clientList.add(new ClientModel(2, "李四", "13333333333",null, 0));
		
		clientIDColumn.setCellValueFactory(celldata->celldata.getValue().iDProperty());
		clientNameColumn.setCellValueFactory(celldata->celldata.getValue().nameProperty());
		clientContactColumn.setCellValueFactory(celldata->celldata.getValue().contactProperty());
		clientVipColumn.setCellValueFactory(celldata->celldata.getValue().viProperty());
		clientCreditColumn.setCellValueFactory(celldata->celldata.getValue().creditProperty());
		
		clientTable.setItems(clientList);
		
		//实时更新时间线程
		Updatable renderer = new Updatable();
		advance(renderer);
		
		//退出时结束进程
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				misfire();
			}
		});
	}

	
}

