package ui.view.market;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ui.view.Main;

public class CreditChargeController implements Initializable {
	private Main main;

	@FXML
	private TextField clientNameTextField;
	
	@FXML
	private TextField creditTextField;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label contactLabel;
	
	@FXML
	private Label viptypeLabel;
	
	@FXML
	private Label creditLabel;
	
	@FXML
	private void searchClient(){
		//TODO
	}
	
	@FXML
	private void chargeCredit(){
		//TODO
	}
	
	public CreditChargeController() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
