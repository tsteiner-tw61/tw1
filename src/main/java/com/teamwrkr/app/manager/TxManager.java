package com.teamwrkr.app.manager;

import java.util.Date;

import com.teamwrkr.app.dto.Tx;
import com.teamwrkr.app.service.DataService;

public class TxManager {
	
	public TxManager() { }
	
	
	public static int getLoginCount(int twid) {
		
		int logins = 0;
		
		Tx tx0 = new Tx();
		String qs = "twid = " + twid;
		Object[] txns = DataService.selectMany(tx0, qs);
		for (Object txobj : txns) {
			Tx tx = (Tx) txobj;
			if (tx.getType() == 1) {
				Date logDate = tx.getCreateDate();
				int yr = logDate.getYear();
				int mo = logDate.getMonth();
				int dt = logDate.getDate();
				
				
			}
			
		}
		
		return logins;
		
		
	}
	

}
