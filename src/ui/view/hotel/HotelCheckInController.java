package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import rmi.RemoteHelper;
import ui.model.OrderModel;
import ui.view.Main;
import vo.OrderVO;

public class HotelCheckInController implements Initializable {
	private Main main;
	
	RemoteHelper helper=RemoteHelper.getInstance();
	
	OrderVO currentordervo;
	
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
	private void search() throws NumberFormatException, RemoteException{
		//TODO
		currentordervo=helper.getOrderBLService().order_findbyid(Integer.parseInt(orderIdTextField.getText()));
		if(currentordervo.getstate()==null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(null);
			alert.setHeaderText(null);
			alert.setContentText("对不起，您输入的订单号不存在");
			alert.showAndWait();
		}
		else{
			//显示
		}
		
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
