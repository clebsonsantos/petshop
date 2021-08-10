package Architecture;

import org.springframework.validation.BindingResult;

public class ControllerValidator extends GenericValidator {
	
	public boolean validarCadastro(Object obj, BindingResult result) {
		this.messages.clear();
		regrasCadastro(obj);
		return controllerPopulateResult(result);
	}
	
	public boolean validarAlteracao(Object obj, BindingResult result) {
		this.messages.clear();
		regrasAlterar(obj);
		return controllerPopulateResult(result);
	}
	
	public boolean validarRemocao(Object obj, BindingResult result) {
		this.messages.clear();
		regrasRemover(obj);
		return controllerPopulateResult(result);
	}
	
	public void regrasCadastro(Object obj) {
		
	}
	
	public void regrasAlterar(Object obj) {
		
	}
	
	public void regrasRemover(Object obj) {
		
	}
	
	private boolean controllerPopulateResult(BindingResult result) {
		if (!messages.isEmpty()) {
			populateResult(result);
			return false;
		}
		else 
			return true;
	}

}
