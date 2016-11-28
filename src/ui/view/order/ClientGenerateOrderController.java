package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class ClientGenerateOrderController implements Initializable {
	private Main main;

	public ClientGenerateOrderController() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO 自动生成的方法存根

	}
//	//回到客户主界面
//	@FXML
//	public void gotoClientOverview(){
//		main.gotoClientSearchHotel();
//	}

	public void setMain(Main main) {
		this.main = main;
	}

}
