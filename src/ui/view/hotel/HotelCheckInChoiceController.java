package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.model.OrderModel;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.AccommodationVO;
import vo.OrderVO;

public class HotelCheckInChoiceController implements Initializable {
	private Main main;
	private HotelCheckInController checkInController;
	private OrderModel currentOrder;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String ISEXECUTE = "是";
	private static final String NORMAL = "正常";
	private static final String ABNORMAL = "异常";
	@FXML
	private Label orderIdLabel;

	@FXML
	private Label orderExecuteLabel;

	@FXML
	private Label orderStateLabel;

	@FXML
	private Label predictLabel;

	@FXML
	private TextField predictTextField;

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
			String predictTime = predictTextField.getText();
			Date predictDate = format.parse(predictTime);
			currentOrder.setPredictLeaveTime(predictTime);
			currentOrder.setIsExecute(true);

			// 更新底层数据
			RemoteHelper helper = RemoteHelper.getInstance();
			int orderid = Integer.parseInt(currentOrder.getOrderid());
			AccommodationVO info = new AccommodationVO("001号房", new Date(), predictDate, new Date());
			ResultMessage message1 = helper.getOrderBLService().order_hotel_execute(orderid);
			ResultMessage message2 = helper.getHotelBLService().hotel_updateAccomodation(info, orderid);

			if (message1 == ResultMessage.Fail || message2 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}

			//TODO 更新信用记录
			// 更新checkin界面
			checkInController.updateTable(currentOrder);

			AlertUtil.showInformationAlert("入住成功！");
		} catch (ParseException | RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void delayCheckin() {
		try {
			// 更新currentOrder
			String predictTime = predictTextField.getText();
			Date predictDate = format.parse(predictTime);
			currentOrder.setPredictLeaveTime(predictTime);
			currentOrder.setIsExecute(true);
			currentOrder.setState(NORMAL);
			
			// 更新底层数据
			RemoteHelper helper = RemoteHelper.getInstance();
			int orderid = Integer.parseInt(currentOrder.getOrderid());
			AccommodationVO info = new AccommodationVO("002号房", new Date(), predictDate, new Date());
			ResultMessage message1 = helper.getOrderBLService().order_hotel_execute(orderid);
			ResultMessage message2 = helper.getHotelBLService().hotel_updateAccomodation(info, orderid);
			
			if (message1 == ResultMessage.Fail || message2 == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("操作失败。。");
				return;
			}
			//TODO 更新信用记录
			// 更新checkin界面
			checkInController.updateTable(currentOrder);
			
			AlertUtil.showInformationAlert("延迟入住成功！");
		} catch (ParseException | RemoteException e) {
			e.printStackTrace();
		}
		;
	}

	@FXML
	public void checkout() {
		//TODO 退房
//		try {
//			// 更新底层数据
//			RemoteHelper helper = RemoteHelper.getInstance();
//			AccommodationVO info = new AccommodationVO("003号房", checkInDate, predictDate, new Date());
//			ResultMessage message1 = helper.getHotelBLService().hotel_updateAccomodation(info, Integer.parseInt(currentOrder.getOrderid()));
//			if (message1 == ResultMessage.Fail) {
//				AlertUtil.showErrorAlert("操作失败。。");
//				return;
//			}
//			// 更新checkin界面
//			checkInController.removeTable(currentOrder);
//			
//			AlertUtil.showInformationAlert("退房成功！");
//		} catch (ParseException | RemoteException e) {
//			e.printStackTrace();
//		}
//		
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

		// 已执行订单显示退房
		if (currentOrder.getIsExecute().equals(ISEXECUTE)) {
			predictLabel.setVisible(false);
			predictTextField.setVisible(false);
			checkinButton.setVisible(false);
			delayCheckinButton.setVisible(false);
			checkoutButton.setVisible(true);
		}
		// 未执行正常订单显示入住
		else if (currentOrder.getState().equals(NORMAL)) {
			predictLabel.setVisible(true);
			predictTextField.setVisible(true);
			checkinButton.setVisible(true);
			delayCheckinButton.setVisible(false);
			checkoutButton.setVisible(false);
		}
		// 未执行异常订单显示延迟入住
		else {
			predictLabel.setVisible(true);
			predictTextField.setVisible(true);
			checkinButton.setVisible(false);
			delayCheckinButton.setVisible(true);
			checkoutButton.setVisible(false);
		}
	}

}
