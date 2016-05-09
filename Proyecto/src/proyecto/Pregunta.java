/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AitorBM
 */
public class Pregunta {
    private int id;
    private String texto;
    
    private List<Respuesta> respuestas = new ArrayList<>();
    private Categoria categoria;
}