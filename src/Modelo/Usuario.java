package Modelo;

import java.util.ArrayList;

public class Usuario {
    private int codigo;
    private String nombre;
    private String apellido;
    ArrayList<Libro> librosEnPrestamo;

        
    public Usuario(int codigo, String nombre, String apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;

        librosEnPrestamo = new ArrayList<>();
    }


    public int getCodigo() {
        return codigo;
    }

    public void prestar(Libro libro){
        libro.prestarA(this);
        librosEnPrestamo.add(libro);
    }


    public void devolver(Libro libro) {
        libro.devolver();
        librosEnPrestamo.remove(libro);
    }


    public boolean tieneLibrosPrestados() {
        return librosEnPrestamo.size() > 0;
    }


    public void listarLibrosEnPrestamo() {
        if (tieneLibrosPrestados()){
            librosEnPrestamo.sort((a, b) -> a.getFechaPrestamo().compareTo(b.getFechaPrestamo()));
            librosEnPrestamo.forEach(libro->libro.escribirDatos());
        }
        else{
            System.out.println("El usuario " + this.nombre + " no tiene libros prestados.");
        }
    }
    
}
