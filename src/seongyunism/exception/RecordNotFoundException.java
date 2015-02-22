package seongyunism.exception;

public class RecordNotFoundException extends Exception {

	public RecordNotFoundException (String message) {
		super (message);
	}

	public RecordNotFoundException () {
		this ("ERROR : 해당 레코드를 찾을 수 없습니다!");
	}

}
