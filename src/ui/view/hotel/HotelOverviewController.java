package ui.view.hotel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class HotelOverviewController implements Initializable {
	private Main main;

	/*
	 * 跳转到酒店基本信息管理窗口
	 */
	@FXML
	public void gotoHotelBasicInfo() {
		main.gotoHotelBasicInfo();
	}

	/*
	 * 跳转到酒店浏览订单窗口
	 */
	@FXML
	public void gotoHotelBrowseOrder() {
		main.gotoHotelBrowseOrder();
	}

	/*
	 * 跳转到酒店客户入住窗口
	 */
	@FXML
	public void gotoHotelCheckIn() {
		main.gotoHotelCheckIn();
	}

	/*
	 * 跳转到酒店执行订单窗口
	 */
	@FXML
	public void gotoHotelExecuteOrder() {
		main.gotoHotelExecuteOrder();
	}

	/*
	 * 跳转到酒店可用房间管理窗口
	 */
	@FXML
	public void gotoHotelRoomManage() {
		main.gotoHotelRoomManage();
	}

	/*
	 * 跳转到酒店促销策略管理窗口
	 */
	@FXML
	public void gotoHotelStrategyManage() {
		main.gotoHotelStrategyManage();
	}

	public HotelOverviewController() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根

	}

	public void setMain(Main main) {
		this.main = main;
	}

}
