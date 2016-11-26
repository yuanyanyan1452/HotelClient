package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class ClientSearchHotelController implements Initializable{
	private Main main;
	@FXML
	private void initialize(){
		
	}
	@FXML
	private void back(){
		main.gotoClientOverview();
	}
	
	//客户查看酒店详细信息
	@FXML
	private void gotoHotelDetailInfo(){
		main.gotoHotelDetailInfo();
	}
	
	//客户生成订单
	@FXML 
	private void gotoGenerateOrder(){
		main.gotoGenerateOrder();
	}
	
	public ClientSearchHotelController() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main){
		this.main = main;
	}
}
