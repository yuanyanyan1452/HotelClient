package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.model.OrderModel;
import ui.view.Main;

public class HotelBrowseOrderController implements Initializable {
	private Main main;
	@FXML
	private RadioButton filledButton;
	
	@FXML
	private RadioButton unfilledButton;
	
	@FXML
	private RadioButton normalButton;
	
	@FXML
	private RadioButton abnormalButton;
	
	@FXML
	private RadioButton cancelButton;
	
	@FXML
	private TableView<OrderModel> orderTable;
	
	@FXML
	private TableColumn<OrderModel, String> idColumn;
	
	@FXML
	private TableColumn<OrderModel, String> stateColumn;
	
	@FXML
	private TableColumn<OrderModel, String> startTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> endTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> roomTypeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> roomAmountColumn;
	
	@FXML
	private TableColumn<OrderModel, String> numberColumn;
	
	@FXML
	private TableColumn<OrderModel, String> hasChildColumn;
	
	@FXML
	private TableColumn<OrderModel, String> priceColumn;
	
	@FXML
	private TableColumn<OrderModel, String> strategyColumn;
	
	public HotelBrowseOrderController() {
		// TODO 
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
