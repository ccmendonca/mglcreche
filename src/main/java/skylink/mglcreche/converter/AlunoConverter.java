/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skylink.mglcreche.converter;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import java.util.HashMap;
import java.util.Map;
import skylink.mglcreche.modelo.Aluno;


@FacesConverter(value = "alunoConverter")
public class AlunoConverter implements Converter{
      private static Map<String, Aluno> mapa = new HashMap<>();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
      try {
            return mapa.get(value);
        } catch (NumberFormatException e) {
            throw new ConverterException(
                    new FacesMessage("Invalid ID"), e);
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
       if(value instanceof Aluno) {
            Aluno aluno = (Aluno) value;
            mapa.put(String.valueOf(aluno.getIdAluno()), aluno);
            return String.valueOf(aluno.getIdAluno());
        } else {
            return "";
        }
    }
    
}
