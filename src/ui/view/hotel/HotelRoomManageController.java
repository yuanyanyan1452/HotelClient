package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.model.RoomModel;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.HotelVO;
import vo.RoomVO;

public class HotelRoomManageController implements Initializable {
	private Main main;
	private HotelVO currentHotel;
	private RoomModel currentRoom;
	@FXML
	private TableView<RoomModel> roomTable;
	
	@FXML
	private TableColumn<RoomModel, String> roomtypecolumn;
	
	@FXML
	private TableColumn<RoomModel, String> roomnumcolumn;
	
	@FXML
	private TableColumn<RoomModel, String> roompricecolumn;
	
	@FXML
	private TextField addTypeTextField;
	
	@FXML
	private TextField addNumTextField;
	
	@FXML
	private TextField addPriceTextField;
	
	@FXML
	private TextField updateTypeTextField;
	
	@FXML
	private TextField updateNumTextField;
	
	@FXML
	private TextField updatePriceTextField;
	
	@FXML
	private void addRoom(){
		if (addTypeTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间类型！");
		}
		else if (addNumTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间数量！");
		}
		else if (addPriceTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间价格！");
		}
		else {
			RemoteHelper helper = RemoteHelper.getInstance();
			String type = addTypeTextField.getText();
			int num = Integer.parseInt(addNumTextField.getText());
			int price = Integer.parseInt(addPriceTextField.getText());
			RoomVO vo = new RoomVO();
			vo.sethotelid(currentHotel.getid());
			vo.setroom_type(type);
			vo.settotal_num(num);
			vo.setprice(price);
			vo.setavailable_num(num);
			try {
				ResultMessage message = helper.getHotelBLService().hotel_importRoom(vo);
				if (message == ResultMessage.Success) {
					AlertUtil.showInformationAlert("添加可用房间成功！");
				}
				else{
					AlertUtil.showErrorAlert("添加可用房间失败。。可能是由于网络问题。");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void updateRoom(){
//		ArrayList<RoomVO> roomVOs = helper.
	}
	
	public HotelRoomManageController() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main,HotelVO hotelVO) {
		this.main = main;
		currentHotel = hotelVO;
		RemoteHelper helper = RemoteHelper.getInstance();
		//TODO 得到当前酒店的所有可用房间
//		ArrayList<RoomVO> roomVOs = helper
		
		ObservableList<RoomModel> roomModels = FXCollections.observableArrayList();
		roomtypecolumn.setCellValueFactory(celldata -> celldata.getValue().roomTypeProperty());
		roomnumcolumn.setCellValueFactory(celldata -> celldata.getValue().roomNumProperty());
		roompricecolumn.setCellValueFactory(celldata -> celldata.getValue().roomPriceProperty());
		roomTable.setItems(roomModels);
	}
}
