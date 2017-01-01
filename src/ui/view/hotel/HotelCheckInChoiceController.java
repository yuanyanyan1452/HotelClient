package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.model.OrderModel;
import ui.util.AlertUtil;
import ui.util.OrderUtil;
import ui.util.RecordActionUtil;
import ui.view.Main;
import vo.AccommodationVO;

public class HotelCheckInChoiceController implements Initializable {
	private Main main;
	private HotelCheckInController checkInController;
	private OrderModel currentOrder;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FXML
	private Button returnButton;
	
	@FXML
	private Label orderIdLabel;

	@FXML
	private Label orderExecuteLabel;

	@FXML
	private Label orderStateLabel;

	@FXML
	private Label predictLabel;

	@FXML
	private DatePicker predictLeaveDatePicker;

	@FXML
	private Button checkinButton;

	@FXML
	private Button delayCheckinButton;

	@FXML
	private Button checkoutButton;

	@FXML
	public void checkin() {
		try {
			// 更新currentOrder
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDate predictlocaldate = predictLeaveDatePicker.getValue();
			Instant instant = predictlocaldate.atStartOfDay().atZone(zoneId).toInstant();
			Date predictDate = Date.from(instant);
			predictDate.setTime(predictDate.getTime()+12*3600*1000);
			currentOrder.setPredictLeaveTime(predictDate);
			currentOrder.setIsExecute(true);

			RemoteHelper helper = RemoteHelper.getInstance();
			int orderid = Integer.parseInt(currentOrder.getOrderid());
			int clientid = Integer.parseInt(currentOrder.getClientid());
			int price = Integer.parseInt(currentOrder.getPrice());
			AccommodationVO info = new AccommodationVO("001号房", new Date(), predictDate, new Date());
			
			// 订单状态更新为已执行
			ResultMessage message1 = helper.getOrderBLService().order_hotel_execute(orderid);
			if (message1 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			// 酒店更新入住信息
			ResultMessage message2 = helper.getOrderBLService().order_checkin(info, orderid);
			if (message2 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			// 客户信用值增加
			ResultMessage message3 = helper.getClientBLService().updateClientCredit(clientid, price, 1);
			if (message3 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			// 添加信用记录
			Date now = new Date();
			String nowtime = format.format(now);
			int nowCredit = helper.getClientBLService().client_checkCredit(clientid);
			String newRecord =  nowtime + "," + orderid + "," + RecordActionUtil.getExecute() + "," + price
					+ "," + nowCredit;
			ResultMessage message4 = helper.getClientBLService().client_updateClientCreditList(clientid, newRecord);
			if (message4 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			// 更新checkin界面
			checkInController.updateTable(currentOrder);
			main.closeExtraStage();
			AlertUtil.showInformationAlert("入住成功！");
		} catch ( RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void delayCheckin() {
		try {
			// 更新currentOrder
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDate predictlocaldate = predictLeaveDatePicker.getValue();
			Instant instant = predictlocaldate.atStartOfDay().atZone(zoneId).toInstant();
			Date predictDate = Date.from(instant);
			predictDate.setTime(predictDate.getTime()+12*3600*1000);
			currentOrder.setPredictLeaveTime(predictDate);
			currentOrder.setIsExecute(true);
			currentOrder.setState(OrderUtil.getNormal());

			// 更新底层数据
			RemoteHelper helper = RemoteHelper.getInstance();
			int orderid = Integer.parseInt(currentOrder.getOrderid());
			int clientid = Integer.parseInt(currentOrder.getClientid());
			int price = Integer.parseInt(currentOrder.getPrice());
			AccommodationVO info = new AccommodationVO("002号房", new Date(), predictDate, new Date());
			// 订单状态更新为已执行
			ResultMessage message1 = helper.getOrderBLService().order_hotel_execute(orderid);
			if (message1 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			// 酒店更新入住信息
			ResultMessage message2 = helper.getOrderBLService().order_checkin(info, orderid);
			if (message2 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			// 客户信用值增加
			ResultMessage message3 = helper.getClientBLService().updateClientCredit(clientid, price, 1);
			if (message3 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			// 添加信用记录
			Date now = new Date();
			String nowtime = format.format(now);
			int nowCredit = helper.getClientBLService().client_checkCredit(clientid);
			String newRecord = "'" + nowtime + "'" + "," + orderid + "," + RecordActionUtil.getExecute() + "," + price
					+ "," + nowCredit;
			ResultMessage message4 = helper.getClientBLService().client_updateClientCreditList(clientid, newRecord);
			if (message4 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}
			main.closeExtraStage();
			// 更新checkin界面
			checkInController.updateTable(currentOrder);

			AlertUtil.showInformationAlert("延迟入住成功！");
		} catch ( RemoteException e) {
			e.printStackTrace();
		}
		;
	}

	@FXML
	public void checkout() {
		try {
			// 退房
			RemoteHelper helper = RemoteHelper.getInstance();
			int orderid = Integer.parseInt(currentOrder.getOrderid());
			ResultMessage message = helper.getOrderBLService().order_checkout(orderid);
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			// checkin界面删除这个订单
			checkInController.removeTable(currentOrder);
			main.closeExtraStage();
			AlertUtil.showInformationAlert("退房成功！");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public HotelCheckInChoiceController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main, HotelCheckInController checkInController) {
		this.main = main;
		this.checkInController = checkInController;
		currentOrder = checkInController.currentOrder;

		returnButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				main.closeExtraStage();
			}
		});
		orderIdLabel.setText(currentOrder.getOrderid());
		orderExecuteLabel.setText(currentOrder.getIsExecute());
		orderStateLabel.setText(currentOrder.getState());
		// 已执行订单显示退房
		if (currentOrder.getIsExecute().equals(OrderUtil.getIsexecute())) {
			predictLabel.setVisible(false);
			predictLeaveDatePicker.setVisible(false);
			checkinButton.setVisible(false);
			delayCheckinButton.setVisible(false);
			checkoutButton.setVisible(true);
		}
		// 未执行正常订单显示入住
		else if (currentOrder.getState().equals(OrderUtil.getNormal())) {
			predictLabel.setVisible(true);
			predictLeaveDatePicker.setVisible(true);
			checkinButton.setVisible(true);
			delayCheckinButton.setVisible(false);
			checkoutButton.setVisible(false);
		}
		// 未执行异常订单显示延迟入住
		else {
			predictLabel.setVisible(true);
			predictLeaveDatePicker.setVisible(true);
			checkinButton.setVisible(false);
			delayCheckinButton.setVisible(true);
			checkoutButton.setVisible(false);
		}

		// 无法选择之前的天数
		LocalDate today = LocalDate.now();
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(today)) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		predictLeaveDatePicker.setDayCellFactory(dayCellFactory);
	}

}
