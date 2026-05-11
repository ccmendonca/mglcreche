package skylink.mglcreche.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import skylink.mglcreche.dao.SexoDAO;
import skylink.mglcreche.modelo.Sexo;

@FacesConverter(value = "sexoConverter")
public class SexoConverter implements Converter {

    private SexoDAO sexoDAO = new SexoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return sexoDAO.findById(id);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter ID: " + value);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Sexo)) {
            return "";
        }
        Sexo sexo = (Sexo) value;
        return String.valueOf(sexo.getIdSexo());
    }
}