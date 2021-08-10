package Architecture;

import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.BindingResult;

public class GenericValidator {
	
	public List<ValidatorMessage> messages;
	protected Object obj;
	
	public boolean isNull(Object obj) {
		if (obj == null)
			return true;
		if ((obj.getClass().isInstance(String.class) || 
				(obj.getClass().isPrimitive() && obj.getClass().isInstance(Character.class))) 
				&& obj == "")
			return true;
		
		return false;	
	}
	
	public boolean isDateTodayOrFuture(LocalDate date) {
		LocalDate objDate = LocalDate.now();
		
		if (!isNull(date)) 
			if (date.isEqual(objDate) || date.isAfter(objDate))
				return true;
		
		return false;
				
	}
	
	public void addMessage(String attribute, String message) {
		this.messages.add(new ValidatorMessage(attribute, 
				ValidatorControllerUtils.getObjectControllerName(obj).concat("."+attribute), message ));
	}
	
	public void populateResult(BindingResult result) {
		for (ValidatorMessage validatorMessage : messages) {
			result.rejectValue(validatorMessage.attribute, validatorMessage.field, validatorMessage.message);
		}
		
	}

	public List<ValidatorMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ValidatorMessage> messages) {
		this.messages = messages;
	}

}

class ValidatorControllerUtils {
	
	public static String getObjectControllerName(Object obj) {
		if (obj != null) {
			String className = obj.getClass().getSimpleName();
			String controllerName = className.substring(0, 1).toLowerCase() + className.substring(1);
			return controllerName;
		}
		
		return "";
	}
	
}

