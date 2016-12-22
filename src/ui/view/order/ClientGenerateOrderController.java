package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;
import rmi.RemoteHelper;
import ui.util.OrderUtil;
import ui.view.Main;
import vo.ClientVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomOrderVO;

public class ClientGenerateOrderController implements Initializable {
	private Main main;
	private HotelVO currentHotel;
	private ClientVO currentClient;
	
	RemoteHelper helper=RemoteHelper.getInstance();
	@FXML
	private Label hotelnameLabel;
	
	@FXML
	private Label priceLabel;
	
	@FXML
	private DatePicker startDatePicker;
	
	@FXML
	private DatePicker endDatePicker;
	
	@FXML
	private ComboBox<String> latestHourComboBox;
	
	
	@FXML
	private ComboBox<String> peopleNumComboBox;
	
	@FXML
	private RadioButton hasChildButton;
	
	@FXML
	private RadioButton hasNoChildButton;
	
	@FXML
	private Label strategyLabel;
	
	@FXML
	private void close(){
		main.closeExtraStage();
	}
	
	@FXML
	private void generate() throws RemoteException{
		//TODO
		OrderVO order=new OrderVO();
		order.setclientid(currentClient.getclientid());
		order.sethotelid(currentHotel.getid());
		order.setstate(OrderUtil.getNormal());
		order.setexecute(false);
		//
		Date start_time=new Date();
		Date end_time=new Date();
		Date latest_execute_time=new Date();
		order.setstart_time(start_time);
		order.setend_time(end_time);
		order.setlatest_execute_time(latest_execute_time);
		ArrayList<RoomOrderVO> roomlist=new ArrayList<RoomOrderVO>();
		order.setprice((int)helper.getOrderBLService().calculateTotalwithStrategy(roomlist,currentHotel.getid(),currentClient.getclientid()));
		order.setexpect_number_of_people(Integer.parseInt(peopleNumComboBox.getValue()));
		if(hasChildButton.isSelected()){
		order.sethave_child(true);
		}
		else if(hasNoChildButton.isSelected()){
			order.sethave_child(false);
		}
	}
	public ClientGenerateOrderController() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}


	public void setMain(Main main , HotelVO hotel,ClientVO client) {
		this.main = main;
		currentClient = client;
		currentHotel =hotel;
		//设置当前酒店
		hotelnameLabel.setText(hotel.getname());
		
		//初始化combobox中的选项
		latestHourComboBox.getItems().addAll("12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00");
		latestHourComboBox.setVisibleRowCount(5);
		peopleNumComboBox.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
		peopleNumComboBox.setVisibleRowCount(5);
		
		//有无儿童单选框
		final ToggleGroup toggleGroup = new ToggleGroup();
		hasChildButton.setSelected(true);
		hasNoChildButton.setSelected(false);
		hasChildButton.setToggleGroup(toggleGroup);
		hasNoChildButton.setToggleGroup(toggleGroup);
		//TODO datepicker无法点选之前的日期；enddatepicker无法点选startdatepicker之前的日期
		final Callback<DatePicker, DateCell> dayCellFactory = 
	            new Callback<DatePicker, DateCell>() {
	                @Override
	                public DateCell call(final DatePicker datePicker) {
	                    return new DateCell() {
	                        @Override
	                        public void updateItem(LocalDate item, boolean empty) {
	                            super.updateItem(item, empty);

	                            if (item.isBefore(
	                                    startDatePicker.getValue().plusDays(1))
	                                ) {
	                                    setDisable(true);
	                                    setStyle("-fx-background-color: #ffc0cb;");
	                            }   
	                    }
	                };
	            }
	        };
	        endDatePicker.setDayCellFactory(dayCellFactory);
	        
	        final Callback<DatePicker, DateCell> dayCellFactory2 = 
	                new Callback<DatePicker, DateCell>() {
	        	LocalDate today=LocalDate.now();
	                    @Override
	                    public DateCell call(final DatePicker datePicker) {
	                        return new DateCell() {
	                            @Override
	                            public void updateItem(LocalDate item, boolean empty) {
	                                super.updateItem(item, empty);

	                                if (item.isBefore(
	                                        today
	                                    )) {
	                                        setDisable(true);
	                                        setStyle("-fx-background-color: #ffc0cb;");
	                                }   
	                        }
	                    };
	                }
	            };
	            startDatePicker.setDayCellFactory(dayCellFactory2);

		
		//TODO 房间类型及其对应数量的选择
	}

}
