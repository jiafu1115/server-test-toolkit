package com.googlecode.test.toolkit.example.database.toJavaBean;

import java.sql.Timestamp;

/**
 * <pre>
 *   "EVENTTYPE"	"VARCHAR2"
 *   "TIMESTAMP"	"DATE"
 *   "ID"	"NUMBER"
 *
 * </pre>
 *
 * @author jiafu
 *
 */
public class TABLENAME {

	private String EVENTTYPE;
	private Timestamp TIMESTAMP;
	private long ID;

	public TABLENAME() {
		super();
	}

	public String getEVENTTYPE() {
		return EVENTTYPE;
	}

	public void setEVENTTYPE(String eVENTTYPE) {
		EVENTTYPE = eVENTTYPE;
	}

	public Timestamp getTIMESTAMP() {
		return TIMESTAMP;
	}

	public void setTIMESTAMP(Timestamp tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TABLENAME [EVENTTYPE=");
		builder.append(EVENTTYPE);
		builder.append(", TIMESTAMP=");
		builder.append(TIMESTAMP);
		builder.append(", ID=");
		builder.append(ID);
		builder.append("]");
		return builder.toString();
	}

}
