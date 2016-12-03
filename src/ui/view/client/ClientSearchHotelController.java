package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import ui.view.Main;

public class ClientSearchHotelController implements Initializable{
	private Main main;
	
	@FXML
	private SplitMenuButton locationButton;
	
	@FXML
	private MenuItem locationMenuItem1;
	
	@FXML
	private MenuItem locationMenuItem2;
	
	@FXML
	private MenuItem locationMenuItem3;
	
	@FXML
	private SplitMenuButton businessAddressButton;
	
	@FXML
	private MenuItem businessAddressMenuItem1;
	
	@FXML
	private MenuItem businessAddressMenuItem2;
	
	@FXML
	private MenuItem businessAddressMenuItem3;
	
	
	
	
	@FXML
	private SplitMenuButton roomtypeButton;
	
	@FXML
	private SplitMenuButton starButton;
	
	@FXML
	private SplitMenuButton lowscoreButton;
	
	@FXML
	private SplitMenuButton highscoreButton;
	@FXML
	private void initialize(){
		
	}
	
	//回显并置为搜索条件
	@FXML
	private void locationAction1(){
		locationButton.setText("南京");
		//TODO
	}
	@FXML
	private void locationAction2(){
		locationButton.setText("北京");
		//TODO
	}
	@FXML
	private void locationAction3(){
		locationButton.setText("上海");
		//TODO
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
