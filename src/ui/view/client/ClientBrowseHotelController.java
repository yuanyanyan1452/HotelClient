package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import rmi.RemoteHelper;
import ui.model.HotelModel;
import ui.view.Main;
import vo.ClientVO;
import vo.HotelVO;
import vo.OrderVO;

public class ClientBrowseHotelController implements Initializable{
	private Main main;
	private ClientVO currentClient;
	private HotelModel currentHotel;
	@FXML
	private TableView<HotelModel> hotelTable;
	
	@FXML
	private TableColumn<HotelModel, String> hotelnameColumn;
	
	@FXML
	private TableColumn<HotelModel, String> businessAddressColumn;
	
	@FXML
	private TableColumn<HotelModel, String> addressColumn;
	
	@FXML
	private TableColumn<HotelModel, String> starColumn;
	
	@FXML
	private TableColumn<HotelModel, String> scoreColumn;
	
	@FXML
	private TableColumn<HotelModel, String> orderStateColumn;
	
	
	public ClientBrowseHotelController() {
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main,ClientVO client){
		this.main = main;
		currentClient = client;
		RemoteHelper helper = RemoteHelper.getInstance();
		
		ObservableList<HotelModel> models = FXCollections.observableArrayList();
		try {
			ArrayList<HotelVO> vos = helper.getClientBLService().client_getpreviousHotelList(currentClient.getclientid());
			for(HotelVO vo : vos){
				HotelModel model = new HotelModel();
				model.setID(vo.getid());
				model.setHotelName(vo.getname());
				model.setBusinessAddress(vo.getbussiness_address());
				model.setAddress(vo.getaddress());
				model.setStar(vo.getstar());
				model.setScore(vo.getscore().split(",")[0]);
				ArrayList<OrderVO> orderVOs = helper.getOrderBLService().order_hotel_browse(vo.getid());
				model.setOrderState(orderVOs.get(orderVOs.size()-1).getstate());
				models.add(model);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		hotelTable.setItems(models);
		
		hotelnameColumn.setCellValueFactory(celldata->celldata.getValue().hotelNameProperty());
		hotelnameColumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						try {
							main.gotoHotelDetailInfo(helper.getHotelBLService().hotel_checkInfo(Integer.parseInt(models.get(cell.getIndex()).getID())));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		businessAddressColumn.setCellValueFactory(celldata -> celldata.getValue().businessAddressProperty());
		businessAddressColumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						try {
							main.gotoHotelDetailInfo(helper.getHotelBLService().hotel_checkInfo(Integer.parseInt(models.get(cell.getIndex()).getID())));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		addressColumn.setCellValueFactory(celldata->celldata.getValue().addressProperty());
		addressColumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						try {
							main.gotoHotelDetailInfo(helper.getHotelBLService().hotel_checkInfo(Integer.parseInt(models.get(cell.getIndex()).getID())));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		starColumn.setCellValueFactory(celldata->celldata.getValue().starProperty());
		starColumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						try {
							main.gotoHotelDetailInfo(helper.getHotelBLService().hotel_checkInfo(Integer.parseInt(models.get(cell.getIndex()).getID())));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		scoreColumn.setCellValueFactory(celldata -> celldata.getValue().scoreProperty());
		scoreColumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						try {
							main.gotoHotelDetailInfo(helper.getHotelBLService().hotel_checkInfo(Integer.parseInt(models.get(cell.getIndex()).getID())));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		orderStateColumn.setCellValueFactory(celldata -> celldata.getValue().orderStateProperty());
		orderStateColumn.setCellFactory(new Callback<TableColumn<HotelModel, String>, TableCell<HotelModel, String>>() {

			@Override
			public TableCell<HotelModel, String> call(TableColumn<HotelModel, String> param) {
				TextFieldTableCell<HotelModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						try {
							main.gotoHotelDetailInfo(helper.getHotelBLService().hotel_checkInfo(Integer.parseInt(models.get(cell.getIndex()).getID())));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				return cell;
			}
		});
		
	}
}
