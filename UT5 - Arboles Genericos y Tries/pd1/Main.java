
package ut5_pd1;

/**
 *
 * @author 
 */
public class Main {
    
    public static void main(String[] args) {
        
        TArbolG instance = new TArbolG();
        
        //Ejercicio 5
        instance.insertar("RECTORIA", "");
        instance.insertar("VICERRECTORÍA DEL MEDIO UNIVERSITARIO", "RECTORIA");
        instance.insertar("VICERRECTORÍA ACADEMICA", "RECTORIA");
        instance.insertar("VICERRECTORÍA ADMINISTRATIVA", "RECTORIA");
        instance.insertar("FACULTAD DE CIENCIAS EMPRESARIALES", "VICERRECTORÍA ACADEMICA");
        instance.insertar("FACULTAD DE CIENCIAS HUMANAS", "VICERRECTORÍA ACADEMICA");
        instance.insertar("FACULTAD DE INGENIERÍA Y TECNOLOGÍAS", "VICERRECTORÍA ACADEMICA");
        instance.insertar("FACULTAD DE PSICOLOGÍA", "VICERRECTORÍA ACADEMICA");
        instance.insertar("DEPARTAMENTO DE INFORMÁTICA Y CIENCIAS DE LA COMPUTACIÓN", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS");
        instance.insertar("DEPARTAMENTO DE INGENIERÍA ELÉCTRICA", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS");
        instance.insertar("DEPARTAMENTO DE MATEMÁTICAS", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS");
        
        //Ejercicio 6
        System.out.println("La etiqueta del nodo resultado de buscar 'RECTORIA' es: " + instance.buscar("RECTORIA").getEtiqueta());
        System.out.println("La etiqueta del nodo resultado de buscar 'FACULTAD DE INGENIERÍA Y TECNOLOGÍAS' es: " + instance.buscar("FACULTAD DE INGENIERÍA Y TECNOLOGÍAS").getEtiqueta());
        System.out.println("El nodo resultado de buscar 'HOLA' es: " + instance.buscar("HOLA"));
        
        //Ejercicio 7
        System.out.println("El listado indentado del arbol escenario es: " + instance.listarIndentado());
        
    }
    
}
