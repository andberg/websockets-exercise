package se.berglund.datastorage;

public class CurrentWhiteboardManager {
	
	private String currentWhiteboardName; 
	
	public CurrentWhiteboardManager(){}
	
	public CurrentWhiteboardManager(String name){
		this.currentWhiteboardName = name; 
	}

	public String getCurrentWhiteboardName() {
		return currentWhiteboardName;
	}

	public void setCurrentWhiteboardName(String currentWhiteboardName) {
		this.currentWhiteboardName = currentWhiteboardName;
	}
}
