package ui.view.hotel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ui.view.Main;

public class HotelDetailInfoController implements Initializable {
	private Main main;
	public HotelDetailInfoController() {

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@FXML
	public void close(){
		main.closeExtraStage();
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
