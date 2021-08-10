package Architecture;

public class ValidatorMessage {
		
	String attribute;
	String field;
	String message;
	
	public ValidatorMessage(String attribute, String field, String message) {
		this.attribute = attribute;
		this.field = field;
		this.message = message;
	}
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}