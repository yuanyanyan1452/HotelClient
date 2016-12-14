package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.model.ClientModel;
import ui.model.HotelModel;
import ui.model.OrderModel;
import ui.view.Main;
import vo.ClientVO;
import vo.OrderVO;

public class MarketBrowseAbnormalOrderController implements Initializable {
	private Main main;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private OrderModel currentOrder;
	@FXML
	private TableView<OrderModel> orderTable;

	@FXML
	private TableColumn<OrderModel, String> idColumn;

	@FXML
	private TableColumn<OrderModel, String> clientColumn;

	@FXML
	private TableColumn<OrderModel, String> deadTimeColumn;

	@FXML
	private TableColumn<OrderModel, String> overTimeColumn;

	@FXML
	private TableColumn<OrderModel, String> punishColumn;

	@FXML
	private ComboBox<String> choiceComboBox;

	@FXML
	private void cancelAbnormalOrder() {
		int recover = Integer.parseInt(currentOrder.getPunishCredit());
		if (choiceComboBox.getValue().equals("一半信用值")) {
			recover /= 2;
		}
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			int orderid = Integer.parseInt(currentOrder.getOrderid());
			int clientid = Integer.parseInt(currentOrder.getClientid());
			//将订单置为已撤销
			ResultMessage m1 = helper.getOrderBLService().order_market_cancelAbnormal(orderid);
			
			//恢复信用值
			ResultMessage m2 = helper.getClientBLService().updateClientCredit(clientid, recover, 1);
			//添加信用记录
//			ArrayList<String> record= helper.getClientBLService().client_checkCreditList(clientid);
//			if (record==null) {
//				record = new ArrayList<String>();
//			}
//			Date date = new Date();
//			String nowTime = format.format(date);
//			String newRecord = "'"+nowTime+"'"+","+currentOrder.getOrderid()+",撤销异常订单,"+recover+","+helper.getClientBLService().client_checkCredit(clientid);
//			record.add(newRecord);
//			
//			ResultMessage m3 = helper.getClientBLService().client_updateInfo(helper.getClientBLService().client_getclientvo("").setcredit_record(record));
					
					
			if (m2==ResultMessage.Success&&m1==m2) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("撤销成功！");
				alert.show();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public MarketBrowseAbnormalOrderController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
		RemoteHelper helper = RemoteHelper.getInstance();

		ObservableList<String> choiceList = choiceComboBox.getItems();
		choiceList.addAll("全部信用值", "一半信用值");
		choiceComboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> param) {
				final ListCell<String> cell = new ListCell<String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(item);
					}
				};
				return cell;
			}
		});

		// vo转成可以在tableview中显示的model
		ObservableList<OrderModel> orderList = orderTable.getItems();
		try {
			ArrayList<OrderVO> orderdata = helper.getOrderBLService().order_market_browseUnfilled();
			for (OrderVO vo : orderdata) {
				if (vo != null) {
					OrderModel model = new OrderModel();
					model.setOrderid(vo.getid());
					model.setClientid(vo.getclientid());
					model.setLatestExecuteTime(vo.getlatest_execute_time());
					model.setOverTime(new Date());
					model.setPunishCredit(vo.getprice());
					orderList.add(model);
				}
			}
		} catch (RemoteException | NullPointerException e) {
			e.printStackTrace();
		}
		
		idColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		clientColumn.setCellValueFactory(celldata -> celldata.getValue().clientidProperty());
		deadTimeColumn.setCellValueFactory(celldata -> celldata.getValue().latestExecuteTimeProperty());
		overTimeColumn.setCellValueFactory(celldata -> celldata.getValue().overTimeProperty());
		punishColumn.setCellValueFactory(celldata -> celldata.getValue().punishCreditProperty());
		
		// 将鼠标最近点击过的那一行的order设为currentOrder
		idColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (cell.getIndex()<orderTable.getItems().size()) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
					}
					
				});
				return cell;
			}
		});
		clientColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (cell.getIndex()<orderTable.getItems().size()) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
					}
				});
				return cell;
			}
		});
		deadTimeColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (cell.getIndex()<orderTable.getItems().size()) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
					}
				});
				return cell;
			}
		});
		overTimeColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (cell.getIndex()<orderTable.getItems().size()) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
					}
				});
				return cell;
			}
		});
		punishColumn.setCellFactory(new Callback<TableColumn<OrderModel, String>, TableCell<OrderModel, String>>() {
			@Override
			public TableCell<OrderModel, String> call(TableColumn<OrderModel, String> param) {
				TextFieldTableCell<OrderModel, String> cell = new TextFieldTableCell<>();
				cell.setOnMouseClicked((MouseEvent t) -> {
					if (cell.getIndex()<orderTable.getItems().size()) {
						currentOrder = orderTable.getItems().get(cell.getIndex());
					}
				});
				return cell;
			}
		});

		orderTable.setItems(orderList);
	}
}
