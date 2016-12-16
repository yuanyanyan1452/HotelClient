package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import ui.model.OrderModel;
import ui.view.Main;

public class ClientBrowseOrderController implements Initializable{
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
	private TableColumn<OrderModel, String> hotelColumn;
	
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
	private TableColumn<OrderModel, String> executeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> priceColumn;
	
	@FXML
	private TableColumn<OrderModel, String> numberColumn;
	
	@FXML
	private TableColumn<OrderModel, String> hasChildColumn;
	
	public ClientBrowseOrderController() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main){
		this.main = main;
		final ToggleGroup group1 = new ToggleGroup();
		filledButton.setToggleGroup(group1);
		unfilledButton.setToggleGroup(group1);
		final ToggleGroup group2 = new ToggleGroup();
		normalButton.setToggleGroup(group2);
		abnormalButton.setToggleGroup(group2);
		cancelButton.setToggleGroup(group2);
	}
	
}
