/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skylink.mglcreche.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import skylink.mglcreche.dao.AnoLectivoDAO;
import skylink.mglcreche.dao.GrauParentescoDAO;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.GrauParentesco;


@FacesConverter(value = "anoLectivoConverter")
public class AnoLectivoConverter implements Converter{
    AnoLectivoDAO anoLectivoDAO =new AnoLectivoDAO();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return anoLectivoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
            AnoLectivo anoLectivo = (AnoLectivo) value;
            return String.valueOf(anoLectivo.getIdAnoLectivo());
        }
        return null;
    }
}
