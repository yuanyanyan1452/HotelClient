package ui.view.order;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	//单价
	int dachuangfangprice;
	int shuangrenfangprice;
	int haohuafangprice;
	int haijingfangprice;
	int shangwufangprice;
	int biaozhunjianprice;
	//该种房间价格
	int dachuangfangprice1;
	int shuangrenfangprice1;
	int haohuafangprice1;
	int haijingfangprice1;
	int shangwufangprice1;
	int biaozhunjianprice1;
	
	int price;
	long daynmber;
	
	RemoteHelper helper=RemoteHelper.getInstance();
	
	@FXML
	private Button generatebuttom;
	
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
		OrderVO order=new OrderVO();
		order.setclientid(currentClient.getclientid());
		order.sethotelid(currentHotel.getid());
		order.setstate(OrderUtil.getNormal());
		order.setexecute(false);
		//时间的处理
		LocalDate start_time=startDatePicker.getValue();
		    ZoneId zone = ZoneId.systemDefault();
		    Instant instant = start_time.atStartOfDay().atZone(zone).toInstant();
		    Date startdate = Date.from(instant);
		LocalDate end_time=endDatePicker.getValue();
		    Instant instant1 = end_time.atStartOfDay().atZone(zone).toInstant();
		    Date enddate = Date.from(instant1);
		LocalDate latest_execute_time=start_time;
		    Instant instant2 = latest_execute_time.atStartOfDay().atZone(zone).toInstant();
		    Date latestdate = Date.from(instant2);
		latestdate.setTime(Integer.parseInt(latestHourComboBox.getValue().toString().substring(0,2))*60*60*1000+latestdate.getTime());
		order.setstart_time(startdate);
		order.setend_time(enddate);
		order.setlatest_execute_time(latestdate);
		//房间的处理
		ArrayList<RoomOrderVO> roomlist=new ArrayList<RoomOrderVO>();
		int daynumber=(int)((enddate.getTime()-startdate.getTime())/1000/3600/24 );
		if(shangwufang.isSelected()&&shangwufangnumber.getValue()!=0){
			RoomOrderVO roomorder=new RoomOrderVO("商务房",shangwufangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(dachuangfang.isSelected()&&dachuangfangnumber.getValue()!=0){
			RoomOrderVO roomorder=new RoomOrderVO("大床房",dachuangfangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(shuangrenfang.isSelected()&&shuangrenfangnumber.getValue()!=0){
			RoomOrderVO roomorder=new RoomOrderVO("双人房",shuangrenfangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(biaozhunjian.isSelected()&&biaozhunjiannumber.getValue()!=0){
			RoomOrderVO roomorder=new RoomOrderVO("标准间",biaozhunjiannumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(haohuafang.isSelected()&&haohuafangnumber.getValue()!=0){
			RoomOrderVO roomorder=new RoomOrderVO("豪华房",haohuafangnumber.getValue(),daynumber);
			roomlist.add(roomorder);
		}
		if(haijingfang.isSelected()&&haijingfangnumber.getValue()!=0){
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
		
		ResultMessage result=helper.getOrderBLService().order_client_generate(order);
		if(result==ResultMessage.Success){
			AlertUtil.showInformationAlert("生成订单成功！");
			main.closeExtraStage();
			main.gotoClientBrowseOrder(currentClient.getclientid());
		}
		else{
			AlertUtil.showErrorAlert("对不起生成订单失败");
		}
	}
	
	//enddatepicker无法点选startdatepicker之前的日期
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
	
	@FXML
	public void updatedaytime(){
		daynmber=endDatePicker.getValue().toEpochDay()-startDatePicker.getValue().toEpochDay();
		price=biaozhunjianprice1+haijingfangprice1+haohuafangprice1+dachuangfangprice1+shangwufangprice1+shuangrenfangprice1;
		price*=daynmber;
		priceLabel.setText(String.valueOf(price));
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
		
		//监听，选中对应酒店类型，酒店数量选框才会出现，酒店类型选择取消后，减去对应价格
		shangwufang.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (shangwufang.isSelected()) {
					shangwufangnumber.setVisible(true);
					
				}
				else{
					shangwufangnumber.setVisible(false);
					price-=(shangwufangprice1*daynmber);
					priceLabel.setText(String.valueOf(price));
					shangwufangnumber.setValue(0);
				}
			}
		});
		biaozhunjian.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (biaozhunjian.isSelected()) {
					biaozhunjiannumber.setVisible(true);
				}
				else{
					biaozhunjiannumber.setVisible(false);
					price-=(biaozhunjianprice1*daynmber);
					priceLabel.setText(String.valueOf(price));
					biaozhunjiannumber.setValue(0);
				}
			}
		});
		
		dachuangfang.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (dachuangfang.isSelected()) {
					dachuangfangnumber.setVisible(true);
				}
				else{
					price-=(dachuangfangprice1*daynmber);
					priceLabel.setText(String.valueOf(price));
					dachuangfangnumber.setVisible(false);
					dachuangfangnumber.setValue(0);
				}
			}
		});
		
		shuangrenfang.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (shuangrenfang.isSelected()) {
					shuangrenfangnumber.setVisible(true);
				}
				else{
					price-=(shuangrenfangprice1*daynmber);
					priceLabel.setText(String.valueOf(price));
					shuangrenfangnumber.setVisible(false);
					shuangrenfangnumber.setValue(0);
				}
			}
		});
		
		haohuafang.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (haohuafang.isSelected()) {
					haohuafangnumber.setVisible(true);
				}
				else{
					price-=(haohuafangprice1*daynmber);
					priceLabel.setText(String.valueOf(price));
					haohuafangnumber.setVisible(false);
					haohuafangnumber.setValue(0);
				}
			}
		});
		
		haijingfang.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(haijingfang.isSelected()){
					haijingfangnumber.setVisible(true);
				}
				else{
					price-=(haijingfangprice1*daynmber);
					priceLabel.setText(String.valueOf(price));
					haijingfangnumber.setVisible(false);
					haijingfangnumber.setValue(0);
				}
			}
			
		});
		
		//监听，选中酒店房间数量之后，价格发生变化
		biaozhunjiannumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				biaozhunjianprice1=biaozhunjianprice*biaozhunjiannumber.getValue();
				price=biaozhunjianprice1+haijingfangprice1+haohuafangprice1+dachuangfangprice1+shangwufangprice1+shuangrenfangprice1;
				price*=daynmber;
				priceLabel.setText(String.valueOf(price));
			}
		});
		
		haohuafangnumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				haohuafangprice1=haohuafangprice*haohuafangnumber.getValue();
				price=biaozhunjianprice1+haijingfangprice1+haohuafangprice1+dachuangfangprice1+shangwufangprice1+shuangrenfangprice1;				
				price*=daynmber;
				priceLabel.setText(String.valueOf(price));
			}
		});
		
		haijingfangnumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				haijingfangprice1=haijingfangprice*haijingfangnumber.getValue();
				price=biaozhunjianprice1+haijingfangprice1+haohuafangprice1+dachuangfangprice1+shangwufangprice1+shuangrenfangprice1;
				price*=daynmber;
				priceLabel.setText(String.valueOf(price));
			}
		});
		
		dachuangfangnumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				dachuangfangprice1=dachuangfangprice*dachuangfangnumber.getValue();
				price=biaozhunjianprice1+haijingfangprice1+haohuafangprice1+dachuangfangprice1+shangwufangprice1+shuangrenfangprice1;
				price*=daynmber;
				priceLabel.setText(String.valueOf(price));
			}
		});
		
		shuangrenfangnumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				shuangrenfangprice1=shuangrenfangprice*shuangrenfangnumber.getValue();
				price=biaozhunjianprice1+haijingfangprice1+haohuafangprice1+dachuangfangprice1+shangwufangprice1+shuangrenfangprice1;
				price*=daynmber;
				priceLabel.setText(String.valueOf(price));
			}
		});
		
		shangwufangnumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				shangwufangprice1=shangwufangprice*shangwufangnumber.getValue();
				price=biaozhunjianprice1+haijingfangprice1+haohuafangprice1+dachuangfangprice1+shangwufangprice1+shuangrenfangprice1;
				price*=daynmber;
				priceLabel.setText(String.valueOf(price));
			}
		});
		
		//初始化combobox中的选项
		latestHourComboBox.getItems().addAll("12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00");
		latestHourComboBox.setVisibleRowCount(5);
		peopleNumComboBox.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
		peopleNumComboBox.setVisibleRowCount(5);
		
		//初始化酒店房间信息
		for(int i=0;i<roomlist.size();i++){
			if(roomlist.get(i).getroom_type().equals("商务房")){
				shangwufang.setVisible(true);
				shangwufangprice=roomlist.get(i).getprice();
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					shangwufangnumber.getItems().add(j+1);
				}
			}
			if(roomlist.get(i).getroom_type().equals("大床房")){
				dachuangfang.setVisible(true);
				dachuangfangprice=roomlist.get(i).getprice();
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					dachuangfangnumber.getItems().add(j+1);
				}
			}
			if(roomlist.get(i).getroom_type().equals("双人房")){
				shuangrenfang.setVisible(true);
				shuangrenfangprice=roomlist.get(i).getprice();
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					shuangrenfangnumber.getItems().add(j+1);
				}
			}
			if(roomlist.get(i).getroom_type().equals("海景房")){
				haijingfang.setVisible(true);
				haijingfangprice=roomlist.get(i).getprice();
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					haijingfangnumber.getItems().add(j+1);
				}
			}
			if(roomlist.get(i).getroom_type().equals("豪华房")){
				haohuafang.setVisible(true);
				haohuafangprice=roomlist.get(i).getprice();
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					haohuafangnumber.getItems().add(j+1);
				}
			}
			if(roomlist.get(i).getroom_type().equals("标准间")){
				biaozhunjian.setVisible(true);
				biaozhunjianprice=roomlist.get(i).getprice();
				for(int j=0;j<roomlist.get(i).getavailable_num();j++){
					biaozhunjiannumber.getItems().add(j+1);
				}
			}
		}
		
		
		//有无儿童单选框
		final ToggleGroup toggleGroup = new ToggleGroup();
		hasChildButton.setSelected(true);
		hasNoChildButton.setSelected(false);
		hasChildButton.setToggleGroup(toggleGroup);
		hasNoChildButton.setToggleGroup(toggleGroup);
		
		//datepicker无法点选之前的日期
		
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

		//generatebuttom.setVisible(false);
	          
	    
	}

}
