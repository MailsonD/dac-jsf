package br.edu.ifpb.infra.jsf.Converter;


import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.interfaces.Dependentes;
import br.edu.ifpb.infra.memory.DependentesEmMemoria;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "convert.dependente")
public class ConvertDep implements Converter {

//    private Dependentes dependentes = DependentesEmMemoria.getInstance();

    private Dependentes dependentes = CDI.current().select(Dependentes.class).get();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return dependentes.localizarDependenteComId(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Dependente dep = (Dependente) value;
        return dep.getUuid();
    }
}
