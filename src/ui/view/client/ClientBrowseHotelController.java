package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.model.HotelModel;
import ui.view.Main;

public class ClientBrowseHotelController implements Initializable{
	private Main main;
	@FXML
	private TableView<HotelModel> hotelTable;
	
	@FXML
	private TableColumn<HotelModel, String> hotelnameColumn;
	
	@FXML
	private TableColumn<HotelModel, String> businessAddressColumn;
	
	@FXML
	private TableColumn<HotelModel, String> addressColumn;
	
	@FXML
	private TableColumn<HotelModel, String> starColumn;
	
	@FXML
	private TableColumn<HotelModel, String> scoreColumn;
	
	@FXML
	private TableColumn<HotelModel, String> orderStateColumn;
	@FXML
	private void initialize(){
		
	}
	@FXML
	private void back(){
		main.gotoClientOverview();
	}
	public ClientBrowseHotelController() {
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main){
		this.main = main;
	}
}
