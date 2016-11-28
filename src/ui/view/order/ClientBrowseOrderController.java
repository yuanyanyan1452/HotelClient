package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class ClientBrowseOrderController implements Initializable{
	private Main main;
	@FXML
	private void back(){
		main.gotoClientOverview();
	}
	
	public ClientBrowseOrderController() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
}
