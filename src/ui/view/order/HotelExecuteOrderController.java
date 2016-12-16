package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.model.OrderModel;
import ui.view.Main;

public class HotelExecuteOrderController implements Initializable {

	private Main main;

	@FXML
	private Button delaybutton;
	
	@FXML
	private Button executebutton;
	
	@FXML
	private TableView<OrderModel> orderTable;
	
	@FXML
	private TableColumn<OrderModel, String> idColumn;
	
	@FXML
	private TableColumn<OrderModel, String> startTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> endTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> latest_execute_time;
	
	@FXML
	private TableColumn<OrderModel, String> roomTypeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> roomAmountColumn;
	
	@FXML
	private TableColumn<OrderModel, String> strategyColumn;
	
	@FXML
	private TableColumn<OrderModel, String> numberColumn;
	
	@FXML
	private TableColumn<OrderModel, String> hasChildColumn;
	
	@FXML
	private TableColumn<OrderModel, String> priceColumn;

	
	public HotelExecuteOrderController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
	}
}
