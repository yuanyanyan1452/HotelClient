package ui.view.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import ui.view.Main;

public class ClientEvaluateHotelController implements Initializable{
	private Main main;
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
		//TODO
		
	}
	@FXML
	private void initialize(){
		
	}
	
	public ClientEvaluateHotelController() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMain(Main main){
		this.main = main;
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
