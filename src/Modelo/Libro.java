package Modelo;

import java.util.Calendar;
import java.util.Date;

public class Libro {
    private String titulo;
    private String categoria;
    private Usuario usuario;
    private Date fechaPrestamo;
    
    public Libro(String titulo, String categoria) {
        this.titulo = titulo;
        this.categoria = categoria;
        usuario = null;
        fechaPrestamo = null;
    }

    public String getCategoria() {
        return categoria;
    }
    

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void prestarA(Usuario usuario) {
        this.usuario = usuario;
        Calendar calendar = Calendar.getInstance();
        this.fechaPrestamo = calendar.getTime();
    }

    public void devolver() {
        this.usuario = null;
        this.fechaPrestamo = null;
    }



    public String getTitulo() {
        return titulo;
    }



    public boolean prestadoA(Usuario usuario) {
        return this.usuario == usuario;
    }



    public boolean estaPrestado() {
        return this.usuario != null;
    }

    public void escribirDatos() {
        System.out.println("Titulo: " + this.titulo);
        System.out.println("Categoria: " + this.categoria);
    } 
}
