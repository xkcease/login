package po;

public class DataJson {
	private boolean state;
	private int errorIndex;
	private String errorText;
	
	public DataJson() {
		this.state = false;
		this.errorIndex = -1;
		this.errorText = "";
	}
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getErrorIndex() {
		return errorIndex;
	}
	public void setErrorIndex(int errorIndex) {
		this.errorIndex = errorIndex;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	@Override
	public String toString() {
		return "DataJson [state=" + state + ", errorIndex=" + errorIndex + ", errorText=" + errorText + "]";
	}
	
}
