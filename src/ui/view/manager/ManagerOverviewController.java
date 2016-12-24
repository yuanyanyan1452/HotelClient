package ui.view.manager;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import objects.*;
import objects.VIPInfo.*;
import rmi.RemoteHelper;
import ui.model.*;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.*;

public class ManagerOverviewController implements Initializable {
	private Main main;
	// 实时刷新
	private boolean able;

	class Updatable {
		void update() {
			RemoteHelper remoteHelper = RemoteHelper.getInstance();
			// 实时更新时间
			timeLabel.setText(format.format(Calendar.getInstance().getTime()));

			// 实时更新统计数据
			clientNumLabel.setText(String.valueOf(clientList.size()));
			hotelNumLabel.setText(String.valueOf(hotelList.size()));
			 try {
				orderNumLabel.setText(String.valueOf(remoteHelper.getManageBLService().getordernumber()));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	// 开始刷新
	public final synchronized void advance(Updatable renderer) {
		if (able) {
			return; // 防止启动多个线程
		}
		able = true;
		new Thread(() -> {
			Semaphore semaphore = new Semaphore(1);
			while (able) {
				try {
					semaphore.acquire();
					Platform.runLater(() -> {
						renderer.update();
						semaphore.release();
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				;
			}
		}).start();

	}

	// 结束进程
	public void misfire() {
		able = false;
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
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FXML
	private Label timeLabel;

	@FXML
	private Label clientNumLabel;

	@FXML
	private Label hotelNumLabel;

	@FXML
	private Label orderNumLabel;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private Tab clientTab;

	@FXML
	private Tab hotelTab;

	@FXML
	private Tab hotelworkerTab;

	@FXML
	private Tab marketTab;

	@FXML
	private TableView<ClientModel> clientTable;

	@FXML
	private TableColumn<ClientModel, String> clientIDColumn;

	@FXML
	private TableColumn<ClientModel, String> clientNameColumn;

	@FXML
	private TableColumn<ClientModel, String> clientContactColumn;

	@FXML
	private TableColumn<ClientModel, String> clientVipInfoColumn;


	@FXML
	private TableColumn<ClientModel, String> clientVipTypeColumn;

	@FXML
	private TextField updateClientIDTextField;

	@FXML
	private TextField updateClientNameTextField;

	@FXML
	private TextField updateClientContactTextField;

	@FXML
	private TextField updateClientVIPTextField;

	@FXML
	private ComboBox<String> updatevipTypeCombobox;

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
	private TextField addHotelBusinessAddressTextField;

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
	private TextField updateHotelBusinessAddressTextField;

	@FXML
	private TextField updateHotelAddressTextField;

	@FXML
	private TextField updateHotelStarTextField;


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
	private void exit(){
		misfire();
		main.exitSystem();
	}
	
	@FXML
	private void updateSearchClient() {
		String id = updateClientIDTextField.getText();
		boolean isSuccess = false;

		for (ClientModel client : clientList) {
			if (client.getID().equals(id)) {
				currentClientModel = client;
				updateClientNameTextField.setText(client.getName());
				updateClientContactTextField.setText(client.getContact());
				updatevipTypeCombobox.setValue(client.getVIPType());
				updateClientVIPTextField.setText(client.getVipInfo());

				isSuccess = true;
			}
		}
		if (!isSuccess) {
			updateClientNameTextField.setText("");
			updateClientContactTextField.setText("");
			updatevipTypeCombobox.setValue("");
			updateClientVIPTextField.setText("");
			
			AlertUtil.showWarningAlert("不存在该客户！");
		}
	}

	@FXML
	private void updateClient() {
		boolean isSuccess = false;
		// 更新表格
		int index;
		for (index = 0; index < clientList.size(); index++) {
			if (clientList.get(index).getID().equals(currentClientModel.getID())) {
				clientList.get(index).setName(updateClientNameTextField.getText());
				clientList.get(index).setContact(updateClientContactTextField.getText());
				clientList.get(index).setVIPtype(updatevipTypeCombobox.getValue());
				clientList.get(index).setVipInfo(updateClientVIPTextField.getText());
				isSuccess = true;
				break;
			}
		}
		if (!isSuccess) {
			AlertUtil.showWarningAlert("未指定客户或客户不存在！");
			return;
		}
		// 更新底层数据
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			ClientVO vo = helper.getClientBLService().client_checkInfo(Integer.parseInt(currentClientModel.getID()));
			vo.setclient_name(updateClientNameTextField.getText());
			vo.setcontact(updateClientContactTextField.getText());
			VIPInfo info = new VIPInfo();
			if (updatevipTypeCombobox.getValue().equals("非会员")) {
				info = null;
			} else {
				info.setType(updatevipTypeCombobox.getValue().equals("普通会员") ? VIPType.NORMAL : VIPType.Enterprise);
				info.setInfo(updateClientVIPTextField.getText());
			}
			vo.setvipinfo(info);
			ResultMessage message = helper.getClientBLService().client_updateInfo(vo);
			if (message == ResultMessage.Success) {
				AlertUtil.showInformationAlert("修改客户信息成功！");
			} else {
				AlertUtil.showErrorAlert("修改客户信息失败！");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void addHotel() {

		// 添加底层数据
		HotelVO vo = new HotelVO();
		vo.setname(addHotelNameTextField.getText());
		vo.setaddress(addHotelAddressTextField.getText());
		vo.setbussiness_address(addHotelBusinessAddressTextField.getText());
		vo.setstar(addHotelStarTextField.getText());
		vo.setscore(addHotelScoreTextField.getText());

		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			ResultMessage message = helper.getManageBLService().manage_addHotel(vo);
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("添加酒店失败！");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 添加表格项
		HotelModel model = new HotelModel();
		model.setID(Integer.parseInt(hotelList.get(hotelList.size()-1).getID())+1);
		model.setHotelName(vo.getname());
		model.setAddress(vo.getaddress());
		model.setBusinessAddress(vo.getbussiness_address());
		model.setStar(vo.getstar());
		model.setScore(vo.getscore());
		hotelList.add(model);

		AlertUtil.showInformationAlert("添加酒店成功！");
	}

	@FXML
	private void updateSearchHotel() {
		String id = updateHotelIDTextField.getText();
		boolean isSuccess = false;

		for (HotelModel model : hotelList) {
			if (model.getID().equals(id)) {
				currentHotelModel = model;
				updateHotelNameTextField.setText(model.getHotelName());
				updateHotelBusinessAddressTextField.setText(model.getBusinessAddress());
				updateHotelAddressTextField.setText(model.getAddress());
				updateHotelStarTextField.setText(model.getStar());
				isSuccess = true;
			}
		}
		if (!isSuccess) {
			updateHotelNameTextField.setText("");
			updateHotelBusinessAddressTextField.setText("");
			updateHotelAddressTextField.setText("");
			updateHotelStarTextField.setText("");
			AlertUtil.showWarningAlert("不存在该酒店！");
		}
	}

	@FXML
	private void updateHotel() {
		if (currentHotelModel == null) {
			AlertUtil.showWarningAlert("未指定酒店！");
			return;
		}
		// 更新底层数据
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			if (!updateHotelIDTextField.getText().equals(currentHotelModel.getID())) {
				AlertUtil.showWarningAlert("不可更改酒店ID！");
				return;
			}
			int id = Integer.parseInt(currentHotelModel.getID());
			HotelVO vo = helper.getHotelBLService().hotel_getInfo(id);
			vo.setname(updateHotelNameTextField.getText());
			vo.setbussiness_address(updateHotelBusinessAddressTextField.getText());
			vo.setaddress(updateHotelAddressTextField.getText());
			vo.setstar(updateHotelStarTextField.getText());
			ResultMessage message = helper.getHotelBLService().hotel_updateInfo(vo);
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("更新酒店失败！");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// 更新表格
		boolean isSuccess = false;
		int index;
		for (index = 0; index < hotelList.size(); index++) {
			if (hotelList.get(index).getID().equals(currentHotelModel.getID())) {
				hotelList.get(index).setHotelName(updateHotelNameTextField.getText());
				hotelList.get(index).setAddress(updateHotelAddressTextField.getText());
				hotelList.get(index).setBusinessAddress(updateHotelBusinessAddressTextField.getText());
				hotelList.get(index).setStar(updateHotelStarTextField.getText());
				isSuccess = true;
				break;
			}
		}
		if (!isSuccess) {
			AlertUtil.showWarningAlert("未指定酒店或酒店不存在！");
			return;
		}
		AlertUtil.showInformationAlert("更新酒店成功！");
	}

	@FXML
	private void addHotelWorker() {
		// 添加底层数据
		HotelWorkerVO vo = new HotelWorkerVO();
		vo.setname(addWorkerNameTextField.getText());
		vo.sethotelid(Integer.parseInt(addWorkerIDTextField.getText()));
		vo.setcontact(addWorkerContactTextField.getText());
		vo.setusername(addWorkerUsernameTextField.getText());
		vo.setpassword(addWorkerPasswordTextField.getText());
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			ResultMessage message = helper.getManageBLService().manage_addHotelWorker(vo);
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("添加酒店工作人员失败！可能是由于酒店不存在或者已经存在一个工作人员。");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 添加表格项
		HotelWorkerModel model = new HotelWorkerModel();
		model.setHotelid(workerList.size() + 1);
		model.setName(vo.getname());
		model.setContact(vo.getcontact());
		workerList.add(model);

		AlertUtil.showInformationAlert("添加酒店工作人员成功！");
	}

	@FXML
	private void updateSearchHotelWorker() {
		String id = updateWorkerIDTextField.getText();
		boolean isSuccess = false;

		for (HotelWorkerModel model : workerList) {
			if (model.getHotelid().equals(id)) {
				currentHotelWorkerModel = model;
				updateWorkerNameTextField.setText(model.getName());
				updateWorkerContactTextField.setText(model.getContact());
				isSuccess = true;
			}
		}
		if (!isSuccess) {
			updateWorkerNameTextField.setText("");
			updateWorkerContactTextField.setText("");
			AlertUtil.showWarningAlert("不存在该酒店工作人员！");
		}
	}

	@FXML
	private void updateHotelWorker() {
		if (currentHotelWorkerModel == null) {
			AlertUtil.showWarningAlert("未指定酒店工作人员！");
			return;
		}
		// 更新底层数据
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			if (!updateWorkerIDTextField.getText().equals(currentHotelWorkerModel.getHotelid())) {
				AlertUtil.showWarningAlert("不可更改工作人员所属酒店ID！");
				return;
			}
			int id = Integer.parseInt(currentHotelWorkerModel.getHotelid());
			HotelWorkerVO vo = helper.getManageBLService().manage_searchHotelWorkerByHotelid(id);
			vo.setname(updateWorkerNameTextField.getText());
			vo.setcontact(updateWorkerContactTextField.getText());
			ResultMessage message = helper.getManageBLService().manage_updateHotelWorker(vo);
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("更新酒店工作人员失败！");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// 更新表格
		boolean isSuccess = false;
		int index;
		for (index = 0; index < workerList.size(); index++) {
			if (workerList.get(index).getHotelid().equals(currentHotelWorkerModel.getHotelid())) {
				workerList.get(index).setName(updateWorkerNameTextField.getText());
				workerList.get(index).setContact(updateWorkerContactTextField.getText());
				isSuccess = true;
				break;
			}
		}
		if (!isSuccess) {
			AlertUtil.showWarningAlert("未指定酒店工作人员或酒店工作人员不存在！");
			return;
		}
		AlertUtil.showInformationAlert("更新酒店工作人员成功！");
	}

	@FXML
	private void addMarket() {
		// 添加底层数据
		WebMarketVO vo = new WebMarketVO();
		vo.setname(addMarketNameTextField.getText());
		vo.setcontact(addMarketContactTextField.getText());
		vo.setusername(addMarketUsernameTextField.getText());
		vo.setpassword(addMarketPasswordTextField.getText());

		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			ResultMessage message = helper.getManageBLService().manage_addMarketWorker(vo);
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("添加网站营销人员失败！");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 添加表格项
		MarketModel model = new MarketModel();
		model.setID(marketList.size() + 1);
		model.setName(vo.getname());
		model.setContact(vo.getcontact());
		marketList.add(model);

		AlertUtil.showInformationAlert("添加网站营销人员成功！");
	}

	@FXML
	private void updateSearchMarket() {
		String id = updateMarketIDTextField.getText();
		boolean isSuccess = false;

		for (MarketModel model : marketList) {
			if (model.getID().equals(id)) {
				currentMarketModel = model;
				updateMarketNameTextField.setText(model.getName());
				updateMarketContactTextField.setText(model.getContact());
				isSuccess = true;
			}
		}
		if (!isSuccess) {
			updateMarketNameTextField.setText("");
			updateMarketContactTextField.setText("");
			AlertUtil.showWarningAlert("不存在该网站营销人员！");
		}
	}

	@FXML
	private void updateMarket() {
		if (currentMarketModel == null) {
			AlertUtil.showWarningAlert("未指定网站营销人员！");
			return;
		}
		// 更新底层数据
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			if (!updateMarketIDTextField.getText().equals(currentMarketModel.getID())) {
				AlertUtil.showWarningAlert("不可更改网站营销人员ID！");
				return;
			}
			int id = Integer.parseInt(currentMarketModel.getID());
			WebMarketVO vo = helper.getManageBLService().manage_searchMarketWorkerByWebmarketid(id);
			vo.setname(updateMarketNameTextField.getText());
			vo.setcontact(updateMarketContactTextField.getText());
			ResultMessage message = helper.getManageBLService().manage_updateMarketWorker(vo);
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("更新网站营销人员失败！");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// 更新表格
		boolean isSuccess = false;
		int index;
		for (index = 0; index < marketList.size(); index++) {
			if (marketList.get(index).getID().equals(currentMarketModel.getID())) {
				marketList.get(index).setName(updateMarketNameTextField.getText());
				marketList.get(index).setContact(updateMarketContactTextField.getText());
				isSuccess = true;
				break;
			}
		}
		if (!isSuccess) {
			AlertUtil.showWarningAlert("未指定网站营销人员或网站营销人员不存在！");
			return;
		}
		AlertUtil.showInformationAlert("更新网站营销人员成功！");
	}

	public ManagerOverviewController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main, WebManagerVO vo, Stage stage) {
		this.main = main;
		// 初始化combobox
		ObservableList<String> viptypeList = updatevipTypeCombobox.getItems();
		viptypeList.addAll("非会员", "普通会员", "企业会员");

		// 初始化所有表格
		updateClientTable();
		updateHotelTable();
		updateHotelWorkerTable();
		updateMarketTable();

		// 实时更新时间线程
		Updatable renderer = new Updatable();
		advance(renderer);

	}

	public void updateClientTable() {
		RemoteHelper remoteHelper = RemoteHelper.getInstance();

		try {
			// 得到所有的clientvo
			ObservableList<ClientVO> clientVOs = FXCollections
					.observableArrayList(remoteHelper.getManageBLService().getallclientvo());
			// 将得到的vo转成model存在tableview里
			clientList.clear();
			for (ClientVO clientVO : clientVOs) {
				ClientModel model = new ClientModel();
				model.setID(clientVO.getclientid());
				model.setName(clientVO.getclient_name());
				model.setContact(clientVO.getcontact());
				model.setCredit(clientVO.getcredit());
				if (clientVO.getvipinfo() == null) {
					model.setVIPtype("非会员");
					model.setVipInfo("");
				} else {
					model.setVIPtype(clientVO.getvipinfo().getType() == VIPType.NORMAL ? "普通会员" : "企业会员");
					model.setVipInfo(clientVO.getvipinfo().getInfo());
				}
				clientList.add(model);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		// 给表格的每一列赋值
		clientIDColumn.setCellValueFactory(celldata -> celldata.getValue().iDProperty());
		clientNameColumn.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
		clientContactColumn.setCellValueFactory(celldata -> celldata.getValue().contactProperty());
		clientVipTypeColumn.setCellValueFactory(celldata -> celldata.getValue().vipTypeProperty());
		clientVipInfoColumn.setCellValueFactory(celldata -> celldata.getValue().vipInfoProperty());

		clientTable.setItems(clientList);
	}

	public void updateHotelTable() {
		RemoteHelper remoteHelper = RemoteHelper.getInstance();

		try {
			// 得到所有的hotelvo
			ObservableList<HotelVO> hotelVOs = FXCollections.observableArrayList(remoteHelper.getManageBLService().getallhotelvo());
			// 将得到的vo转成model存在tableview里
			hotelList.clear();
			for (HotelVO hotelVO : hotelVOs) {
				HotelModel model = new HotelModel();
				model.setID(hotelVO.getid());
				model.setHotelName(hotelVO.getname());
				model.setBusinessAddress(hotelVO.getbussiness_address());
				model.setAddress(hotelVO.getaddress());
				model.setStar(hotelVO.getstar());
				if (hotelVO.getscore()!=null) {
					model.setScore(hotelVO.getscore().split(",")[0]);
				}
				hotelList.add(model);
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// 给表格的每一列赋值
		hotelIDColumn.setCellValueFactory(celldata -> celldata.getValue().idProperty());
		hotelNameColumn.setCellValueFactory(celldata -> celldata.getValue().hotelNameProperty());
		hotelBusinessAddressColumn.setCellValueFactory(celldata -> celldata.getValue().businessAddressProperty());
		hotelAddressColumn.setCellValueFactory(celldata -> celldata.getValue().addressProperty());
		hotelStarColumn.setCellValueFactory(celldata -> celldata.getValue().starProperty());

		hotelTable.setItems(hotelList);
	}

	public void updateHotelWorkerTable() {
		RemoteHelper remoteHelper = RemoteHelper.getInstance();

	
		
		try {
			// 得到所有的hotelworkervo
			ObservableList<HotelWorkerVO> hotelWorkerVOs = FXCollections.observableArrayList(remoteHelper.getManageBLService().getallhotelworkervo());
			// 将得到的vo转成model存在tableview里
			workerList.clear();
			for (HotelWorkerVO hotelWorkerVO : hotelWorkerVOs) {
				HotelWorkerModel model = new HotelWorkerModel();
				model.setHotelid(hotelWorkerVO.gethotelid());
				model.setName(hotelWorkerVO.getname());
				model.setContact(hotelWorkerVO.getcontact());
				workerList.add(model);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		

		// 给表格的每一列赋值
		workerHotelColumn.setCellValueFactory(celldata -> celldata.getValue().hotelidProperty());
		workerNameColumn.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
		workerContactColumn.setCellValueFactory(celldata -> celldata.getValue().contactProperty());

		hotelWorkerTable.setItems(workerList);
	}

	public void updateMarketTable() {
		RemoteHelper remoteHelper = RemoteHelper.getInstance();

		
		try {
			// 得到所有的marketvo
			ObservableList<WebMarketVO> vos = FXCollections.observableArrayList(remoteHelper.getManageBLService().getallwebmarketvo());
			// 将得到的vo转成model存在tableview里
			marketList.clear();
			for (WebMarketVO marketVO : vos) {
				MarketModel model = new MarketModel();
				model.setID(marketVO.getwebmarketid());
				model.setName(marketVO.getname());
				model.setContact(marketVO.getcontact());
				marketList.add(model);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

		// 给表格的每一列赋值
		marketIDColumn.setCellValueFactory(celldata -> celldata.getValue().idProperty());
		marketNameColumn.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
		marketContactColumn.setCellValueFactory(celldata -> celldata.getValue().contactProperty());

		marketTable.setItems(marketList);
	}
}
