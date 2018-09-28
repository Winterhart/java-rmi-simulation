package model;

public class Record {
	
	private String recordID;
	
	public Record(String recordId) {
		///TODO: Enforce Rule over RecordID
		this.recordID = recordId;
	}
	public String getRecordID() {
		return recordID;
	}

	private void setRecordID(String recordId) {

		this.recordID = recordId;
	}
	
	
	

}
