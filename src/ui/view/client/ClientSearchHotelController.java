package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import rmi.RemoteHelper;
import ui.model.HotelModel;
import ui.view.Main;
import vo.HotelVO;

public class ClientSearchHotelController implements Initializable {
	private Main main;
	private ArrayList<HotelVO> currentHotelList;
	
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
	private ComboBox<String> locationButton;

	@FXML
	private ComboBox<String> businessAddressButton;

	@FXML
	private ComboBox<String> roomtypeButton;

	@FXML
	private ComboBox<String> starButton;

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
		ObservableList<HotelModel> hotelModels = hotelTable.getItems();
		RemoteHelper helper = RemoteHelper.getInstance();
		
		
		
		
		
		hotelTable.setItems(hotelModels);
	}
	

	// 直接跳转到生成订单界面
	@FXML
	private void gotoGenerateOrder() {
		main.gotoGenerateOrder(new HotelModel());
	}

	public ClientSearchHotelController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
		
		//初始化组合框
		locationButton.getItems().addAll("南京","上海","北京");
		roomtypeButton.getItems().addAll("单人房","大床房","双人房");
		starButton.getItems().addAll("一星级","二星级","三星级","四星级","五星级");
		lowscoreButton.getItems().addAll("0.0","1.0","2.0","3.0","4.0","5.0");
		highscoreButton.getItems().addAll("0.0","1.0","2.0","3.0","4.0","5.0");
		
		//设置城市,商圈完毕后得到一个list
		RemoteHelper helper = RemoteHelper.getInstance();
		businessAddressButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (locationButton!=null&&locationButton.getValue()!=null&&businessAddressButton!=null&&businessAddressButton.getValue()!=null) {
					try {
						currentHotelList = helper.getHotelBLService().searchHotelBylocation(locationButton.getValue(), businessAddressButton.getValue());
						ObservableList<HotelModel> models = FXCollections.observableArrayList();
						for(HotelVO vo:currentHotelList){
							HotelModel model  = new HotelModel();
							model.setHotelName(vo.getname());
							model.setBusinessAddress(vo.getbussiness_address());
							model.setAddress(vo.getaddress());
							model.setStar(vo.getstar());
							model.setScore(vo.getscore());
							models.add(model);
						}
						hotelTable.setItems(models);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		//初始化hotelTable
		ObservableList<HotelModel> hotelData = FXCollections.observableArrayList();

		hotelnamecolumn.setCellValueFactory(celldata -> celldata.getValue().hotelNameProperty());
		hotelnamecolumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						main.gotoHotelDetailInfo(hotelData.get(cell.getIndex()));
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
								main.gotoHotelDetailInfo(hotelData.get(cell.getIndex()));
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
						main.gotoHotelDetailInfo(hotelData.get(cell.getIndex()));
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
						main.gotoHotelDetailInfo(hotelData.get(cell.getIndex()));
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
						main.gotoHotelDetailInfo(hotelData.get(cell.getIndex()));
					}
				});
				return cell;
			}
		});
		hotelTable.setItems(hotelData);
	}
}
