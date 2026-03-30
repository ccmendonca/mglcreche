/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skylink.mglcreche.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import skylink.mglcreche.dao.AlunoDAO;
import skylink.mglcreche.modelo.Aluno;


@FacesConverter(value = "anoLectivoConverter")
public class AlunoConverter implements Converter{
    AlunoDAO alunoDAO =new AlunoDAO();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer id = Integer.parseInt(value);
        try {
            return alunoDAO.findById(id);
        } catch (Exception ex) {
            System.err.println("Erro na conversão: " + ex.getMessage());
        }
        return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      
      if (value != null) {
             Aluno  aluno = ( Aluno) value;
            return String.valueOf(aluno.getIdAluno());
        }
        return null;
    }
}
