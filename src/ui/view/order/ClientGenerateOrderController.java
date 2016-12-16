package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.model.HotelModel;
import ui.view.Main;

public class ClientGenerateOrderController implements Initializable {
	private Main main;
	
	@FXML
	private Label hotelnameLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private ComboBox<String> startMonth;
	
	@FXML
	private ComboBox<String> startDay;
	
	@FXML
	private ComboBox<String> endMonth;
	
	@FXML
	private ComboBox<String> endDay;
	
	@FXML
	private ComboBox<String> deadHour;
	
	@FXML
	private ComboBox<String> roomType;
	
	@FXML
	private ComboBox<String> hasChild;
	
	@FXML
	private ComboBox<String> strategy;
	
	@FXML
	private TextField roomNumTextField;
	
	@FXML
	private TextField predictCheckinNumTextField;
	
	@FXML
	private void close(){
		main.closeExtraStage();
	}
	
	@FXML
	private void generate(){
		//TODO
	}
	public ClientGenerateOrderController() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}


	public void setMain(Main main , HotelModel hotel) {
		this.main = main;
		hotelnameLabel.setText(hotel.getHotelName());
	}

}
