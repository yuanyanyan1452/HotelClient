package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import rmi.RemoteHelper;
import ui.view.Main;
import vo.HotelVO;
import vo.HotelWorkerVO;

public class HotelOverviewController implements Initializable {
	private Main main;
	RemoteHelper helper =  RemoteHelper.getInstance();
	private HotelVO currenthotelvo;
	private HotelWorkerVO currenthotelworkervo;
	
	public void updateHotelVO(HotelVO hotelVO){
		currenthotelvo = hotelVO;
		hotelnamelabel.setText(currenthotelvo.getname());
	}
	public void updateWorkerVO(HotelWorkerVO workerVO){
		currenthotelworkervo = workerVO;
		namelabel.setText(currenthotelworkervo.getname());
		contactlabel.setText(currenthotelworkervo.getcontact());
	}
	
	@FXML
	private Label hotelnamelabel;

	@FXML
	private Label namelabel;
	
	@FXML
	private Label contactlabel;
	

	@FXML
	public void gotoHotelBasicInfo() throws RemoteException {
		main.gotoHotelBasicInfo(currenthotelvo,this);
	}

	@FXML
	public void gotoHotelBrowseOrder() {
		main.gotoHotelBrowseOrder(currenthotelvo.getid());
	}


	@FXML
	public void gotoHotelCheckIn() {
		main.gotoHotelCheckIn(currenthotelvo);
	}


//	@FXML
//	public void gotoHotelExecuteOrder() {
//		main.gotoHotelExecuteOrder();
//	}


	@FXML
	public void gotoHotelRoomManage() {
		main.gotoHotelRoomManage();
	}

	@FXML
	public void gotoHotelStrategyManage() {
		main.gotoHotelStrategyManage(currenthotelvo);
	}

	public HotelOverviewController() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main,HotelWorkerVO hotelworkervo,HotelVO hotelvo) throws RemoteException {
		this.main = main;
		currenthotelvo=hotelvo;
		currenthotelworkervo=hotelworkervo;
		hotelnamelabel.setText(hotelvo.getname());
		namelabel.setText(hotelworkervo.getname());
		contactlabel.setText(hotelworkervo.getcontact());
		
	}
	
//	public void update() throws RemoteException{
//		currenthotelvo=helper.getHotelBLService().hotel_checkInfo(currenthotelvo.getid());
//	}

}
