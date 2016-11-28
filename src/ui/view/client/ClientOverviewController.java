package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class ClientOverviewController implements Initializable {
	private Main main;

	@FXML
	private void initialize() {

	}

	/*
	 * 跳转到客户基本信息管理窗口
	 */
	@FXML
	private void gotoBasicInfo() {
		main.gotoClientBasicInfo();
	}

	/*
	 * 跳转到客户浏览历史酒店窗口
	 */
	@FXML
	private void gotoBrowseHotel() {
		main.gotoClientBrowseHotel();
	}

	/*
	 * 跳转到客户注册会员窗口
	 */
	@FXML
	private void gotoEnrollVIP() {
		main.gotoClientEnrollVIP();
	}

	/*
	 * 跳转到客户浏览历史订单窗口
	 */
	@FXML
	private void gotoBrowseOrder() {
		main.gotoClientBrowseOrder();
	}

	/*
	 * 跳转到客户搜索酒店窗口
	 */
	@FXML
	private void gotoSearchHotel() {
		main.gotoClientSearchHotel();
	}

	public ClientOverviewController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
