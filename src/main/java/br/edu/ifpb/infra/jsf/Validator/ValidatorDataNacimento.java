package br.edu.ifpb.infra.jsf.Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDate;

@FacesValidator(value = "validator.dataNascimento")
public class ValidatorDataNacimento implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        LocalDate data = (LocalDate) value;
        if(data == null){
            throw new ValidatorException(new FacesMessage("Tá errada essa data aí, boy!"));
        }
        if(data.isEqual(LocalDate.now()) || data.isAfter(LocalDate.now())){
            throw new ValidatorException(new FacesMessage("Future boys not allowed here!"));
        }
    }
}
