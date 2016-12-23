package ui.view.client;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.ClientVO;
import vo.EvaluationVO;
import vo.HotelVO;
import vo.OrderVO;

public class ClientEvaluateHotelController implements Initializable{
	private Main main;
	private ClientVO currentClient;
	private HotelVO currentHotel;
	private OrderVO currentorder;
	RemoteHelper helper=RemoteHelper.getInstance();
	
	@FXML
	private Label hotelnameLabel;
	
	@FXML
	private Label scoreLabel;
	
	@FXML
	private Slider scoreSlider;
	
	@FXML
	private TextArea commentArea;
	
	@FXML
	private void close(){
		main.closeExtraStage();
	}
	
	@FXML
	private void evaluate(){
		EvaluationVO evaluation = new EvaluationVO(Double.parseDouble(scoreLabel.getText()), commentArea.getText());
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			ResultMessage message = helper.getHotelBLService().evalutehotel(evaluation, currentClient.getclientid(),currentHotel.getid());
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("评价失败。。");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		main.closeExtraStage();
		AlertUtil.showInformationAlert("评价成功！");
	}
	
	public ClientEvaluateHotelController() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main,ClientVO clientvo,HotelVO hotelvo,OrderVO orderVO){
		this.main = main;
		currentClient = clientvo;
		currentHotel = hotelvo;
		currentorder=orderVO;
		hotelnameLabel.setText(hotelvo.getname());
		
		//初始化评价
		commentArea.setText(currentorder.getevaluation());
		
		//初始化滑动条
		scoreSlider.setMin(0.0);
		scoreSlider.setMax(5.0);
		scoreSlider.setValue(4.0);
		scoreLabel.setText("4.0");
		scoreSlider.setShowTickLabels(true);
		scoreSlider.setShowTickMarks(true);
		scoreSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				scoreLabel.setText(String.format("%.1f", newValue));
			}
			
		});
		
		
	}
	
}
