package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.OrderVO;
import vo.RoomOrderVO;

public class AbnormalOrderDetailInfoByClientController implements Initializable {
	private Main main;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	RemoteHelper helper=RemoteHelper.getInstance();
	
	@FXML
	private Label clientnamelabel;
	
	@FXML
	private Label hotelnamelabel;
	
	@FXML
	private Label orderidlabel;
	
	@FXML
	private Label orderstatelabel;
	
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
	private Label orderexecutelabel;
	
	@FXML
	private void close(){
		main.closeExtraStage();
	}
	
	public AbnormalOrderDetailInfoByClientController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	
	public void setMain(Main main,OrderVO ordervo) throws RemoteException {
		this.main = main;
		clientnamelabel.setText(helper.getClientBLService().checkClientInfo(ordervo.getclientid()).getclient_name());
		hotelnamelabel.setText(helper.getHotelBLService().hotel_checkInfo(ordervo.gethotelid()).getname());
		orderidlabel.setText(String.valueOf(ordervo.getid()));
		orderstatelabel.setText(ordervo.getstate());
		if(ordervo.getexecute()){
		orderexecutelabel.setText("已执行");
		}
		else{
			orderexecutelabel.setText("未执行");
		}
		starttimelabel.setText(format.format(ordervo.getstart_time()));
		endtimelabel.setText(format.format(ordervo.getend_time()));
		ArrayList<RoomOrderVO> roomorderlist=ordervo.getroom_order();
		String roomtype=null;
		int roomnumber=0;
		for(int i=0;i<roomorderlist.size();i++){
			roomtype+=(roomorderlist.get(i).getroom_type()+" ");
			roomnumber+=roomorderlist.get(i).getroom_number();
		}
		roomtypelabel.setText(roomtype);
		roomnumberlabel.setText(String.valueOf(roomnumber));
		peoplenumberlabel.setText(String.valueOf(ordervo.getexpect_number_of_people()));
		if(ordervo.gethave_child()){
			ifchildlabel.setText("有");
		}
		else{
			ifchildlabel.setText("无");
		}
		pricelabel.setText(String.valueOf(ordervo.getprice()));
	}
}
