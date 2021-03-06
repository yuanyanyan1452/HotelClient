package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import objects.RoomType;
import rmi.RemoteHelper;
import ui.model.HotelModel;
import ui.util.AlertUtil;
import ui.util.LocationUtil;
import ui.util.RoomTypeUtil;
import ui.view.Main;
import vo.ClientVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomVO;

public class ClientSearchHotelController implements Initializable {
	private Main main;
	private ClientVO currentClient;
	private HotelVO currentHotel;
	private ArrayList<HotelVO> currentHotelList;
	RemoteHelper helper = RemoteHelper.getInstance();

	@FXML
	private TableView<HotelModel> hotelTable;
	
	@FXML
	private TableColumn<HotelModel, String> hotelnamecolumn;

	@FXML
	private TableColumn<HotelModel, String> businessaddresscolumn;

	@FXML
	private TableColumn<HotelModel, String> addresscolumn;

	@FXML
	private TableColumn<HotelModel, String> starcolumn;

	@FXML
	private TableColumn<HotelModel, String> scorecolumn;

	@FXML
	private TableColumn<HotelModel, String> orderstatecolumn;

	@FXML
	private TableColumn<HotelModel, String> cheapestroomcolumn;
	
	@FXML
	private ComboBox<String> locationButton;

	@FXML
	private ComboBox<String> businessAddressButton;

	@FXML
	private ComboBox<String> roomtypeButton;

	@FXML
	private ComboBox<String> starButton;

	@FXML
	private ComboBox<String> lowpriceButton;

	@FXML
	private ComboBox<String> highpriceButton;

	@FXML
	private ComboBox<String> lowscoreButton;

	@FXML
	private ComboBox<String> highscoreButton;

	@FXML
	private Button searchButton;

	@FXML
	private RadioButton everBookedButton;

	@FXML
	private TextField hotelnameTextField;

	@FXML
	private void initialize() {

	}

