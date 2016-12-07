package ui.view.market;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import ui.model.*;
import ui.view.Main;

public class MarketStrategyController implements Initializable {
	private Main main;
	private ArrayList<Integer> IDList = new ArrayList<Integer>();
	private WebStrategyModel currentStrategy;
	
	@FXML
	private TableView<WebStrategyModel> strategyTable;

	@FXML
	private TableColumn<WebStrategyModel, String> nameColumn;

	@FXML
	private TableColumn<WebStrategyModel, String> startColumn;

	@FXML
	private TableColumn<WebStrategyModel, String> endColumn;

	@FXML
	private TableColumn<WebStrategyModel, String> discountColumn;

	@FXML
	private TableColumn<WebStrategyModel, String> conditionColumn;

	@FXML
	private TableColumn<WebStrategyModel, String> superpositionColumn;

	@FXML
	private TextField addNameField;

	@FXML
	private TextField addStartTimeField;

	@FXML
	private TextField addEndTimeField;

	@FXML
	private TextField addConditionField;

	@FXML
	private TextField addDiscountField;

	@FXML
	private RadioButton addYesButton;

	@FXML
	private RadioButton addNoButton;

	@FXML
	private TextField updateNameField;

	@FXML
	private TextField updateStartTimeField;

	@FXML
	private TextField updateEndTimeField;

	@FXML
	private TextField updateConditionField;

	@FXML
	private TextField updateDiscountField;

	@FXML
	private RadioButton updateYesButton;

	@FXML
	private RadioButton updateNoButton;

	@FXML
	private TextField deleteNameField;

	@FXML
	private Label deleteStartTimeLabel;

	@FXML
	private Label deleteEndTimeLabel;

	@FXML
	private Label deleteConditionLabel;

	@FXML
	private Label deleteDiscountLabel;

	@FXML
	private Label deleteSuperpositionLabel;

	@FXML
	private void deleteSearchStrategy() {
		String name = deleteNameField.getText();
		boolean isSuccess = false;
		ObservableList<WebStrategyModel> list = strategyTable.getItems();
		for (WebStrategyModel strategy : list) {
			if (strategy.getName().equals(name)) {
				currentStrategy = strategy;
				deleteStartTimeLabel.setText(strategy.getStartTime());
				deleteEndTimeLabel.setText(strategy.getEndTime());
				deleteDiscountLabel.setText(strategy.getDiscount());
				deleteConditionLabel.setText(strategy.getCondition());
				deleteSuperpositionLabel.setText(strategy.getSuperposition());
				
				isSuccess = true;
			}
		}
		if(!isSuccess){
			deleteStartTimeLabel.setText("");
			deleteEndTimeLabel.setText("");
			deleteDiscountLabel.setText("");
			deleteConditionLabel.setText("");
			deleteSuperpositionLabel.setText("");
		}
	}

	@FXML
	private void updateSearchStrategy() {
		String name = updateNameField.getText();
		boolean isSuccess = false;
		ObservableList<WebStrategyModel> list = strategyTable.getItems();
		for (WebStrategyModel strategy : list) {
			if (strategy.getName().equals(name)) {
				currentStrategy = strategy;
				updateStartTimeField.setText(strategy.getStartTime());
				updateEndTimeField.setText(strategy.getEndTime());
				updateDiscountField.setText(strategy.getDiscount());
				updateConditionField.setText(strategy.getCondition());
				if(strategy.getSuperposition().equals("是")){
					updateYesButton.setSelected(true);
				}
				else{
					updateNoButton.setSelected(false);
				}
				
				isSuccess = true;
			}
		}
		if(!isSuccess){
			updateStartTimeField.setText("");
			updateEndTimeField.setText("");
			updateDiscountField.setText("");
			updateConditionField.setText("");
			updateYesButton.setSelected(false);
			updateNoButton.setSelected(false);
		}
	}

	@FXML
	private void addStrategy() {
		int id = createID();
		String name = addNameField.getText();
		String startTime = addStartTimeField.getText();
		String endTime = addEndTimeField.getText();
		String condition = addConditionField.getText();
		String discount = addDiscountField.getText();
		String superposition = (addYesButton.isSelected()) ? "是" : "否";

		WebStrategyModel strategyModel = new WebStrategyModel(id, name, startTime, endTime, discount, condition,
				superposition);
		strategyTable.getItems().add(strategyModel);
	}

	@FXML
	private void updateStrategy() {
		String name = updateNameField.getText();
		String startTime = updateStartTimeField.getText();
		String endTime = updateEndTimeField.getText();
		String condition = updateConditionField.getText();
		String discount = updateDiscountField.getText();
		String superposition = (updateYesButton.isSelected()) ? "是" : "否";
		
		ObservableList<WebStrategyModel> list = strategyTable.getItems();
		list.remove(currentStrategy);
		
		currentStrategy.setName(name);
		currentStrategy.setStartTime(startTime);
		currentStrategy.setEndTime(endTime);
		currentStrategy.setDiscount(discount);
		currentStrategy.setCondition(condition);
		currentStrategy.setSuperposition(superposition);
		
		list.add(currentStrategy);
	}

	@FXML
	private void deleteStrategy() {
		if(strategyTable.getItems().contains(currentStrategy)){
			strategyTable.getItems().remove(currentStrategy);
			IDList.remove(IDList.indexOf(currentStrategy.getID()));
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("不存在该促销策略！");
			alert.setHeaderText("出错了！");
			alert.show();
		}
	}

	// 创建strategID
	public int createID() {
		int newID = IDList.size() + 1;
		return newID;
	}

	public MarketStrategyController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
		

		final ToggleGroup addGroup = new ToggleGroup();
		addYesButton.setToggleGroup(addGroup);
		addNoButton.setToggleGroup(addGroup);
		final ToggleGroup updateGroup = new ToggleGroup();
		updateYesButton.setToggleGroup(updateGroup);
		updateNoButton.setToggleGroup(updateGroup);

		ObservableList<WebStrategyModel> webStrategyData = FXCollections.observableArrayList();
		webStrategyData.add(new WebStrategyModel(1, "双十一优惠", "11/11", "11/12", "0.5", "无条件", "是"));
		webStrategyData.add(new WebStrategyModel(2, "会员折扣", "01/01", "12/31", "0.8", "会员适用", "是"));

		for (WebStrategyModel model : webStrategyData) {
			IDList.add(model.getID());
		}
		nameColumn.setCellValueFactory(celldata -> celldata.getValue().nameProperty());
		startColumn.setCellValueFactory(celldata -> celldata.getValue().startTimeProperty());
		endColumn.setCellValueFactory(celldata -> celldata.getValue().endTimeProperty());
		discountColumn.setCellValueFactory(celldata -> celldata.getValue().discountProperty());
		conditionColumn.setCellValueFactory(celldata -> celldata.getValue().conditionProperty());
		superpositionColumn.setCellValueFactory(celldata -> celldata.getValue().superpostionProperty());

		strategyTable.setItems(webStrategyData);
	}
}
