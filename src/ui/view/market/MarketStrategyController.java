package ui.view.market;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import objects.ResultMessage;
import rmi.RemoteHelper;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import ui.model.*;
import ui.util.AlertUtil;
import ui.view.Main;
import vo.WebStrategyVO;

public class MarketStrategyController implements Initializable {
	private Main main;
	private WebStrategyModel currentStrategy;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	private DatePicker addStartTimeDatePicker;

	@FXML
	private DatePicker addEndTimeDatePicker;

	@FXML
	private ComboBox<String> addConditionComboBox;

	@FXML
	private ComboBox<String> addDiscountComboBox;

	@FXML
	private RadioButton addYesButton;

	@FXML
	private RadioButton addNoButton;

	@FXML
	private TextField updateNameField;

	@FXML
	private DatePicker updateStartTimeDatePicker;

	@FXML
	private DatePicker updateEndTimeDatePicker;

	@FXML
	private ComboBox<String> updateConditionComboBox;

	@FXML
	private ComboBox<String> updateDiscountComboBox;

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
		if (!isSuccess) {
			deleteStartTimeLabel.setText("");
			deleteEndTimeLabel.setText("");
			deleteDiscountLabel.setText("");
			deleteConditionLabel.setText("");
			deleteSuperpositionLabel.setText("");

			AlertUtil.showErrorAlert("不存在该促销策略！");
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
				updateStartTimeDatePicker.setValue(strategy.getStartTime());
				updateEndTimeDatePicker.setValue(strategy.getEndTime());
				updateDiscountComboBox.setValue(strategy.getDiscount());
				updateConditionComboBox.setValue(strategy.getCondition());
				if (strategy.getSuperposition().equals("是")) {
					updateYesButton.setSelected(true);
				} else {
					updateNoButton.setSelected(true);
				}

				isSuccess = true;
			}
		}
		if (!isSuccess) {
			updateStartTimeField.setText("");
			updateEndTimeField.setText("");
			updateDiscountField.setText("");
			updateConditionField.setText("");
			updateYesButton.setSelected(false);
			updateNoButton.setSelected(false);

			AlertUtil.showErrorAlert("不存在该促销策略！");
		}
	}

	@FXML
	private void addStrategy() {

		String name = addNameField.getText();
		LocalDate startDate = addStartTimeDatePicker.getValue();
		LocalDate endDate = addEndTimeDatePicker.getValue();
		String condition = addConditionComboBox.getValue();
		String discount = addDiscountComboBox.getValue();
		String superposition = (addYesButton.isSelected()) ? "是" : "否";

		
		// 检查同名策略
		ObservableList<WebStrategyModel> list = strategyTable.getItems();
		for (WebStrategyModel model : list) {
			if (model.getName().equals(name)) {
				AlertUtil.showErrorAlert("已存在同名的促销策略！");
				return;
			}
		}
		
		//更新策略表格
		WebStrategyModel strategyModel = new WebStrategyModel(0, name, startDate.toString(), endDate.toString(), discount, condition,
				superposition);
		strategyTable.getItems().add(strategyModel);

		//更新底层数据
		WebStrategyVO vo = strategyModel.changeToVO();
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			ResultMessage message = helper.getStrategyBLService().webstrategy_make(vo);
			if (message==ResultMessage.Fail) {
				AlertUtil.showErrorAlert("添加促销策略失败！");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		AlertUtil.showInformationAlert("添加促销策略成功！");
	}

	@FXML
	private void updateStrategy() {
		if (updateNameField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未指定促销策略！");
			return;
		}
		String name = updateNameField.getText();
		String startTime = updateStartTimeField.getText();
		String endTime = updateEndTimeField.getText();
		String condition = updateConditionField.getText();
		String discount = updateDiscountField.getText();
		String superposition = (updateYesButton.isSelected()) ? "是" : "否";

		ObservableList<WebStrategyModel> list = strategyTable.getItems();

		// 拒绝更新一个不存在的策略
		if (!list.contains(currentStrategy)) {
			AlertUtil.showErrorAlert("不存在该促销策略！");
			return;
		}
		
		//更新表格
		int index;
		for(index = 0;index<list.size();index++){
			if (list.get(index).getName().equals(currentStrategy.getName())) {
				list.get(index).setName(name);
				list.get(index).setStartTime(startTime);
				list.get(index).setEndTime(endTime);
				list.get(index).setDiscount(discount);
				list.get(index).setCondition(condition);
				list.get(index).setSuperposition(superposition);
			}
		}
		
		//更新底层数据
		try {
			RemoteHelper helper = RemoteHelper.getInstance();
			WebStrategyVO vo = helper.getStrategyBLService().getwebstrategybyname(currentStrategy.getName());
			ResultMessage message = helper.getStrategyBLService().webstrategy_update(vo);
			if (message==ResultMessage.Fail) {
				AlertUtil.showErrorAlert("更新促销策略失败！");
				return;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		AlertUtil.showInformationAlert("更新促销策略成功！");
	}

	@FXML
	private void deleteStrategy() {
		if (deleteNameField.getText().isEmpty()) {
			AlertUtil.showWarningAlert("未指定促销策略！");
			return;
		}
		if (strategyTable.getItems().contains(currentStrategy)) {
			strategyTable.getItems().remove(currentStrategy);
			RemoteHelper helper = RemoteHelper.getInstance();
			try {
				ResultMessage message = helper.getStrategyBLService().webstrategy_delete(
						helper.getStrategyBLService().getwebstrategybyname(currentStrategy.getName()));
				if (message==ResultMessage.Fail) {
					AlertUtil.showErrorAlert("删除促销策略失败！");
				}
				else{
					AlertUtil.showInformationAlert("删除促销策略成功！");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			AlertUtil.showErrorAlert("不存在该促销策略！");
		}
	}

	public MarketStrategyController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main) {
		this.main = main;
		RemoteHelper helper = RemoteHelper.getInstance();

		//单选键的初始化
		final ToggleGroup addGroup = new ToggleGroup();
		addYesButton.setToggleGroup(addGroup);
		addNoButton.setToggleGroup(addGroup);
		final ToggleGroup updateGroup = new ToggleGroup();
		updateYesButton.setToggleGroup(updateGroup);
		updateNoButton.setToggleGroup(updateGroup);

		//单选框的初始化
		
		
		
		
		
		
		ObservableList<WebStrategyModel> webStrategyData = FXCollections.observableArrayList();
		try {
			ArrayList<WebStrategyVO> webdata = helper.getStrategyBLService().getWebStrategy();

			for (WebStrategyVO vo : webdata) {
				WebStrategyModel temp = new WebStrategyModel(vo.getid(), vo.getname(),
						format.format(vo.getstart_time()), format.format(vo.getend_time()), vo.getexecuteway(),
						vo.getcondition(), vo.getsuperposition() ? "是" : "否");
				webStrategyData.add(temp);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
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
