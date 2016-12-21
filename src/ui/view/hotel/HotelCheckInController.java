package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import objects.Order;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import rmi.RemoteHelper;
import ui.model.OrderModel;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.OrderVO;

public class HotelCheckInController implements Initializable {
	private Main main;
	
	RemoteHelper helper=RemoteHelper.getInstance();
	
	OrderVO currentordervo;
	
	@FXML
	private TableView<OrderModel> orderTable;
	
	@FXML
	private TableColumn<OrderModel, String> idColumn;
	
	@FXML
	private TableColumn<OrderModel, String> stateColumn;
	
	@FXML
	private TableColumn<OrderModel, String> executeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> latestExecuteTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> overExecuteTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> predictLeaveTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> leaveTimeColumn;
	
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
