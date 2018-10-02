package model;

public class Record {
	
	private String recordID;
	
	public Record(String recordId) {
		this.recordID = recordId;
	}
	public String getRecordID() {
		return recordID;
	}

	public void setRecordID(String recordId) {

		this.recordID = recordId;
	}
	
	public String toString() {
		return recordID;
	}
	
	
	

}
