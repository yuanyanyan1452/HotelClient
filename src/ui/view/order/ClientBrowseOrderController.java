package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import rmi.RemoteHelper;
import ui.model.OrderModel;
import ui.view.Main;
import vo.OrderVO;

public class ClientBrowseOrderController implements Initializable{
	private Main main;
	
	private ObservableList<OrderModel> orderlist=FXCollections.observableArrayList();
	
	private ObservableList<OrderModel> mostorderlist=FXCollections.observableArrayList();
	
	RemoteHelper helper=RemoteHelper.getInstance();
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
	
	@FXML
	public void update_normal(){
		orderlist.clear();
		orderlist=changeOrderlist(mostorderlist, "正常");
		this.show(orderlist);
	}
	
	public void update_abnormal(){
		orderlist.clear();
		orderlist=changeOrderlist(mostorderlist, "异常");
		this.show(orderlist);
	}
	
	public void update_cancelled(){
		orderlist.clear();
		orderlist=changeOrderlist(mostorderlist, "撤销");
		this.show(orderlist);
	}
	
	public void update_execute(ObservableList<OrderModel> orderlist){
		orderlist=changeOrderlist(orderlist, "是");
		this.show(orderlist);
	}
	
	public void update_noexecute(ObservableList<OrderModel> orderlist){
		orderlist=changeOrderlist(orderlist, "否");
		this.show(orderlist);
	}
	public ObservableList<OrderModel> changeOrderlist(ObservableList<OrderModel> orderlist,String condition){
		ObservableList<OrderModel> neworderlist=FXCollections.observableArrayList();
		for(int i=0;i<orderlist.size();i++){
			if(orderlist.get(i).getState().equals(condition)||orderlist.get(i).getIsExecute().equals(condition)){
				neworderlist.add(orderlist.get(i));
			}
		}
		return neworderlist;
	}
	
	public void show(ObservableList<OrderModel> orderlist){
		idColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		hotelColumn.setCellValueFactory(celldata -> celldata.getValue().hotelidProperty());
		executeColumn.setCellValueFactory(celldata -> celldata.getValue().isExecuteProperty());
		stateColumn.setCellValueFactory(celldata -> celldata.getValue().stateProperty());
		startTimeColumn.setCellValueFactory(celldata -> celldata.getValue().startTimeProperty());
		endTimeColumn.setCellValueFactory(celldata -> celldata.getValue().endTimeProperty());
		//roomTypeColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		//roomAmountColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		priceColumn.setCellValueFactory(celldata -> celldata.getValue().priceProperty());
		numberColumn.setCellValueFactory(celldata -> celldata.getValue().numOfPeopleProperty());
		hasChildColumn.setCellValueFactory(celldata -> celldata.getValue().haveChildProperty());
		
		orderTable.setItems(orderlist);
	}
	
	
	public ClientBrowseOrderController() {
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main,int clientid) throws RemoteException{
		this.main = main;
		final ToggleGroup group1 = new ToggleGroup();
		filledButton.setToggleGroup(group1);
		unfilledButton.setToggleGroup(group1);
		final ToggleGroup group2 = new ToggleGroup();
		normalButton.setToggleGroup(group2);
		abnormalButton.setToggleGroup(group2);
		cancelButton.setToggleGroup(group2);
		ObservableList<OrderVO> ordervolist=FXCollections.observableArrayList(helper.getOrderBLService().order_client_browse(clientid));
		mostorderlist.clear();
		for(OrderVO ordervo:ordervolist){
			OrderModel model=new OrderModel();
			model.setOrderid(ordervo.getid());
			model.setHotelid(ordervo.gethotelid());
			model.setIsExecute(ordervo.getexecute());
			model.setState(ordervo.getstate());
			model.setStartTime(ordervo.getstart_time());
		  //model.setRoomOrder(ordervo.getroom_order());
			model.setPrice(ordervo.getprice());
			model.setNumOfPeople(ordervo.getexpect_number_of_people());
			model.setHaveChild(ordervo.gethave_child());
			mostorderlist.add(model);
		}
		this.show(mostorderlist);
		
		if(normalButton.isSelected()){
			this.update_normal();
			if(filledButton.isSelected()){
				this.update_execute(orderlist);
			}
			else if(unfilledButton.isSelected()){
				this.update_noexecute(orderlist);
			}
		}
		else if(abnormalButton.isSelected()){
			this.update_abnormal();
			if(filledButton.isSelected()){
				this.update_execute(orderlist);
			}
			else if(unfilledButton.isSelected()){
				this.update_noexecute(orderlist);
			}
		}
		else if(cancelButton.isSelected()){
			this.update_cancelled();
			if(filledButton.isSelected()){
				this.update_execute(orderlist);
			}
			else if(unfilledButton.isSelected()){
				this.update_noexecute(orderlist);
			}
		}
		else {
			if(filledButton.isSelected()){
				this.update_execute(mostorderlist);
			}
			else if(unfilledButton.isSelected()){
				this.update_noexecute(mostorderlist);
			}
		}
	}
	
}
