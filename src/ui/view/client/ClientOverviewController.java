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
	
	//查看客户基本信息
	@FXML
	private void gotoBasicInfo(){
		main.gotoClientBasicInfo();
	}
	
	//浏览预定过的酒店
	@FXML
	private void gotoBrowseHotel(){
		main.gotoClientBrowseHotel();
	}
	
	//注册会员
	@FXML
	private void gotoEnrollVIP(){
		main.gotoClientEnrollVIP();
	}
	
	//浏览订单
	@FXML
	private void gotoBrowseOrder(){
		main.gotoClientBrowseOrder();
	}
	
	//搜索酒店
	@FXML
	private void gotoSearchHotel(){
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
