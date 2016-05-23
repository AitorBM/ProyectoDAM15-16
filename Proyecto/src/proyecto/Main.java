/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AitorBM
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    private static List<Categoria> categorias = new ArrayList<>();
    private static List<Pregunta> preguntas = new ArrayList<>();
    private static List<Respuesta> respuestas = new ArrayList<>();
    
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        
        Categoria c = new Categoria();
        Pregunta p = new Pregunta();
        Respuesta r = new Respuesta();
        
        c.setId(1);
        c.setNombre("Categoria1");
        getCategorias().add(c);
        c.setId(2);
        c.setNombre("Categoria2");
        getCategorias().add(c);
        c.setId(3);
        c.setNombre("Categoria3");
        getCategorias().add(c);
        
        p.setId(1);
        p.setTexto("Pregunta1");
        getPreguntas().add(p);
        p.setId(2);
        p.setTexto("Pregunta2");
        getPreguntas().add(p);
        p.setId(3);
        p.setTexto("Pregunta3");
        getPreguntas().add(p);
        
        r.setId(1);
        r.setTexto("Respuesta1");
        r.setCorrecta(1);
        getRespuestas().add(r);
        r.setId(2);
        r.setTexto("Respuesta2");
        r.setCorrecta(0);
        getRespuestas().add(r);
        r.setId(3);
        r.setTexto("Respuesta3");
        r.setCorrecta(1);
        getRespuestas().add(r);
        
        VP ventanaPrincipal = new VP();
        ventanaPrincipal.setVisible(true);
    }

    /**
     * @return the categorias
     */
    public static List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @return the preguntas
     */
    public static List<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * @return the respuestas
     */
    public static List<Respuesta> getRespuestas() {
        return respuestas;
    }
}