package ui.view.market;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ui.view.Main;

public class MarketOverviewController implements Initializable {
	private Main main;

	@FXML
	private Label nameLabel;
	
	@FXML
	private Label contactLabel;

	@FXML
	public void gotoCreditCharge(){
		main.gotoMarketCreditCharge();
	}
	
	
	@FXML
	public void gotoStrategy(){
		main.gotoWebStrategyManage();
	}
	
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
