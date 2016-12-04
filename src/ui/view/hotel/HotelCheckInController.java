package ui.view.hotel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ui.model.OrderModel;
import ui.view.Main;

public class HotelCheckInController implements Initializable {
	private Main main;
	@FXML
	private TextField orderIdTextField;
	
	@FXML
	private TableView<OrderModel> orderTable;
	
	@FXML
	private TableColumn<OrderModel, String> idColumn;
	
	@FXML
	private TableColumn<OrderModel, String> stateColumn;
	
	@FXML
	private TableColumn<OrderModel, String> checkInTimeColumn;
	
	@FXML
	private void search(){
		//TODO
	}
	
	@FXML
	private void checkIn(){
		//TODO
	}
	
	@FXML
	private void checkOut(){
		//TODO
	}
	
	public HotelCheckInController() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
