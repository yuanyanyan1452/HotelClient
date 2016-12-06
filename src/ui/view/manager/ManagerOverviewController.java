package ui.view.manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ui.model.*;
import ui.view.Main;

public class ManagerOverviewController implements Initializable {
	private Main main;
	
	@FXML
	private Label clientNumLabel;
	
	@FXML
	private Label hotelNumLabel;
	
	@FXML
	private Label orderNumLabel;
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private TableView<ClientModel> clientTable;
	
	@FXML
	private TableColumn<ClientModel, String> clientNameColumn;
	
	@FXML
	private TableColumn<ClientModel, String> clientContactColumn;
	
	@FXML
	private TableColumn<ClientModel, String> clientVipColumn;
	
	@FXML
	private TableColumn<ClientModel, String> clientCreditColumn;
	
	@FXML
	private Button clientAddButton;
	
	@FXML
	private Button clientDeleteButton;
	
	@FXML
	private Button clientUpdateButton;
	
	@FXML
	private TableView<HotelModel> hotelTable;
	
	@FXML
	private TableColumn<HotelModel, String> hotelNameColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelAddressColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelBusinessAddressColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelStarColumn;
	
	@FXML
	private TableColumn<HotelModel, String> hotelScoreColumn;
	
	@FXML
	private Button hotelAddButton;
	
	@FXML
	private Button hotelDeleteButton;
	
	@FXML
	private Button hotelUpdateButton;
	
	@FXML
	private TableView<HotelWorkerModel> hotelWorkerTable;
	
	@FXML
	private TableColumn<HotelWorkerModel, String> workerNameColumn;
	
	@FXML
	private TableColumn<HotelWorkerModel, String> workerHotelColumn;
	
	@FXML
	private TableColumn<HotelWorkerModel, String> workerContactColumn;
	
	@FXML
	private Button workerAddButton;
	
	@FXML
	private Button workerDeleteButton;
	
	@FXML
	private Button workerUpdateButton;
	
	@FXML
	private TableView<MarketModel> marketTable;
	
	@FXML
	private TableColumn<MarketModel, String> marketNameColumn;
	
	@FXML
	private TableColumn<MarketModel, String> marketContactColumn;
	
	@FXML
	private Button marketAddButton;
	
	@FXML
	private Button marketDeleteButton;
	
	@FXML
	private Button marketUpdateButton;
	
	public ManagerOverviewController() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
