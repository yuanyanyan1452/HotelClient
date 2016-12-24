package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.model.RoomModel;
import ui.util.AlertUtil;
import ui.util.RoomTypeUtil;
import ui.view.Main;
import vo.HotelVO;
import vo.RoomVO;

public class HotelRoomManageController implements Initializable {
	private Main main;
	private HotelVO currentHotel;

	@FXML
	private TableView<RoomModel> roomTable;

	@FXML
	private TableColumn<RoomModel, String> roomtypecolumn;

	@FXML
	private TableColumn<RoomModel, String> roomnumcolumn;

	@FXML
	private TableColumn<RoomModel, String> roompricecolumn;

	@FXML
	private ComboBox<String> addTypeTextField;

	@FXML
	private TextField addNumTextField;

	@FXML
	private TextField addPriceTextField;

	@FXML
	private ComboBox<String> updateTypeTextField;

	@FXML
	private TextField updateNumTextField;

	@FXML
	private TextField updatePriceTextField;

	@FXML
	private void addRoom() {
		if (addTypeTextField.getValue().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间类型！");
		} else if (addNumTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间数量！");
		} else if (addPriceTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间价格！");
		} else {
			RemoteHelper helper = RemoteHelper.getInstance();
			String type = addTypeTextField.getValue();
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
				} else {
					AlertUtil.showErrorAlert("添加可用房间失败。。可能是由于网络问题。");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void updateRoom() {
		if (updateTypeTextField.getValue().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间类型！");
		} else if (updateNumTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间数量！");
		} else if (updatePriceTextField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未输入房间价格！");
		} else {
			try {
				boolean hasFind = false;
				String type = updateTypeTextField.getValue();
				int num = Integer.parseInt(updateNumTextField.getText());
				int price = Integer.parseInt(updatePriceTextField.getText());
				RemoteHelper helper = RemoteHelper.getInstance();
				ArrayList<RoomVO> roomVOs = helper.getHotelBLService().getallroom(currentHotel.getid());
				for (RoomVO roomVO : roomVOs) {
					if (roomVO.getroom_type().equals(type)) {
						roomVO.settotal_num(num);
						roomVO.setprice(price);
						helper.getHotelBLService().hotel_updateRoom(roomVO);
						hasFind = true;
						break;
					}
				}
				if (!hasFind) {
					AlertUtil.showWarningAlert("当前酒店不存在这种类型的房间，请先添加~");
				}
				else {
					AlertUtil.showInformationAlert("更新可用房间成功！");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public HotelRoomManageController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main, HotelVO hotelVO) {
		this.main = main;
		currentHotel = hotelVO;

		addTypeTextField.getItems().addAll(RoomTypeUtil.getAllRoomType());
		updateTypeTextField.setItems(addTypeTextField.getItems());
		// 将当前酒店的所有可用房间录入到表格内
		RemoteHelper helper = RemoteHelper.getInstance();
		ObservableList<RoomModel> roomModels = FXCollections.observableArrayList();
		try {
			ArrayList<RoomVO> roomVOs = helper.getHotelBLService().getallroom(currentHotel.getid());
			for (RoomVO roomVO : roomVOs) {
				RoomModel roomModel = new RoomModel();
				roomModel.setRoomNum(roomVO.gettotal_num());
				roomModel.setRoomPrice(roomVO.getprice());
				roomModel.setRoomType(roomVO.getroom_type());
				roomModels.add(roomModel);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		roomtypecolumn.setCellValueFactory(celldata -> celldata.getValue().roomTypeProperty());
		roomnumcolumn.setCellValueFactory(celldata -> celldata.getValue().roomNumProperty());
		roompricecolumn.setCellValueFactory(celldata -> celldata.getValue().roomPriceProperty());

		roomTable.setItems(roomModels);
	}
}
