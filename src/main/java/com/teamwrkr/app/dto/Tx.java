package com.teamwrkr.app.dto;

import java.util.Date;

/**
 * This class represents a system transaction.
 * 
 * @author Ted E. Steiner
 * @date 5.31.2023
 *
 */
public class Tx {
	
	private int txid;
	private int twid;
	private int type;
	private Date createDate;
	
	public Tx() { }
	
	
	public Tx(Tx tx) {
		
		this.txid = tx.getTxid();
		this.twid = tx.getTwid();
		this.type = tx.getType();
		this.createDate = tx.getCreateDate();
		
	}
	
	
	public int getTxid() {
        return txid;
    }
    public void setTxid(int txid) {
        this.txid = txid;
    }

    
    public int getTwid() {
        return twid;
    }
    public void setTwid(int twid) {
        this.twid = twid;
    }
    
    
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
}
