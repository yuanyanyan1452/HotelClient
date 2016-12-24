package ui.view.market;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.WebMarketVO;

public class MarketOverviewController implements Initializable {
	private Main main;
	private WebMarketVO currentwebmarketvo;
	RemoteHelper helper=RemoteHelper.getInstance();
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label contactLabel;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private void exit(){
		currentwebmarketvo.setlogged(false);
		try {
			helper.getManageBLService().manage_updateMarketWorker(currentwebmarketvo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		main.exitSystem();
	}
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

	public void setMain(Main main,WebMarketVO vo) {
		this.main = main;
		currentwebmarketvo=vo;
		nameLabel.setText(vo.getname());
		contactLabel.setText(vo.getcontact());
	}
}
