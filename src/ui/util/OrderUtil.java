package ui.util;

/*
 * 订单状态和执行情况辅助类
 */
public class OrderUtil {

	private static final String ISEXECUTE = "是";
	private static final String NOEXECUTE = "否";
	private static final String NORMAL = "正常";
	private static final String ABNORMAL = "异常";
	private static final String CANCEL = "撤销";
	
	public static String getIsexecute() {
		return ISEXECUTE;
	}
	public static String getNoexecute() {
		return NOEXECUTE;
	}
	public static String getNormal() {
		return NORMAL;
	}
	public static String getAbnormal() {
		return ABNORMAL;
	}
	public static String getCancel() {
		return CANCEL;
	}
	
}
