package ui.util;

/*
 * 抽象信用记录动作名称的字面值常量
 */
public class RecordActionUtil {
	private static final String EXECUTE = "订单执行";
	private static final String ABNORMAL = "订单异常";
	private static final String CHARGE = "信用充值";
	private static final String CLIENT_CANCEL = "订单客户撤销";
	private static final String MARKET_CANCEL = "订单网站营销人员撤销";
	
	public static String getExecute() {
		return EXECUTE;
	}
	public static String getAbnormal() {
		return ABNORMAL;
	}
	public static String getCharge() {
		return CHARGE;
	}
	public static String getClientCancel() {
		return CLIENT_CANCEL;
	}
	public static String getMarketCancel() {
		return MARKET_CANCEL;
	}
	
	
}
