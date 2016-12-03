package ui.view.hotel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class HotelDetailInfoController implements Initializable {
	private Main main;
	public HotelDetailInfoController() {
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根

	}
	
	//关闭酒店详细信息界面
	@FXML
	public void close(){
		main.closeExtraStage();
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
