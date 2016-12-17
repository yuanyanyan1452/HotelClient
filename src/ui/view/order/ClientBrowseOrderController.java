package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
import vo.RoomOrderVO;

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
		if(filledButton.isSelected()){
			orderlist=changeOrderlist(mostorderlist,"是");
		}
		else if(unfilledButton.isSelected()){
			orderlist=changeOrderlist(mostorderlist,"否");
		}
		this.show(orderlist);
	}
	
	@FXML
	public void update_abnormal(){
		orderlist.clear();
		orderlist=changeOrderlist(mostorderlist, "异常");
		if(filledButton.isSelected()){
			orderlist=changeOrderlist(mostorderlist,"是");
		}
		else if(unfilledButton.isSelected()){
			orderlist=changeOrderlist(mostorderlist,"否");
		}
		this.show(orderlist);
	}
	
	@FXML
	public void update_cancelled(){
		orderlist.clear();
		orderlist=changeOrderlist(mostorderlist, "撤销");
		if(filledButton.isSelected()){
			orderlist=changeOrderlist(mostorderlist,"是");
		}
		else if(unfilledButton.isSelected()){
			orderlist=changeOrderlist(mostorderlist,"否");
		}
		this.show(orderlist);
	}
	
	@FXML
	public void update_execute(){
		orderlist.clear();
		orderlist=changeOrderlist(mostorderlist, "是");
		if(normalButton.isSelected()){
			orderlist=changeOrderlist(orderlist,"正常");
		}
		else if(abnormalButton.isSelected()){
			orderlist=changeOrderlist(orderlist,"异常");
		}
		else if(cancelButton.isSelected()){
			orderlist=changeOrderlist(orderlist, "撤销");
		}
		this.show(orderlist);
	}
	
	@FXML
	public void update_noexecute(){
		orderlist.clear();
		orderlist=changeOrderlist(mostorderlist, "否");
		if(normalButton.isSelected()){
			orderlist=changeOrderlist(orderlist,"正常");
		}
		else if(abnormalButton.isSelected()){
			orderlist=changeOrderlist(orderlist,"异常");
		}
		else if(cancelButton.isSelected()){
			orderlist=changeOrderlist(orderlist, "撤销");
		}
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
		//没实现根据条件选择
		
		idColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		hotelColumn.setCellValueFactory(celldata -> celldata.getValue().hotelnameProperty());
		executeColumn.setCellValueFactory(celldata -> celldata.getValue().isExecuteProperty());
		stateColumn.setCellValueFactory(celldata -> celldata.getValue().stateProperty());
		startTimeColumn.setCellValueFactory(celldata -> celldata.getValue().startTimeProperty());
		endTimeColumn.setCellValueFactory(celldata -> celldata.getValue().endTimeProperty());
		roomTypeColumn.setCellValueFactory(celldata -> celldata.getValue().roomtypeProperty());
		roomAmountColumn.setCellValueFactory(celldata -> celldata.getValue().roomnumberProperty());
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
			model.setHotelname(helper.getHotelBLService().hotel_checkInfo(ordervo.gethotelid()).getname());
			model.setIsExecute(ordervo.getexecute());
			model.setState(ordervo.getstate());
			model.setStartTime(ordervo.getstart_time());
			ArrayList<RoomOrderVO> roomorderlist=ordervo.getroom_order();
			String roomtype="";
			int roomnumber=0;
			for(int i=0;i<roomorderlist.size();i++){
				roomtype+=(roomorderlist.get(i).getroom_type()+" ");
				roomnumber+=roomorderlist.get(i).getroom_number();
			}
			model.setRoomType(roomtype);
			model.setRoomNumber(String.valueOf(roomnumber));
			model.setPrice(ordervo.getprice());
			model.setNumOfPeople(ordervo.getexpect_number_of_people());
			model.setHaveChild(ordervo.gethave_child());
			mostorderlist.add(model);
		}
		this.show(mostorderlist);
		

	}
	
}
