package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.OrderVO;
import vo.RoomOrderVO;

public class OrderDetailInfoByHotelController implements Initializable{
	private Main main;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private OrderVO currentordervo;
	RemoteHelper helper=RemoteHelper.getInstance();
	
	@FXML
	private Button closebutton;
	
	@FXML
	private Button delaybutton;
	
	@FXML
	private Button executebutton;
	
	@FXML
	private Label clientnamelabel;
	
	@FXML
	private Label orderidlabel;
	
	@FXML
	private Label orderstatelabel;
	
	@FXML
	private Label executeLabel;
	
	@FXML
	private Label starttimelabel;
	
	@FXML
	private Label endtimelabel;
	
	@FXML
	private Label roomtypelabel;
	
	@FXML
	private Label roomnumberlabel;
	
	@FXML
	private Label peoplenumberlabel;
	
	@FXML
	private Label ifchildlabel;
	
	@FXML
	private Label pricelabel;
	
	@FXML
	private void close(){
		main.closeExtraStage();
	}
	
	@FXML
	public void dealy(){
		
	}
	
	@FXML 
	public void execute(){
		
	}
	
	public OrderDetailInfoByHotelController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main,OrderVO ordervo) throws RemoteException {
		this.main = main;
		currentordervo=ordervo;
		clientnamelabel.setText(helper.getClientBLService().checkClientInfo(currentordervo.getclientid()).getclient_name());
		orderidlabel.setText(String.valueOf(currentordervo.getid()));
		orderstatelabel.setText(currentordervo.getstate());
		if(currentordervo.getexecute()){
			executeLabel.setText("已执行");
		}
		else{
			executeLabel.setText("未执行");
		}
		starttimelabel.setText(format.format(currentordervo.getstart_time()));
		endtimelabel.setText(format.format(currentordervo.getend_time()));
		ArrayList<RoomOrderVO> roomorderlist=currentordervo.getroom_order();
		String roomtype=null;
		int roomnumber=0;
		for(int i=0;i<roomorderlist.size();i++){
			roomtype+=(roomorderlist.get(i).getroom_type()+" ");
			roomnumber+=roomorderlist.get(i).getroom_number();
		}
		roomtypelabel.setText(roomtype);
		roomnumberlabel.setText(String.valueOf(roomnumber));
		peoplenumberlabel.setText(String.valueOf(currentordervo.getexpect_number_of_people()));
		if(currentordervo.gethave_child()){
			ifchildlabel.setText("有");
		}
		else{
			ifchildlabel.setText("无");
		}
		pricelabel.setText(String.valueOf(currentordervo.getprice()));
	}
}
