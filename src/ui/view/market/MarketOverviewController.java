package ui.view.market;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class MarketOverviewController implements Initializable {
	private Main main;
	
	//网站销售策略管理
	@FXML
	private void gotoStrategyManage(){
		main.gotoWebStrategyManage();
	}
	
	//异常订单浏览
	@FXML
	private void gotoAbnormalOrder(){
		main.gotoMarketAbnormalOrder();
	}
	
	//信用充值
	@FXML
	private void gotoCreditCharge(){
		main.gotoMarketCreditCharge();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根

	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}
