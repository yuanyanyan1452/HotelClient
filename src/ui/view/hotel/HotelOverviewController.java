package ui.view.hotel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class HotelOverviewController implements Initializable {
	private Main main;
	
	@FXML
	private void initialize() {

	}
	
	//管理酒店信息
	@FXML
	private void gotoBasicInfo(){
		main.gotoHotelBasicInfo();
	}
	
	//可用客房管理
	@FXML
	private void gotoRoomManage(){
		main.gotoHotelRoomManage();
	}
	
	//订单浏览
	@FXML
	private void gotoOrderBrowse(){
		main.gotoHotelBrowseOrder();
	}
	//订单执行
	@FXML
	private void gotoOrderExecute(){
		main.gotoHotelExecuteOrder();
	}
	
	//酒店房间信息更新
	@FXML
	private void gotoCheckIn(){
		main.gotoHotelCheckIn();
	}
	
	//酒店销售策略管理
	@FXML
	private void gotoStrategyManage(){
		main.gotoHotelStrategyManage();
	}
	
	public HotelOverviewController() {

	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根

	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}
