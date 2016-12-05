package ui.view.hotel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.model.HotelModel;
import ui.model.OrderModel;
import ui.model.RoomModel;
import ui.view.Main;

public class HotelDetailInfoController implements Initializable {
	private Main main;
	private HotelModel hotel;
	@FXML
	private Label hotelnameLabel;
	
	@FXML
	private Label businessaddressLabel;
	
	@FXML
	private Label addressLabel;
	
	@FXML
	private Label starLabel;
	
	@FXML
	private Label scoreLabel;
	
	@FXML
	private Label serviceLabel;
	
	@FXML
	private Label introductionLabel;
	
	@FXML
	private TableView<RoomModel> roomTable;
	
	@FXML
	private TableColumn<RoomModel, String> roomtypeColumn;
	
	@FXML
	private TableColumn<RoomModel, String> priceColumn;
	
	@FXML
	private TableView<OrderModel> orderTable;
	
	@FXML
	private TableColumn<OrderModel, String> orderidColumn;
	
	@FXML
	private TableColumn<OrderModel, String> orderRoomtypeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> orderRoomamountColumn;
	
	@FXML
	private TableColumn<OrderModel, String> orderPriceColumn;
	
	@FXML
	private TableColumn<OrderModel, String> orderStateColumn;
	
	@FXML
	private ComboBox<String> startMonthBox;
	
	@FXML
	private ComboBox<String> startDayBox;
	
	@FXML
	private ComboBox<String> endMonthBox;
	
	@FXML
	private ComboBox<String> endDayBox;
	
	@FXML
	private Label totalLabel;
	
	@FXML
	private void searchHotel(){
		//TODO
	}
	
	@FXML
	public void close(){
		main.closeExtraStage();
	}
	
	@FXML
	private void generateOrder(){
		main.gotoGenerateOrder(hotel);
	}
	
	public HotelDetailInfoController() {

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	

	public void setMain(Main main,HotelModel hotel) {
		this.main = main;
		this.hotel = hotel;
		
		hotelnameLabel.setText(hotel.getHotelName());
		businessaddressLabel.setText(hotel.getBusinessAddress());
		addressLabel.setText(hotel.getAddress());
		starLabel.setText(hotel.getStar());
		scoreLabel.setText(hotel.getScore());
		
		//TODO 每个月日期不同的现实问题需解决
		startMonthBox.getItems().addAll("1","2","3","4","5","6"
				,"7","8","9","10","11","12");
		startDayBox.getItems().addAll("1","2","3","4","5","6","7"
				,"8","9","10","11","12","13","14","15","16","17","18",
				"19","20","21","22","23","24","25","26","27","28","29","30","31");
		endMonthBox.getItems().addAll("1","2","3","4","5","6"
				,"7","8","9","10","11","12");
		endDayBox.getItems().addAll("1","2","3","4","5","6","7"
				,"8","9","10","11","12","13","14","15","16","17","18",
				"19","20","21","22","23","24","25","26","27","28","29","30","31");
	}
}
