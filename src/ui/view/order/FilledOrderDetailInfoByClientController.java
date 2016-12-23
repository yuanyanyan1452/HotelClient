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

public class FilledOrderDetailInfoByClientController implements Initializable {
	private Main main;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private OrderVO currentordervo;
	
	RemoteHelper helper=RemoteHelper.getInstance();
	@FXML
	private Button closebutton;
	
	@FXML
	private Button evaluatebutton;
	
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
	private Label executelabel;
	
	@FXML
	private void close(){
		main.closeExtraStage();
	}
	
	@FXML
	public void evaluate(){
		try {
			main.gotoClientEvaluateHotel(helper.getClientBLService().client_checkInfo(currentordervo.getclientid()),helper.getHotelBLService().hotel_getInfo(currentordervo.gethotelid()),currentordervo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public FilledOrderDetailInfoByClientController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main,OrderVO ordervo) throws RemoteException {
		this.main = main;
		currentordervo=ordervo;
		clientnamelabel.setText(helper.getClientBLService().checkClientInfo(currentordervo.getclientid()).getclient_name());
		hotelnamelabel.setText(helper.getHotelBLService().hotel_getInfo(currentordervo.gethotelid()).getname());
		orderidlabel.setText(String.valueOf(currentordervo.getid()));
		orderstatelabel.setText(currentordervo.getstate());
		if(currentordervo.getexecute()){
			executelabel.setText("已执行");
		}
		else{
			executelabel.setText("未执行");
		}
		starttimelabel.setText(format.format(currentordervo.getstart_time()));
		endtimelabel.setText(format.format(currentordervo.getend_time()));
		ArrayList<RoomOrderVO> roomorderlist=currentordervo.getroom_order();
		String roomtype="";
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
