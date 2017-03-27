package com.allscore.wireless.biz;


public interface FundPoolManager {
	
	
	void updateFundPoolByBusinessTime(String businessTime);
	
	int addShopBusinessByBusinessTime(String businessTime);

	
}
