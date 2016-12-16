package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.view.Main;

public class UnfilledOrderDetailInfoByClientController implements Initializable {
	private Main main;
	
	@FXML
	private Button closebutton;
	
	@FXML
	private Button cancelbutton;
	
	@FXML
	private Label clientnamelabel;
	
	@FXML
	private Label hotelnamelabel;
	
	@FXML
	private Label orderidlabel;
	
	@FXML
	private Label orderstatelabel;
	
	@FXML
	private Label starttimelabel;
	
	@FXML
	private Label endtimelabel;
	
	@FXML
	private Label roomtypelabel;
	
	@FXML
	private Label roomnumberlabel;
	
	@FXML
	private Label peoplenumberlabel;
	
	@FXML
	private Label ifchildlabel;
	
	@FXML
	private Label pricelabel;
	
	@FXML
	private Label strategylabel;
	
	public UnfilledOrderDetailInfoByClientController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}

}
