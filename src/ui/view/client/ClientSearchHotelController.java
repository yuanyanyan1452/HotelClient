package ui.view.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import ui.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import objects.Hotel;
import ui.view.Main;

public class ClientSearchHotelController implements Initializable {
	private Main main;
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
	private void initialize() {

	}

	@FXML
	private void search() {
		ObservableList<HotelModel> hotelData = FXCollections.observableArrayList();
		hotelData.add(new HotelModel("如家", "新街口", "xx广场xx号", "1", "2.0"));
		hotelData.add(new HotelModel("7天", "马群", "xx广场xx号", "2", "3.0"));
		hotelData.add(new HotelModel("汉庭", "仙林", "xx广场xx号", "3", "4.0"));
		hotelData.add(new HotelModel("布丁", "旧街口", "xx广场xx号", "4", "5.0"));

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
//		hotelTable.setItems(main.getHotelData());
	}
}
