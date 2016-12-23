package ui.util;

/**
 * 抽象策略相关的字面值常量
 * @author Ferriswheel
 *
 */
public class StrategyUtil {

	private static final String[] HOTEL_ALL_CONDITIONS = {
			"开业酬宾",
			"生日特惠折扣",
			"三间及以上预定折扣",
			"双十一活动折扣"
	};
	private static final String[] WEB_ALL_CONDITIONS={
			"开业酬宾",
			 "双十一活动折扣",
			 "一级会员折扣",
			 "二级会员折扣",
			 "三级会员折扣",
			 "新街口专属折扣",
			 "仙林中心专属折扣",
			 "学则路专属折扣",
			 "玄武区专属折扣",
	};
	private static final String[] DISCOUNTS={
			"一折",
			"二折",
			"三折",
			"四折",
			"五折",
			"六折",
			"七折",
			"八折",
			"九折"
	};
	public static final String[] getHotelAllConditions(){
		return HOTEL_ALL_CONDITIONS;
	}
	
	public static final String[] getWebAllConditions(){
		return WEB_ALL_CONDITIONS;
	}
	
	public static final String[] getDiscounts(){
		return DISCOUNTS;
	}
}
