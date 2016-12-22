package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.util.AlertUtil;
import ui.util.OrderUtil;
import ui.view.Main;
import vo.ClientVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.RoomVO;

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
	private CheckBox shangwufang;
	
	@FXML
	private ComboBox<Integer> shangwufangnumber;
	
	@FXML
	private CheckBox dachuangfang;
	
	@FXML
	private ComboBox<Integer> dachuangfangnumber;
	
	@FXML
	private CheckBox shuangrenfang;
	
	@FXML
	private ComboBox<Integer> shuangrenfangnumber;
	
	@FXML
	private CheckBox biaozhunjian;
	
	@FXML
	private ComboBox<Integer> biaozhunjiannumber;
	
	@FXML
	private CheckBox haohuafang;
	
	@FXML
	private ComboBox<Integer> haohuafangnumber;
	
	@FXML
	private CheckBox haijingfang;
	
	@FXML
	private ComboBox<Integer> haijingfangnumber;
	
	
	
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
		LocalDate start_time=startDatePicker.getValue();
		    ZoneId zone = ZoneId.systemDefault();
		    Instant instant = start_time.atStartOfDay().atZone(zone).toInstant();
		    Date startdate = Date.from(instant);
		LocalDate end_time=endDatePicker.getValue();
		    Instant instant1 = end_time.atStartOfDay().atZone(zone).toInstant();
		    Date enddate = Date.from(instant1);
		LocalDate latest_execute_time=start_time.plusDays(1);
		    Instant instant2 = latest_execute_time.atStartOfDay().atZone(zone).toInstant();
		    Date latestdate = Date.from(instant2);
		   // latestdate.setTime(Integer.parseInt(latestHourComboBox.getSelectionModel().toString().substring(0,2))*60*60*1000+latestdate.getTime());
		latestdate.setTime(latestdate.getTime()+12*3600*1000);
		System.out.println(latestdate);
		order.setstart_time(startdate);
		order.setend_time(enddate);
		order.setlatest_execute_time(latestdate);
		ArrayList<RoomOrderVO> roomlist=new ArrayList<RoomOrderVO>();
		int daynumber=(int)((enddate.getTime()-startdate.getTime())/1000/3600/24 );
		if(shangwufang.isSelected()){
			shangwufangnumber.setVisible(true);
			RoomOrderVO roomorder=new RoomOrderVO("商务房",shangwufangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(dachuangfang.isSelected()){
			dachuangfangnumber.setVisible(true);
			RoomOrderVO roomorder=new RoomOrderVO("大床房",dachuangfangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(shuangrenfang.isSelected()){
			shuangrenfangnumber.setVisible(true);
			RoomOrderVO roomorder=new RoomOrderVO("双人房",shuangrenfangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(biaozhunjian.isSelected()){
			biaozhunjian.setVisible(true);
			RoomOrderVO roomorder=new RoomOrderVO("标准间",biaozhunjiannumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(haohuafang.isSelected()){
			haohuafang.setVisible(true);
			RoomOrderVO roomorder=new RoomOrderVO("豪华房",haohuafangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(haijingfang.isSelected()){
			haijingfangnumber.setVisible(true);
			RoomOrderVO roomorder=new RoomOrderVO("海景房",haijingfangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		order.setroom_order(roomlist);
		order.setprice((int)helper.getOrderBLService().calculateTotalwithStrategy(roomlist,currentHotel.getid(),currentClient.getclientid()));
		order.setexpect_number_of_people(Integer.parseInt(peopleNumComboBox.getValue()));
		if(hasChildButton.isSelected()){
		order.sethave_child(true);
		}
		else if(hasNoChildButton.isSelected()){
			order.sethave_child(false);
		}
//		System.out.println(roomlist.size());
		ResultMessage result=helper.getOrderBLService().order_client_generate(order);
//		System.out.println(helper.getOrderBLService().calculateTotalwithStrategy(roomlist,currentHotel.getid(),currentClient.getclientid()));
//		System.out.println((int)helper.getOrderBLService().calculateTotalwithStrategy(roomlist,currentHotel.getid(),currentClient.getclientid()));
//		System.out.println(result);
		if(result==ResultMessage.Success){
			AlertUtil.showInformationAlert("生成订单成功！");
			main.gotoHotelDetailInfo(currentHotel, currentClient.getclientid());
		}
		else{
			AlertUtil.showErrorAlert("对不起生成订单失败");
		}
	}
	
	@FXML 
	public void updateendtime(){
		final Callback<DatePicker, DateCell> dayCellFactory1 = 
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
            endDatePicker.setDayCellFactory(dayCellFactory1);
	}
	public ClientGenerateOrderController() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}


	public void setMain(Main main , HotelVO hotel,ClientVO client) throws RemoteException {
		this.main = main;
		currentClient = client;
		currentHotel =hotel;
		//设置当前酒店
		hotelnameLabel.setText(hotel.getname());
		ArrayList<RoomVO> roomlist=helper.getHotelBLService().getallroom(hotel.getid());
		shangwufang.setVisible(false);
		dachuangfang.setVisible(false);
		shuangrenfang.setVisible(false);
		haohuafang.setVisible(false);
		haijingfang.setVisible(false);
		biaozhunjian.setVisible(false);
		shangwufangnumber.setVisible(false);
		dachuangfangnumber.setVisible(false);
		shuangrenfangnumber.setVisible(false);
		haohuafangnumber.setVisible(false);
		haijingfangnumber.setVisible(false);
		biaozhunjiannumber.setVisible(false);
		//初始化combobox中的选项
		latestHourComboBox.getItems().addAll("12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00");
		latestHourComboBox.setVisibleRowCount(5);
		peopleNumComboBox.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
		peopleNumComboBox.setVisibleRowCount(5);
		for(int i=0;i<roomlist.size();i++){
			if(roomlist.get(i).getroom_type().equals("商务房")){
				shangwufang.setVisible(true);
				
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					shangwufangnumber.getItems().add(j);
				}
			}
			if(roomlist.get(i).getroom_type().equals("大床房")){
				dachuangfang.setVisible(true);
				
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					dachuangfangnumber.getItems().add(j);
				}
			}
			if(roomlist.get(i).getroom_type().equals("双人房")){
				shuangrenfang.setVisible(true);
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					shuangrenfangnumber.getItems().add(j);
				}
			}
			if(roomlist.get(i).getroom_type().equals("海景房")){
				haijingfang.setVisible(true);
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					haijingfangnumber.getItems().add(j);
				}
			}
			if(roomlist.get(i).getroom_type().equals("豪华房")){
				haohuafang.setVisible(true);
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					haohuafangnumber.getItems().add(j);
				}
			}
			if(roomlist.get(i).getroom_type().equals("标准间")){
				biaozhunjian.setVisible(true);
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					biaozhunjiannumber.getItems().add(j);
				}
			}
		}
		
		
		//有无儿童单选框
		final ToggleGroup toggleGroup = new ToggleGroup();
		hasChildButton.setSelected(true);
		hasNoChildButton.setSelected(false);
		hasChildButton.setToggleGroup(toggleGroup);
		hasNoChildButton.setToggleGroup(toggleGroup);
		//TODO datepicker无法点选之前的日期；enddatepicker无法点选startdatepicker之前的日期
		
		LocalDate today=LocalDate.now();
		final Callback<DatePicker, DateCell> dayCellFactory = 
	            new Callback<DatePicker, DateCell>() {
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
	        startDatePicker.setValue(today);
	        startDatePicker.setDayCellFactory(dayCellFactory);
	        endDatePicker.setDayCellFactory(dayCellFactory);

		
		//TODO 房间类型及其对应数量的选择
	          
	    
	}

}
