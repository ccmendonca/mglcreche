package skylink.mglcreche.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.sql.SQLException;
import skylink.mglcreche.dao.SexoDAO;
import skylink.mglcreche.modelo.Sexo;

/**
 * @author Henriques
 */
@FacesConverter(value = "sexoConverter")
public class SexoConverter implements Converter {

    private SexoDAO sexoDAO = new SexoDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent compnent, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return sexoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            Sexo sexo = (Sexo) value;
            return String.valueOf(sexo.getIdSexo());
        }
        return null;
    

    }
}

