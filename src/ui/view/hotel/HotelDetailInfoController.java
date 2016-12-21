package ui.view.hotel;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import ui.model.HotelModel;
import ui.model.OrderModel;
import ui.model.RoomModel;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.ClientVO;
import vo.HotelVO;

public class HotelDetailInfoController implements Initializable {
	private Main main;
	private ClientVO currentClient;
	private HotelVO currentHotel;

	@FXML
	private Button button1;

	@FXML
	private Button button2;
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
	private void searchHotel() {
		// TODO
	}

	@FXML
	public void close() {
		main.closeExtraStage();
	}

	@FXML
	private void generateOrder() {
		main.gotoGenerateOrder(currentHotel,currentClient);
	}

	public HotelDetailInfoController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main, HotelVO hotel) {
		this.main = main;
		this.currentHotel = hotel;

		hotelnameLabel.setText(hotel.getname());
		businessaddressLabel.setText(hotel.getbussiness_address());
		addressLabel.setText(hotel.getaddress());
		starLabel.setText(hotel.getstar());
		scoreLabel.setText(hotel.getscore());
		introductionLabel.setText(hotel.getintroduction());
		serviceLabel.setText(hotel.getservice());
		// introductionLabel.setText(hotel.get);

		// 每个月日期不同的现实问题需解决
		String[] months = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		startMonthBox.getItems().addAll(months);
		endMonthBox.getItems().addAll(months);
		startMonthBox.setVisibleRowCount(5);
		endMonthBox.setVisibleRowCount(5);
		startDayBox.setVisibleRowCount(5);
		endDayBox.setVisibleRowCount(5);
		startMonthBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> daylist = FXCollections.observableArrayList(days);
				if (startMonthBox.getValue().equals("2")) {
					Calendar calendar = Calendar.getInstance();
					if (calendar.get(Calendar.DAY_OF_YEAR) == 366) {
						daylist.removeAll("30", "31");
					} else
						daylist.removeAll("29", "30", "31");
				}
				if (startMonthBox.getValue().equals("4") && startMonthBox.getValue().equals("6")
						&& startMonthBox.getValue().equals("9") && startMonthBox.getValue().equals("11")) {
					daylist.remove("31");
				}

				startDayBox.setItems(daylist);
			}
		});
		endMonthBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> daylist = FXCollections.observableArrayList(days);
				if (endMonthBox.getValue().equals("2")) {
					Calendar calendar = Calendar.getInstance();
					if (calendar.get(Calendar.DAY_OF_YEAR) == 366) {
						daylist.removeAll("30", "31");
					} else
						daylist.removeAll("29", "30", "31");
				}
				if (endMonthBox.getValue().equals("4") && endMonthBox.getValue().equals("6")
						&& endMonthBox.getValue().equals("9") && endMonthBox.getValue().equals("11")) {
					daylist.remove("31");
				}
				endDayBox.setItems(daylist);
			}
		});
		endDayBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
					if (startMonthBox.getValue() != null && startDayBox.getValue() != null
							&& endMonthBox.getValue() != null && endDayBox.getValue() != null) {
						int startmonth = Integer.parseInt(startMonthBox.getValue()) * 100;
						int endmonth = Integer.parseInt(endMonthBox.getValue()) * 100;
						int startday = Integer.parseInt(startDayBox.getValue());
						int endday = Integer.parseInt(endDayBox.getValue());
						if ((startmonth + startday) >= (endmonth + endday)) {
							AlertUtil.showWarningAlert("离开时间应晚于入住时间！");
						} else {
							totalLabel.setText(
									"共" + String.valueOf((endmonth - startmonth) * 30 / 100 + endday - startday) + "晚");
							//TODO
						}
					}
			}

		});
	}
}
