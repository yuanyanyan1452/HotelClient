package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import objects.Order;
import objects.ResultMessage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import rmi.RemoteHelper;
import ui.model.OrderModel;
import ui.util.AlertUtil;
import ui.util.OrderUtil;
import ui.view.Main;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomOrderVO;

public class HotelCheckInController implements Initializable {
	private Main main;
	private HotelVO currentHotel;
	private HotelCheckInController controller;
	OrderModel currentOrder;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FXML
	private TableView<OrderModel> orderTable;

	@FXML
	private TableColumn<OrderModel, String> idColumn;

	@FXML
	private TableColumn<OrderModel, String> stateColumn;

	@FXML
	private TableColumn<OrderModel, String> executeColumn;

	@FXML
	private TableColumn<OrderModel, String> latestExecuteTimeColumn;

	@FXML
	private TableColumn<OrderModel, String> overExecuteTimeColumn;

	@FXML
	private TableColumn<OrderModel, String> predictLeaveTimeColumn;

	@FXML
	private TableColumn<OrderModel, String> roomTypeColumn;

	@FXML
	private ComboBox<String> roomtypeComboBox;

	@FXML
	private ComboBox<Integer> roomNumComboBox;

	@FXML
	private ComboBox<Integer> daysComboBox;

	/**
	 * 线下入住
	 */
	@FXML
	private void downlinecheckin() {
		if (roomtypeComboBox.getValue() == null) {
			AlertUtil.showWarningAlert("未为线下操作选择房间类型！");
		} else if (roomNumComboBox.getValue() == null) {
			AlertUtil.showWarningAlert("未选择房间数量！");
		} else if( daysComboBox.getValue()==null) {
			AlertUtil.showWarningAlert("未选择入住天数！");
		} else {
			try {
				RemoteHelper helper = RemoteHelper.getInstance();
				String roomtype = roomtypeComboBox.getValue();
				int roomnum = roomNumComboBox.getValue();
				int days = daysComboBox.getValue();

				ArrayList<RoomOrderVO> roomOrderVOs = new ArrayList<>();
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setnum_of_days(days);
				roomOrderVO.setroom_number(roomnum);
				roomOrderVO.setroom_type(roomtype);

				ResultMessage message = helper.getOrderBLService().offline_checkin(currentHotel.getid(), roomOrderVOs);
				if (message == ResultMessage.Fail) {
					AlertUtil.showWarningAlert("线下入住失败。。可能是由于网络问题");
				} else {
					AlertUtil.showInformationAlert("线下入住成功！");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	/**
	 * 线下退房
	 */
	@FXML
	private void downlinecheckout() {
		if (roomtypeComboBox.getValue() == null) {
			AlertUtil.showWarningAlert("未为线下操作选择房间类型！");
		} else if (roomNumComboBox.getValue() == null) {
			AlertUtil.showWarningAlert("未选择房间数量！");
		} else if( daysComboBox.getValue()==null) {
			AlertUtil.showWarningAlert("未选择入住天数！");
		} else {
			try {
				RemoteHelper helper = RemoteHelper.getInstance();

				String roomtype = roomtypeComboBox.getValue();
				int roomnum = roomNumComboBox.getValue();
				int days = daysComboBox.getValue();

				ArrayList<RoomOrderVO> roomOrderVOs = new ArrayList<>();
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setnum_of_days(days);
				roomOrderVO.setroom_number(roomnum);
				roomOrderVO.setroom_type(roomtype);

				ResultMessage message = helper.getOrderBLService().offline_checkout(currentHotel.getid(), roomOrderVOs);
				if (message == ResultMessage.Fail) {
					AlertUtil.showWarningAlert("线下退房失败。。可能是由于网络问题");
				} else {
					AlertUtil.showInformationAlert("线下退房成功！");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
	}

	public HotelCheckInController() {

	}

	/**
	 * 用于入住结束后的更新表格
	 * @param changedOrder
	 */
	public void updateTable(OrderModel changedOrder) {

		for (int index = 0; index < orderTable.getItems().size(); index++) {
			if (orderTable.getItems().get(index).getOrderid().equals(changedOrder.getOrderid())) {
				orderTable.getItems().get(index)
						.setIsExecute(changedOrder.getIsExecute().equals(OrderUtil.getIsexecute()));
				orderTable.getItems().get(index).setState(changedOrder.getState());
				orderTable.getItems().get(index).setOverTime(new Date());
				if (!changedOrder.getPredictLeaveTime().equals(OrderUtil.getNoexecute())) {
					try {
						orderTable.getItems().get(index).setPredictLeaveTime(format.parse(changedOrder.getPredictLeaveTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}


	/**
	 * 用于退房后的表格项删除
	 * @param finishedOrder
	 */
	public void removeTable(OrderModel finishedOrder) {
		for (int index = 0; index < orderTable.getItems().size(); index++) {
			if (orderTable.getItems().get(index).getOrderid().equals(finishedOrder.getOrderid())) {
				orderTable.getItems().remove(index);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main, HotelVO hotelVO) {
		this.main = main;
		controller = this;
		currentHotel = hotelVO;
		RemoteHelper helper = RemoteHelper.getInstance();

		// 初始化房间类型combobox
		roomtypeComboBox.getItems().addAll("标准间", "大床房", "双人房","商务房","豪华房","海景房");

		ObservableList<OrderModel> models = FXCollections.observableArrayList();
		try {
			//取出未执行的所有订单
			ArrayList<OrderVO> orderVOs = helper.getOrderBLService().findorderBy_Hotelid_Execute(currentHotel.getid(), false);
			for (OrderVO vo : orderVOs) {
				OrderModel model = new OrderModel();
				model.setOrderid(vo.getid());
				model.setClientid(vo.getclientid());
				model.setHotelid(vo.gethotelid());
				model.setPrice(vo.getprice());
				ArrayList<RoomOrderVO> roomOrder = vo.getroom_order();
				String roomtype = "";
				for (int i = 0; i < roomOrder.size(); i++) {
					if (i == roomOrder.size() - 1) {
						roomtype += roomOrder.get(i).getroom_type();
					} else {
						roomtype += roomOrder.get(i).getroom_type() + ",";
					}
				}
				model.setRoomType(roomtype);
				model.setIsExecute(vo.getexecute());
				model.setState(vo.getstate());
				model.setLatestExecuteTime(vo.getlatest_execute_time());
				model.setOverTime(new Date());
				//未执行订单设置预计离开时间为未入住
				model.setPredictLeaveTime(null);
				models.add(model);
			}
			//取出执行了但未退房的所有订单
			orderVOs = helper.getOrderBLService().findorderBy_Hotelid_Execute(currentHotel.getid(), true);
			String flag = "12:00:00";
			for(OrderVO vo: orderVOs){
				String endtime = format.format(vo.getend_time());
				if (endtime.contains(flag)) {
					OrderModel model = new OrderModel();
					model.setOrderid(vo.getid());
					model.setClientid(vo.getclientid());
					model.setHotelid(vo.gethotelid());
					model.setPrice(vo.getprice());
					ArrayList<RoomOrderVO> roomOrder = vo.getroom_order();
					String roomtype = "";
					for (int i = 0; i < roomOrder.size(); i++) {
						if (i == roomOrder.size() - 1) {
							roomtype += roomOrder.get(i).getroom_type();
						} else {
							roomtype += roomOrder.get(i).getroom_type() + ",";
						}
					}
					model.setRoomType(roomtype);
					model.setIsExecute(vo.getexecute());
					model.setState(vo.getstate());
					model.setLatestExecuteTime(vo.getlatest_execute_time());
					model.setOverTime(new Date());
					//未退房的实际离开时间现在存的是预计离开时间
					model.setPredictLeaveTime(vo.getend_time());
					models.add(model);
				}
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		orderTable.setItems(models);

		idColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		idColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {

			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent e) -> {
					if (e.getClickCount() == 2) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
						main.gotoHotelCheckInChoice(controller);
					}
				});
				return cell;
			}
		});
		roomTypeColumn.setCellValueFactory(celldata ->celldata.getValue().roomtypeProperty());
		roomTypeColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {

			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent e) -> {
					if (e.getClickCount() == 2) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
						main.gotoHotelCheckInChoice(controller);
					}
				});
				return cell;
			}
		});
		executeColumn.setCellValueFactory(celldata -> celldata.getValue().isExecuteProperty());
		executeColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {

			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent e) -> {
					if (e.getClickCount() == 2) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
						main.gotoHotelCheckInChoice(controller);
					}
				});
				return cell;
			}
		});
		stateColumn.setCellValueFactory(celldata -> celldata.getValue().stateProperty());
		stateColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {

			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent e) -> {
					if (e.getClickCount() == 2) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
						main.gotoHotelCheckInChoice(controller);
					}
				});
				return cell;
			}
		});
		latestExecuteTimeColumn.setCellValueFactory(celldata -> celldata.getValue().latestExecuteTimeProperty());
		latestExecuteTimeColumn
				.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {

					@Override
					public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
						TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
						cell.setOnMouseClicked((MouseEvent e) -> {
							if (e.getClickCount() == 2) {
								currentOrder = orderTable.getItems().get(cell.getIndex());
								main.gotoHotelCheckInChoice(controller);
							}
						});
						return cell;
					}
				});
		overExecuteTimeColumn.setCellValueFactory(celldata -> celldata.getValue().overTimeProperty());
		overExecuteTimeColumn
				.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {

					@Override
					public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
						TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
						cell.setOnMouseClicked((MouseEvent e) -> {
							if (e.getClickCount() == 2) {
								currentOrder = orderTable.getItems().get(cell.getIndex());
								main.gotoHotelCheckInChoice(controller);
							}
						});
						return cell;
					}
				});
		predictLeaveTimeColumn.setCellValueFactory(celldata -> celldata.getValue().predictLeaveTimeProperty());
		predictLeaveTimeColumn
				.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {

					@Override
					public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
						TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
						cell.setOnMouseClicked((MouseEvent e) -> {
							if (e.getClickCount() == 2) {
								currentOrder = orderTable.getItems().get(cell.getIndex());
								main.gotoHotelCheckInChoice(controller);
							}
						});
						return cell;
					}
				});
	}
}
