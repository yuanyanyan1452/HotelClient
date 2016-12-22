package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import rmi.RemoteHelper;
import ui.model.OrderModel;
import ui.model.RoomModel;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.ClientVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.RoomVO;

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
	private TableColumn<RoomModel, String> avaliableColumn;
	
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
	private Label totalLabel;

	@FXML
	public void close() {
		main.closeExtraStage();
	}

	@FXML
	private void generateOrder() {
		if(currentClient.getcredit()>=0){
		main.gotoGenerateOrder(currentHotel,currentClient);
		}
		else{
			AlertUtil.showWarningAlert("对不起，您的信用值不足");
		}
	}

	public HotelDetailInfoController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main, HotelVO hotel,int clientid) {
		this.main = main;
		this.currentHotel = hotel;
		
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			currentClient=helper.getClientBLService().client_checkInfo(clientid);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		//酒店基本信息初始化
		hotelnameLabel.setText(hotel.getname());
		businessaddressLabel.setText(hotel.getbussiness_address());
		addressLabel.setText(hotel.getaddress());
		starLabel.setText(hotel.getstar());
		
		if (hotel.getscore().contains(",")) {//屏蔽酒店评分人数
			scoreLabel.setText(hotel.getscore().split(",")[0]);
		}
		else{
			scoreLabel.setText(hotel.getscore());
		}
		introductionLabel.setText(hotel.getintroduction());
		serviceLabel.setText(hotel.getservice());

		
		// 将当前酒店的所有可用房间录入到表格内
		ObservableList<RoomModel> rooms = roomTable.getItems();
		try {
			ArrayList<RoomVO> roomVOs = helper.getHotelBLService().getallroom(currentHotel.getid());
			for(RoomVO roomVO : roomVOs){
				RoomModel roomModel = new RoomModel();
				roomModel.setRoomNum(roomVO.getavailable_num());
				roomModel.setRoomPrice(roomVO.getprice());
				roomModel.setRoomType(roomVO.getroom_type());
				rooms.add(roomModel);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		roomtypeColumn.setCellValueFactory(celldata -> celldata.getValue().roomTypeProperty());
		priceColumn.setCellValueFactory(celldata -> celldata.getValue().roomPriceProperty());
		avaliableColumn.setCellValueFactory(celldata -> celldata.getValue().roomNumProperty());
		
		roomTable.setItems(rooms);
		
		//导入当前客户在这个酒店的历史订单
		ObservableList<OrderModel> orders = orderTable.getItems();
		try {
			ArrayList<OrderVO> orderVOs = helper.getOrderBLService().get_client_hotel_order(clientid, currentHotel.getid());
			for(OrderVO vo: orderVOs){
				OrderModel orderModel = new OrderModel();
				orderModel.setOrderid(vo.getid());
				ArrayList<RoomOrderVO> roomOrderVOs = vo.getroom_order();
				String roomtype = "";
				String roomnumber = "";
				for(int i=0;i<roomOrderVOs.size();i++){
					if (i==roomOrderVOs.size()-1){
						roomtype+=roomOrderVOs.get(i).getroom_type();
						roomnumber+=roomOrderVOs.get(i).getroom_number();
					}
					else{
						roomtype+=roomOrderVOs.get(i).getroom_type()+",";
						roomnumber+=roomOrderVOs.get(i).getroom_number()+",";
					}
				}
				orderModel.setRoomType(roomtype);
				orderModel.setRoomNumber(roomnumber);
				orderModel.setPrice(vo.getprice());
				orderModel.setState(vo.getstate());
				
				orders.add(orderModel);
			}
				
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		orderidColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		orderRoomtypeColumn.setCellValueFactory(celldata -> celldata.getValue().roomtypeProperty());
		orderRoomamountColumn.setCellValueFactory(celldata -> celldata.getValue().roomnumberProperty());
		orderPriceColumn.setCellValueFactory(celldata -> celldata.getValue().priceProperty());
		orderStateColumn.setCellValueFactory(celldata -> celldata.getValue().stateProperty());
		
		orderTable.setItems(orders);
	}
}
