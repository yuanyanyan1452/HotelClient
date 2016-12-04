package ui.view.hotel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class HotelBasicInfoController implements Initializable {
	private Main main;
	
	@FXML
	private void save(){
		//TODO
	}
	
	public HotelBasicInfoController() {
	
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