	@FXML
	private void search() {
		ObservableList<HotelModel> hotelModels = FXCollections.observableArrayList();
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			if (currentHotelList == null) {
				AlertUtil.showWarningAlert("未设置城市及商圈！");
				return;
			}
			// 只搜索自己预定过的酒店
			if (everBookedButton.isSelected()) {
				currentHotelList = helper.getHotelBLService().searchpreviousHotelList(currentHotelList,currentClient.getclientid());
			}
			// 按酒店名称搜索
			if (!hotelnameTextField.getText().isEmpty()) {
				currentHotelList = helper.getHotelBLService().searchHotelByname(currentHotelList,
						hotelnameTextField.getText());
			}
			// 按房间类型查找
			if ( !roomtypeButton.getValue().equals("不限")) {
				currentHotelList = helper.getHotelBLService().searchHotelByroom(currentHotelList,
						roomtypeButton.getValue());
			}
			// 按星级查找
			if ( !starButton.getValue().equals("不限")) {
				currentHotelList = helper.getHotelBLService().searchHotelBystar(currentHotelList,
						starButton.getValue());
			}
			// 按价格查找
			if (lowpriceButton.getValue() != null && highpriceButton.getValue() != null) {
				currentHotelList = helper.getHotelBLService().searchHotelByprice(currentHotelList,
						Integer.parseInt(lowpriceButton.getValue()), Integer.parseInt(highpriceButton.getValue()));
			}
			// 按评分查找
			if ((lowscoreButton.getValue() != null && highscoreButton.getValue() != null)) {
				currentHotelList = helper.getHotelBLService().searchHotelByscore(currentHotelList,
						Double.parseDouble(lowscoreButton.getValue()), Double.parseDouble(highscoreButton.getValue()));
			}

			if (currentHotelList.isEmpty()) {
				AlertUtil.showInformationAlert("未找到符合条件的酒店！");
			} else {
				for (HotelVO vo : currentHotelList) {
					HotelModel model = hotelvoToModel(vo);
					hotelModels.add(model);
				}
			}
			hotelTable.setItems(hotelModels);

			// 一次搜索完毕后将currenthotellist重置为当前地址的所有的酒店
			currentHotelList = helper.getHotelBLService().searchHotelBylocation(locationButton.getValue(),
					businessAddressButton.getValue());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	// 直接跳转到生成订单界面
	@FXML
	private void gotoGenerateOrder() {
		if(currentClient.getcredit()>=0){
		main.gotoGenerateOrder(currentHotel,currentClient);
		}
		else{
			AlertUtil.showWarningAlert("对不起，您的信用值不足");
		}
	}

	public ClientSearchHotelController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void gotodetailinfo(int  hotelid){
		try {
			main.gotoHotelDetailInfo(helper.getHotelBLService().hotel_getInfo(hotelid),currentClient.getclientid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setMain(Main main, ClientVO clientVO) {
		this.main = main;
		currentClient = clientVO;
		// 初始化组合框
		ObservableList<String> cities = FXCollections.observableArrayList();
		cities.addAll(LocationUtil.getCities());
		locationButton.setItems(cities);
		locationButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (locationButton.getValue() != null) {
					businessAddressButton.getItems().setAll(LocationUtil.getBusinessAddress(locationButton.getValue()));
				}
			}
		});

		roomtypeButton.getItems().add("不限");
		roomtypeButton.getItems().addAll(RoomTypeUtil.getAllRoomType());
		roomtypeButton.setValue("不限");

		starButton.getItems().addAll("不限", "一星级", "二星级", "三星级", "四星级", "五星级");
		starButton.setValue("不限");

		ObservableList<String> prices = FXCollections.observableArrayList();
		prices.addAll("0", "100", "200", "300", "400", "500", "600", "700", "800", "900");
		lowpriceButton.setItems(prices);
		lowpriceButton.setVisibleRowCount(5);
		highpriceButton.setVisibleRowCount(5);
		lowpriceButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> pricelist = FXCollections.observableArrayList(prices);

				if (lowpriceButton.getValue() != null) {
					String lowprice = lowpriceButton.getValue();
					int index = prices.indexOf(lowprice);
					for (int i = 0; i <= index; i++) {
						pricelist.remove(0);
					}
				}
				highpriceButton.setItems(pricelist);
			}
		});

		ObservableList<String> scores = FXCollections.observableArrayList();
		scores.addAll("0.0", "1.0", "2.0", "3.0", "4.0", "5.0");
		lowscoreButton.setItems(scores);
		lowscoreButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> scoreList = FXCollections.observableArrayList(scores);

				if (lowscoreButton.getValue() != null) {
					String lowscore = lowscoreButton.getValue();
					int index = scores.indexOf(lowscore);
					for (int i = 0; i <= index; i++) {
						scoreList.remove(0);
					}
				}
				highscoreButton.setItems(scoreList);
			}
		});

		// 设置双击后取消选择radiobutton
		everBookedButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					everBookedButton.setSelected(false);
				}
			}
		});

		// 设置城市,商圈完毕后得到一个list
		
		businessAddressButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (locationButton != null && locationButton.getValue() != null && businessAddressButton != null
						&& businessAddressButton.getValue() != null) {
					try {
						currentHotelList = helper.getHotelBLService().searchHotelBylocation(locationButton.getValue(),
								businessAddressButton.getValue());
						ObservableList<HotelModel> models = FXCollections.observableArrayList();
						for (HotelVO vo : currentHotelList) {
							HotelModel model = hotelvoToModel(vo);
							models.add(model);
						}
						hotelTable.setItems(models);

					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// 初始化hotelTable
		ObservableList<HotelModel> models = FXCollections.observableArrayList();
		hotelTable.setItems(models);
		//监听
		hotelnamecolumn.setCellValueFactory(celldata -> celldata.getValue().hotelNameProperty());
		hotelnamecolumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						if(cell.getIndex()<=models.size()){
						int hotelid=(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
						gotodetailinfo(hotelid);
					}
					}
					else if(t.getClickCount() == 1){
						try {
							if(cell.getIndex()<=models.size()){
							currentHotel=helper.getHotelBLService().hotel_getInfo(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
						} 
						} catch (NumberFormatException | RemoteException e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		businessaddresscolumn.setCellValueFactory(celldata -> celldata.getValue().businessAddressProperty());
		businessaddresscolumn
				.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

					@Override
					public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
						TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
						cell.setOnMouseClicked((MouseEvent t) -> {
							if (t.getClickCount() == 2) {
								if(cell.getIndex()<=currentHotelList.size()){
								int hotelid=(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
								gotodetailinfo(hotelid);
							}
							}
							else if(t.getClickCount() == 1){
								try {
									if(cell.getIndex()<=currentHotelList.size()){
									currentHotel=helper.getHotelBLService().hotel_getInfo(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
								} 
								}catch (NumberFormatException | RemoteException e) {
									e.printStackTrace();
								}
							}
						});
						return cell;
					}
				});
		addresscolumn.setCellValueFactory(celldata -> celldata.getValue().addressProperty());
		addresscolumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						if(cell.getIndex()<=currentHotelList.size()){
						int hotelid=(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
						gotodetailinfo(hotelid);
					}
					}
					else if(t.getClickCount() == 1){
						try {
							if(cell.getIndex()<=currentHotelList.size()){
							currentHotel=helper.getHotelBLService().hotel_getInfo(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
						} 
						} catch (NumberFormatException | RemoteException e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		cheapestroomcolumn.setCellValueFactory(celldata -> celldata.getValue().cheapestRoomPriceProperty());
		cheapestroomcolumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						if(cell.getIndex()<=currentHotelList.size()){
						int hotelid=(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
						gotodetailinfo(hotelid);
					}
					}
					else if(t.getClickCount() == 1){
						try {
							if(cell.getIndex()<=currentHotelList.size()){
								currentHotel=helper.getHotelBLService().hotel_getInfo(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
							} 
						} catch (NumberFormatException | RemoteException e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		starcolumn.setCellValueFactory(celldata -> celldata.getValue().starProperty());
		starcolumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						if(cell.getIndex()<=currentHotelList.size()){
						int hotelid=(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
						gotodetailinfo(hotelid);
					}
					}
					else if(t.getClickCount() == 1){
						try {
							if(cell.getIndex()<=currentHotelList.size()){
								currentHotel=helper.getHotelBLService().hotel_getInfo(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
							} 
						} catch (NumberFormatException | RemoteException e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		scorecolumn.setCellValueFactory(celldata -> celldata.getValue().scoreProperty());
		scorecolumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						if(cell.getIndex()<=currentHotelList.size()){
						int hotelid=(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
						gotodetailinfo(hotelid);
					}
					}
					else if(t.getClickCount() == 1){
						try {
							if(cell.getIndex()<=currentHotelList.size()){
								currentHotel=helper.getHotelBLService().hotel_getInfo(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
							} 
						} catch (NumberFormatException | RemoteException e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		orderstatecolumn.setCellValueFactory(celldata -> celldata.getValue().orderStateProperty());
		orderstatecolumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						if(cell.getIndex()<=currentHotelList.size()){
						int hotelid=(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
						gotodetailinfo(hotelid);
					}
					}
					else if(t.getClickCount() == 1){
						try {
							if(cell.getIndex()<=currentHotelList.size()){
								currentHotel=helper.getHotelBLService().hotel_getInfo(Integer.parseInt(hotelTable.getItems().get(cell.getIndex()).getID()));
							} 
						} catch (NumberFormatException | RemoteException e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
	}

	public HotelModel hotelvoToModel(HotelVO vo) {
		RemoteHelper helper = RemoteHelper.getInstance();
		HotelModel model = new HotelModel();
		try {
			model.setID(vo.getid());
			model.setHotelName(vo.getname());
			model.setBusinessAddress(vo.getbussiness_address());
			model.setAddress(vo.getaddress());
			
			//选择当前酒店最便宜的房间价格
			ArrayList<RoomVO> rooms = helper.getHotelBLService().getallroom(vo.getid());
			int cheapest = rooms.get(0).getprice();
			for(RoomVO room: rooms){
				if (cheapest>room.getprice()) {
					cheapest = room.getprice();
				}
			}
			model.setCheapestRoomPrice(cheapest);
			model.setStar(vo.getstar());
			model.setScore(vo.getscore().split(",")[0]);
			model.setOrderState("未曾入住");
			//选择最近一次订单
			ArrayList<OrderVO> orders = helper.getOrderBLService().findorderByHotelid(vo.getid());
			for(int i=orders.size()-1;i>=0;i--){
				if (orders.get(i).getclientid()==currentClient.getclientid()) {
					model.setOrderState(orders.get(i).getstate());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
