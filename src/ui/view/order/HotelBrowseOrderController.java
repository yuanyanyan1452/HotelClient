package ui.view.order;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import rmi.RemoteHelper;
import ui.model.OrderModel;
import ui.util.OrderUtil;
import ui.view.Main;
import vo.OrderVO;
import vo.RoomOrderVO;

public class HotelBrowseOrderController implements Initializable {
	private Main main;

	private ObservableList<OrderModel> orderlist = FXCollections.observableArrayList();

	private ObservableList<OrderModel> mostorderlist = FXCollections.observableArrayList();

	RemoteHelper helper = RemoteHelper.getInstance();

	@FXML
	private RadioButton filledButton;

	@FXML
	private RadioButton unfilledButton;

	@FXML
	private RadioButton normalButton;

	@FXML
	private RadioButton abnormalButton;

	@FXML
	private RadioButton cancelButton;

	@FXML
	private TableView<OrderModel> orderTable;

	@FXML
	private TableColumn<OrderModel, String> idColumn;

	@FXML
	private TableColumn<OrderModel, String> stateColumn;

	@FXML
	private TableColumn<OrderModel, String> startTimeColumn;

	@FXML
	private TableColumn<OrderModel, String> endTimeColumn;

	@FXML
	private TableColumn<OrderModel, String> roomTypeColumn;

	@FXML
	private TableColumn<OrderModel, String> roomAmountColumn;

	@FXML
	private TableColumn<OrderModel, String> numberColumn;

	@FXML
	private TableColumn<OrderModel, String> hasChildColumn;

	@FXML
	private TableColumn<OrderModel, String> priceColumn;

	@FXML
	private TableColumn<OrderModel, String> executeColumn;

	// 查看正常订单
	@FXML
	public void update_normal() {
		orderlist.clear();
		orderlist = changeOrderlist(mostorderlist, OrderUtil.getNormal());
		if (filledButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getIsexecute());
		} else if (unfilledButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getNoexecute());
		}
		this.show(orderlist);
	}

	// 查看异常订单
	@FXML
	public void update_abnormal() {
		orderlist.clear();
		orderlist = changeOrderlist(mostorderlist, OrderUtil.getAbnormal());
		if (filledButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getIsexecute());
		} else if (unfilledButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getNoexecute());
		}
		this.show(orderlist);
	}

	// 查看撤销订单
	@FXML
	public void update_cancelled() {
		orderlist.clear();
		orderlist = changeOrderlist(mostorderlist, OrderUtil.getCancel());
		if (filledButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getIsexecute());
		} else if (unfilledButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getNoexecute());
		}
		this.show(orderlist);
	}

	// 查看已执行订单
	@FXML
	public void update_execute() {
		orderlist.clear();
		orderlist = changeOrderlist(mostorderlist, OrderUtil.getIsexecute());
		if (normalButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getNormal());
		} else if (abnormalButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getAbnormal());
		} else if (cancelButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getCancel());
		}
		this.show(orderlist);
	}

	// 查看未执行订单
	@FXML
	public void update_noexecute() {
		orderlist.clear();
		orderlist = changeOrderlist(mostorderlist, OrderUtil.getNoexecute());
		if (normalButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getNormal());
		} else if (abnormalButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getAbnormal());
		} else if (cancelButton.isSelected()) {
			orderlist = changeOrderlist(orderlist, OrderUtil.getCancel());
		}
		this.show(orderlist);
	}

	public ObservableList<OrderModel> changeOrderlist(ObservableList<OrderModel> orderlist, String condition) {
		ObservableList<OrderModel> neworderlist = FXCollections.observableArrayList();
		for (int i = 0; i < orderlist.size(); i++) {
			if (orderlist.get(i).getState().equals(condition) || orderlist.get(i).getIsExecute().equals(condition)) {
				neworderlist.add(orderlist.get(i));
			}
		}
		return neworderlist;
	}

	public void gotodetailorder(OrderModel order){
		main.gotoHotelDetailOrder(order);
	}
	public void show(ObservableList<OrderModel> orderlist) {

		idColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		executeColumn.setCellValueFactory(celldata -> celldata.getValue().isExecuteProperty());
		stateColumn.setCellValueFactory(celldata -> celldata.getValue().stateProperty());
		startTimeColumn.setCellValueFactory(celldata -> celldata.getValue().startTimeProperty());
		endTimeColumn.setCellValueFactory(celldata -> celldata.getValue().endTimeProperty());
		roomTypeColumn.setCellValueFactory(celldata -> celldata.getValue().roomtypeProperty());
		roomAmountColumn.setCellValueFactory(celldata -> celldata.getValue().roomnumberProperty());
		priceColumn.setCellValueFactory(celldata -> celldata.getValue().priceProperty());
		numberColumn.setCellValueFactory(celldata -> celldata.getValue().numOfPeopleProperty());
		hasChildColumn.setCellValueFactory(celldata -> celldata.getValue().haveChildProperty());

		idColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});

		executeColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});

		stateColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						if (order.getIsExecute().equals("是")) {
							try {
								main.gotoClientExecuteOrder(order);
							} catch (NumberFormatException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							try {
								main.gotoClientNoExecuteOrder(order);
							} catch (NumberFormatException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				});
				return cell;
			}
		});

		startTimeColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});

		endTimeColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});

		roomTypeColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});

		roomAmountColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});

		priceColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});

		numberColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});

		hasChildColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (t.getClickCount() == 2) {
						OrderModel order = orderlist.get(cell.getIndex());
						gotodetailorder(order);
					}
				});
				return cell;
			}
		});
		orderTable.setItems(orderlist);
	}

	public HotelBrowseOrderController() {
		// TODO
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO

	}

	public void setMain(Main main, int hotelid) throws RemoteException {
		this.main = main;
		final ToggleGroup group1 = new ToggleGroup();
		filledButton.setToggleGroup(group1);
		unfilledButton.setToggleGroup(group1);
		final ToggleGroup group2 = new ToggleGroup();
		normalButton.setToggleGroup(group2);
		abnormalButton.setToggleGroup(group2);
		cancelButton.setToggleGroup(group2);
		ObservableList<OrderVO> ordervolist = FXCollections
				.observableArrayList(helper.getOrderBLService().findorderByHotelid(hotelid));
		mostorderlist.clear();
		for (OrderVO ordervo : ordervolist) {
			OrderModel model = new OrderModel();
			model.setOrderid(ordervo.getid());
			model.setIsExecute(ordervo.getexecute());
			model.setState(ordervo.getstate());
			model.setStartTime(ordervo.getstart_time());
			model.setEndTime(ordervo.getend_time());
			ArrayList<RoomOrderVO> roomorderlist = ordervo.getroom_order();
			String roomtype = "";
			int roomnumber = 0;
			for (int i = 0; i < roomorderlist.size(); i++) {
				roomtype += (roomorderlist.get(i).getroom_type() + " ");
				roomnumber += roomorderlist.get(i).getroom_number();
			}
			model.setRoomType(roomtype);
			model.setRoomNumber(String.valueOf(roomnumber));
			model.setPrice(ordervo.getprice());
			model.setNumOfPeople(ordervo.getexpect_number_of_people());
			model.setHaveChild(ordervo.gethave_child());
			mostorderlist.add(model);
		}
		this.show(mostorderlist);
	}
}
