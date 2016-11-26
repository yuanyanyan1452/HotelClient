package ui.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.view.client.ClientBasicInfoController;
import ui.view.client.ClientBrowseHotelController;
import ui.view.client.ClientEnrollVIPController;
import ui.view.client.ClientEvaluateHotelController;
import ui.view.client.ClientOverviewController;
import ui.view.client.ClientSearchHotelController;
import ui.view.hotel.HotelCheckInController;
import ui.view.hotel.HotelDetailInfoController;
import ui.view.hotel.HotelOverviewController;
import ui.view.hotel.HotelRoomManageController;
import ui.view.hotel.HotelStrategyController;
import ui.view.manager.ManagerOverviewController;
import ui.view.market.CreditChargeController;
import ui.view.market.MarketOverviewController;
import ui.view.market.MarketStrategyController;
import ui.view.order.ClientBrowseOrderController;
import ui.view.order.ClientGenerateOrderController;
import ui.view.order.HotelBrowseOrderController;
import ui.view.order.HotelExecuteOrderController;
import ui.view.order.MarketBrowseAbnormalOrderController;
import ui.view.user.ClientLoginController;
import ui.view.user.ClientRegistController;
import ui.view.user.LoginOverviewController;

public class Main extends Application {
	private Stage stage;
	private Scene scene;
	private final double MINIMUM_WINDOW_WIDTH = 400.0;
	private final double MINIMUM_WINDOW_HEIGHT = 250.0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Hotel");
		stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
		stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
		stage.setResizable(false);
		initUI();
		stage.show();
	}

	// 初始界面
	public void initUI() {
		try {
			LoginOverviewController loginOverviewController = (LoginOverviewController) replaceSceneContent(
					"user/LoginOverview.fxml");
			loginOverviewController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到客户登录界面
	public void gotoClientLogin() {
		try {
			ClientLoginController loginController = (ClientLoginController) replaceSceneContent("user/Login.fxml");
			loginController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	// 跳转到酒店工作人员登录界面
	
	// 跳转到网站营销人员登录界面
	
	// 跳转到网站管理人员登录界面
	
	// 跳转到注册界面
	public void gotoRegist() {
		try {
			ClientRegistController registController = (ClientRegistController) replaceSceneContent("user/Regist.fxml");
			registController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 跳转到客户主界面
	public void gotoClientOverview() {
		try {
			ClientOverviewController controller = (ClientOverviewController) replaceSceneContent(
					"client/ClientOverview.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户查看基本信息
	public void gotoClientBasicInfo() {
		try {
			ClientBasicInfoController clientBasicInfoController = (ClientBasicInfoController) replaceSceneContent(
					"client/ClientBasicInfo.fxml");
			clientBasicInfoController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户浏览酒店
	public void gotoClientBrowseHotel() {
		try {
			ClientBrowseHotelController controller = (ClientBrowseHotelController) replaceSceneContent(
					"client/ClientBrowseHotel.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户浏览订单
		public void gotoClientBrowseOrder() {
			try {
				ClientBrowseOrderController controller = (ClientBrowseOrderController) replaceSceneContent(
						"order/ClientBrowseOrder.fxml");
				controller.setMain(this);
			} catch (Exception e) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		
	// 客户注册会员
	public void gotoClientEnrollVIP() {
		try {
			ClientEnrollVIPController controller = (ClientEnrollVIPController) replaceSceneContent(
					"client/ClientEnrollVIP.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户评价酒店
	public void gotoClientEvaluateHotel() {
		try {
			ClientEvaluateHotelController controller = (ClientEvaluateHotelController) replaceSceneContent(
					"client/ClientEvaluateHotel.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 客户搜索酒店
	public void gotoClientSearchHotel() {
		try {
			ClientSearchHotelController controller = (ClientSearchHotelController) replaceSceneContent(
					"client/ClientSearchHotel.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	//客户查看酒店详细信息
	public void gotoHotelDetailInfo() {
		try {
			ClientSearchHotelController controller = (ClientSearchHotelController) replaceSceneContent(
					"hotel/HotelDetailInfo.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	//客户生成订单
	public void gotoGenerateOrder() {
		try {
			ClientGenerateOrderController controller = (ClientGenerateOrderController) replaceSceneContent(
					"order/ClientGenerateOrder.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	//跳转到酒店主界面
	public void gotoHotelOverview() {
		try {
			HotelOverviewController HotelOverviewController = (HotelOverviewController) replaceSceneContent(
					"hotel/HotelOverview.fxml");
			HotelOverviewController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	// 酒店工作人员管理酒店信息
	public void gotoHotelBasicInfo() {
		try {
			HotelDetailInfoController controller = (HotelDetailInfoController) replaceSceneContent(
					"hotel/HotelBasicInfo.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	// 酒店可用房间管理
	public void gotoHotelRoomManage() {
		try {
			HotelRoomManageController controller = (HotelRoomManageController) replaceSceneContent(
					"hotel/HotelRoomManage.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	//酒店订单浏览
	public void gotoHotelBrowseOrder() {
		try {
			HotelBrowseOrderController controller = (HotelBrowseOrderController) replaceSceneContent(
					"order/HotelBrowseOrder.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	//酒店订单执行
	public void gotoHotelExecuteOrder() {
		try {
			HotelExecuteOrderController controller = (HotelExecuteOrderController) replaceSceneContent(
					"order/HotelExecuteOrder.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
	//酒店房间信息更新
	public void gotoHotelCheckIn() {
		try {
			HotelCheckInController controller = (HotelCheckInController) replaceSceneContent(
					"hotel/HotelCheckIn.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	//酒店销售策略管理
	public void gotoHotelStrategyManage() {
		try {
			HotelStrategyController controller = (HotelStrategyController) replaceSceneContent(
					"hotel/HotelStrategy.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	//跳转到网站营销人员主界面
	public void gotoMarketOverview() {
		try {
			MarketOverviewController MarketOverviewController = (MarketOverviewController) replaceSceneContent(
					"market/MarketOverview.fxml");
			MarketOverviewController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
	//网站销售策略管理
	public void gotoWebStrategyManage() {
		// TODO Auto-generated method stub
		try {
			MarketStrategyController MarketStrategyController = (MarketStrategyController) replaceSceneContent(
					"market/MarketStrategy.fxml");
			MarketStrategyController.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	//网站营销人员浏览异常订单
	public void gotoMarketAbnormalOrder() {
		// TODO Auto-generated method stub
		try {
			MarketBrowseAbnormalOrderController controller = (MarketBrowseAbnormalOrderController) replaceSceneContent(
					"order/MarketBrowseAbnormalOrder.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	//信用充值
	public void gotoMarketCreditCharge() {
		// TODO Auto-generated method stub
		try {
			CreditChargeController controller = (CreditChargeController) replaceSceneContent(
					"order/MarketBrowseAbnormalOrder.fxml");
			controller.setMain(this);
		} catch (Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	//跳转到网站管理人员主界面
		public void gotoManagerOverview() {
			try {
				ManagerOverviewController ManagerOverviewController = (ManagerOverviewController) replaceSceneContent(
						"manager/ManagerOverview.fxml");
				ManagerOverviewController.setMain(this);
			} catch (Exception e) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		

	// 界面跳转主要方法
	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(fxml));
		AnchorPane pane = (AnchorPane) loader.load();
		this.scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.centerOnScreen();
		stage.setResizable(false);
		return (Initializable) loader.getController();
	}

	public Stage getPrimaryStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
