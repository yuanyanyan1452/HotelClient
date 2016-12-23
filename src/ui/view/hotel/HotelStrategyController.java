package ui.view.hotel;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import rmi.RemoteHelper;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import objects.ResultMessage;
import ui.model.HotelStrategyModel;
import ui.model.WebStrategyModel;
import ui.util.AlertUtil;
import ui.util.StrategyUtil;
import ui.view.Main;
import vo.HotelStrategyVO;
import vo.HotelVO;
import vo.WebStrategyVO;

public class HotelStrategyController implements Initializable {
	private Main main;
	private HotelVO currentHotel;
	private HotelStrategyModel currentStrategy;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@FXML
	private TableView<HotelStrategyModel> strategyTable;

	@FXML
	private TableColumn<HotelStrategyModel, String> nameColumn;

	@FXML
	private TableColumn<HotelStrategyModel, String> startColumn;

	@FXML
	private TableColumn<HotelStrategyModel, String> endColumn;

	@FXML
	private TableColumn<HotelStrategyModel, String> discountColumn;

	@FXML
	private TableColumn<HotelStrategyModel, String> conditionColumn;

	@FXML
	private TableColumn<HotelStrategyModel, String> superpositionColumn;

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
		ObservableList<HotelStrategyModel> list = strategyTable.getItems();
		for (HotelStrategyModel strategy : list) {
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

			AlertUtil.showWarningAlert("不存在该促销策略！");
		}
	}

	@FXML
	private void updateSearchStrategy() {
		String name = updateNameField.getText();
		boolean isSuccess = false;
		ObservableList<HotelStrategyModel> list = strategyTable.getItems();
		for (HotelStrategyModel strategy : list) {
			if (strategy.getName().equals(name)) {
				currentStrategy = strategy;
				updateStartTimeDatePicker.setValue(LocalDate.parse(strategy.getStartTime()));
				updateEndTimeDatePicker.setValue(LocalDate.parse(strategy.getEndTime()));
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
			updateStartTimeDatePicker.setValue(null);
			updateEndTimeDatePicker.setValue(null);
			updateDiscountComboBox.setValue(null);
			updateConditionComboBox.setValue(null);
			updateYesButton.setSelected(false);
			updateNoButton.setSelected(false);

			AlertUtil.showWarningAlert("不存在该促销策略！");
		}
	}

	@FXML
	private void addStrategy() {

		String name = addNameField.getText();
		LocalDate startDate = addStartTimeDatePicker.getValue();
		LocalDate endDate = addEndTimeDatePicker.getValue();
		String condition = addConditionComboBox.getValue();
		String discount = addDiscountComboBox.getValue();
		boolean superposition = addYesButton.isSelected();

		// 检查同名策略
		ObservableList<HotelStrategyModel> list = strategyTable.getItems();
		for (HotelStrategyModel model : list) {
			if (model.getName().equals(name)) {
				AlertUtil.showErrorAlert("已存在同名的促销策略！");
				return;
			}
		}

		// 更新表格
		HotelStrategyModel strategyModel = new HotelStrategyModel(0, currentHotel.getid(), name, startDate.toString(),
				endDate.toString(), discount, condition, superposition);
		strategyTable.getItems().add(strategyModel);

		// 更新底层数据

		try {
			RemoteHelper helper = RemoteHelper.getInstance();
			HotelStrategyVO vo = new HotelStrategyVO();
			vo.setname(name);
			vo.sethotelid(currentHotel.getid());
			vo.setcondition(condition);
			vo.setstart_time(format.parse(strategyModel.getStartTime()));
			vo.setend_time(format.parse(strategyModel.getEndTime()));
			vo.setexecuteway(discount);
			vo.setsuperposition(superposition);
			ResultMessage message = helper.getStrategyBLService().hotelstrategy_make(vo);
			if (message == ResultMessage.Fail) {
				AlertUtil.showErrorAlert("添加促销策略失败！");
				return;
			}
		} catch (ParseException | RemoteException e1) {
			e1.printStackTrace();
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
		LocalDate startTime = updateStartTimeDatePicker.getValue();
		LocalDate endTime = updateEndTimeDatePicker.getValue();
		String condition = updateConditionComboBox.getValue();
		String discount = updateDiscountComboBox.getValue();
		String superposition = (updateYesButton.isSelected()) ? "是" : "否";

		ObservableList<HotelStrategyModel> list = strategyTable.getItems();

		// 拒绝更新一个不存在的策略
		if (!list.contains(currentStrategy)) {
			AlertUtil.showErrorAlert("不存在该促销策略！");
			return;
		}

		// 更新表格
		int index;
		for (index = 0; index < list.size(); index++) {
			if (list.get(index).getName().equals(currentStrategy.getName())) {
				list.get(index).setName(name);
				list.get(index).setStartTime(startTime.toString());
				list.get(index).setEndTime(endTime.toString());
				list.get(index).setCondition(condition);
				list.get(index).setDiscount(discount);
				list.get(index).setCondition(condition);
				list.get(index).setSuperposition(superposition);
			}
		}

		// 更新底层数据
		try {
			RemoteHelper helper = RemoteHelper.getInstance();
			HotelStrategyVO vo = helper.getStrategyBLService().gethotelstrategybyname(currentStrategy.getName());
			vo.setname(name);
			vo.setcondition(condition);
			vo.setexecuteway(discount);
			// localdate转date
			ZoneId zoneId = ZoneId.systemDefault();
			Instant instant = startTime.atStartOfDay().atZone(zoneId).toInstant();
			vo.setstart_time(Date.from(instant));
			instant = endTime.atStartOfDay().atZone(zoneId).toInstant();
			vo.setend_time(Date.from(instant));
			vo.setsuperposition(updateYesButton.isSelected());
			ResultMessage message = helper.getStrategyBLService().hotelstrategy_update(vo);
			if (message == ResultMessage.Fail) {
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
				ResultMessage message = helper.getStrategyBLService().hotelstrategy_delete(
						helper.getStrategyBLService().gethotelstrategybyname(currentStrategy.getName()));
				if (message == ResultMessage.Fail) {
					AlertUtil.showErrorAlert("删除促销策略失败！");
				} else {
					AlertUtil.showInformationAlert("删除促销策略成功！");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			AlertUtil.showErrorAlert("不存在该促销策略！");
		}
	}

	public HotelStrategyController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMain(Main main, HotelVO hotelVO) {
		this.main = main;
		currentHotel = hotelVO;

		// 单选键的初始化
		final ToggleGroup addGroup = new ToggleGroup();
		addYesButton.setToggleGroup(addGroup);
		addNoButton.setToggleGroup(addGroup);
		final ToggleGroup updateGroup = new ToggleGroup();
		updateYesButton.setToggleGroup(updateGroup);
		updateNoButton.setToggleGroup(updateGroup);

		// 单选框的初始化
		addDiscountComboBox.getItems().addAll(StrategyUtil.getDiscounts());
		addConditionComboBox.getItems().addAll(StrategyUtil.getWebAllConditions());
		updateDiscountComboBox.getItems().addAll(StrategyUtil.getDiscounts());
		updateConditionComboBox.getItems().addAll(StrategyUtil.getWebAllConditions());

		// 结束日期不得早于开始日期
		addEndTimeDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {

			@Override
			public DateCell call(DatePicker param) {
				DateCell cell = new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(addStartTimeDatePicker.getValue())) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
				return cell;
			}
		});

		// 导入当前酒店的所有策略
		ObservableList<HotelStrategyModel> hotelStrategyData = FXCollections.observableArrayList();
		RemoteHelper helper = RemoteHelper.getInstance();
		try {
			ArrayList<HotelStrategyVO> vos = helper.getStrategyBLService().getHotelStrategy(currentHotel.getid());
			for (HotelStrategyVO strategyVO : vos) {
				HotelStrategyModel model = new HotelStrategyModel(strategyVO.getid(), strategyVO.gethotelid(),
						strategyVO.getname(), format.format(strategyVO.getstart_time()),
						format.format(strategyVO.getend_time()), strategyVO.getexecuteway(), strategyVO.getcondition(),
						strategyVO.getsuperposition());
				hotelStrategyData.add(model);
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

		strategyTable.setItems(hotelStrategyData);
	}
}
