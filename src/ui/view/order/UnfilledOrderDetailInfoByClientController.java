package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.util.AlertUtil;
import ui.util.RecordActionUtil;
import ui.view.Main;
import vo.OrderVO;
import vo.RoomOrderVO;

public class UnfilledOrderDetailInfoByClientController implements Initializable {
	private Main main;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private OrderVO currentordervo;
	
	RemoteHelper helper=RemoteHelper.getInstance();
	@FXML
	private Button closebutton;
	
	@FXML
	private Button cancelbutton;
	
	@FXML
	private Label clientnamelabel;
	
	@FXML
	private Label hotelnamelabel;
	
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
	public void cancel() throws RemoteException{
		currentordervo.setstate("撤销");
		ResultMessage result=helper.getOrderBLService().order_client_cancel(currentordervo.getid());
		if(result==ResultMessage.Success){
			main.closeExtraStage();
			AlertUtil.showInformationAlert("撤销订单成功");
			this.update();
			Date date=new Date();
			if(currentordervo.getlatest_execute_time().getTime()-date.getTime()<6*60*60*1000){
				helper.getClientBLService().updateClientCredit(currentordervo.getclientid(), currentordervo.getprice()/2, 0);
				String creditinfo=format.format(date)+","+String.valueOf(currentordervo.getid())+","+RecordActionUtil.getClientCancel()+","+"-"+String.valueOf(currentordervo.getprice()/2)+","+String.valueOf(helper.getClientBLService().client_checkCredit(currentordervo.getclientid()));
				helper.getClientBLService().client_updateClientCreditList(currentordervo.getclientid(), creditinfo);
			}
		}
		else{
			AlertUtil.showErrorAlert("对不起，撤销订单失败");
			currentordervo.setstate("正常");
		}
	}
	
	public UnfilledOrderDetailInfoByClientController() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public void update() throws RemoteException{
		clientnamelabel.setText(helper.getClientBLService().checkClientInfo(currentordervo.getclientid()).getclient_name());
		hotelnamelabel.setText(helper.getHotelBLService().hotel_getInfo(currentordervo.gethotelid()).getname());
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

	public void setMain(Main main,OrderVO ordervo) throws RemoteException {
		this.main = main;
		currentordervo=ordervo;
		this.update();
	}

}
