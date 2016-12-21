package ui.view.order;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import ui.model.HotelModel;
import ui.view.Main;
import vo.ClientVO;
import vo.HotelVO;

public class ClientGenerateOrderController implements Initializable {
	private Main main;
	private HotelVO currentHotel;
	private ClientVO currentClient;
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
	private void generate(){
		//TODO
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
		
		
		//TODO 房间类型及其对应数量的选择
	}

}
