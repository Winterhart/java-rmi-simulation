package shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Record;

public class RecordList extends ArrayList<Record> {
	//TODO: Replace this class by a more common way...
	private List<Record> records;
	public RecordList() {
		records = new ArrayList<Record>();
	}
	
	
}
