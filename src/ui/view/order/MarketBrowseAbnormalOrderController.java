package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import rmi.RemoteHelper;
import ui.model.ClientModel;
import ui.model.OrderModel;
import ui.view.Main;
import vo.OrderVO;

public class MarketBrowseAbnormalOrderController implements Initializable {
	private Main main;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private ClientModel currentClient;
	
	@FXML
	private TableView<OrderModel> orderTable;
	
	@FXML
	private TableColumn<OrderModel, String> idColumn;
	
	@FXML
	private TableColumn<OrderModel, String> clientColumn;
	
	@FXML
	private TableColumn<OrderModel, String> deadTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> overTimeColumn;
	
	@FXML
	private TableColumn<OrderModel, String> punishColumn;
	
	@FXML
	private ComboBox<String> choiceComboBox;
	
	@FXML
	private void cancelAbnormalOrder(){
		System.out.println(choiceComboBox.getValue());
	}
	public MarketBrowseAbnormalOrderController() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

	public void setMain(Main main) {
		this.main = main;
		RemoteHelper helper = RemoteHelper.getInstance();
		
		ObservableList<String> choiceList = choiceComboBox.getItems();
		choiceList.addAll("全部信用值","一半信用值");
		
		choiceComboBox.setCellFactory(
				new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				final ListCell<String> cell = new ListCell<String>(){
					@Override
					 public void updateItem(String item,boolean empty){
						super.updateItem(item, empty);
						if (item.isEmpty()) {
							setText(item);
						}
						else {
							setText("");
						}
					}
				};
				return cell;
			}
		});
		
		//vo转成可以在tableview中显示的model
		ObservableList<OrderModel> orderList = FXCollections.observableArrayList();
		try {
			ArrayList<OrderVO> orderdata = helper.getOrderBLService().order_market_browseUnfilled();
			for(OrderVO vo:orderdata){
				OrderModel model = new OrderModel();
				model.setOrderid(vo.getid());
				model.setClientid(vo.getclientid());
				model.setLatestExecuteTime(vo.getlatest_execute_time());
				model.setOverTime(new Date());
				model.setPunishCredit(vo.getprice());
				orderList.add(model);
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		idColumn.setCellValueFactory(celldata -> celldata.getValue().orderidProperty());
		clientColumn.setCellValueFactory(celldata -> celldata.getValue().clientidProperty());
		deadTimeColumn.setCellValueFactory(celldata -> celldata.getValue().latestExecuteTimeProperty());
		overTimeColumn.setCellValueFactory(celldata -> celldata.getValue().overTimeProperty());
		punishColumn.setCellValueFactory(celldata -> celldata.getValue().punishCreditProperty());
		
		orderTable.setItems(orderList);
	}
}
