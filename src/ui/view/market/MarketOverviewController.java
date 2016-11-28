package ui.view.market;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class MarketOverviewController implements Initializable {
	private Main main;

	/*
	 * 跳转到信用充值窗口
	 */
	@FXML
	public void gotoCreditCharge(){
		main.gotoMarketCreditCharge();
	}
	
	/*
	 * 跳转到网站促销策略管理窗口
	 */
	@FXML
	public void gotoStrategy(){
		main.gotoWebStrategyManage();
	}
	/*
	 * 跳转到撤销异常订单窗口
	 */
	@FXML
	public void gotoAbnormalOrder(){
		main.gotoMarketAbnormalOrder();
	}
	public MarketOverviewController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
