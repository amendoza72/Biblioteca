package Modelo;

import java.util.ArrayList;

import Herramientas.Utilidades;

public class Biblioteca {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Libro> libros = new ArrayList<>();


    private Usuario buscarUsuario(int codigoUsuario){
        for (Usuario it : usuarios){
            if (it.getCodigo() == codigoUsuario){
                return it;
            }
        }
        return null;
    }
    

    public void altaUsuario(){
        System.out.println("****************************");
        System.out.println("****** Alta Usuario   ******");
        System.out.println("****************************");
        int codigoUsuario = Utilidades.leerEntero("código usuario");

        Usuario usuario = buscarUsuario(codigoUsuario);

        if (usuario != null){
            System.out.println("Ese código ya existe.");
        }
        else{
            String nombre = Utilidades.leerString("nombre usuario: ");
            String apellido = Utilidades.leerString("apellido usuario: ");
            usuarios.add(new Usuario(codigoUsuario, nombre, apellido));
        }
    }


    public void bajaUsuario(){
        System.out.println("****************************");
        System.out.println("****** Baja Usuario   ******");
        System.out.println("****************************");
        int codigoUsuario = Utilidades.leerEntero("código usuario");

        Usuario usuario = buscarUsuario(codigoUsuario);

        if (usuario == null){
            System.out.println("Ese código no existe.");
        }
        else{
            if (usuario.tieneLibrosPrestados()){
                System.out.println("Ese usuario tiene libros prestados.");
            }
            else{
                usuarios.remove(usuario);
            }
        }
    }

    private Libro buscarLibro(String tituloLibro){
        for (Libro it : libros){
            if (it.getTitulo().equals(tituloLibro)){
                return it;
            }
        }
        return null;
    }
    

    public void altaLibro(){
        System.out.println("****************************");
        System.out.println("****** Alta Libro   ******");
        System.out.println("****************************");
        String tituloLibro = Utilidades.leerString("título libro");

        Libro libro = buscarLibro(tituloLibro);

        if (libro != null){
            System.out.println("Ese título ya existe.");
        }
        else{
            String categoria = Utilidades.leerString("categoria del libro: ");
            libros.add(new Libro(tituloLibro, categoria));
        }
    }


    public void bajaLibro(){
        System.out.println("****************************");
        System.out.println("****** Baja  Libro   ******");
        System.out.println("****************************");
        String tituloLibro = Utilidades.leerString("título libro");

        Libro libro = buscarLibro(tituloLibro);

        if (libro == null){
            System.out.println("Ese título no existe.");
        }
        else{
            if (libro.estaPrestado()){
                System.out.println("Ese libro está prestado.");
            }
            else{
                libros.remove(libro);
            }
        }
    }

    public void gestionPrestamoLibro(){
        boolean salir = false;
        while (!salir){
            salir = mostrarMenuGestionPrestamos();
        }
    }

    private boolean mostrarMenuGestionPrestamos(){
        boolean salir = false;
        System.out.println("************************************");
        System.out.println("****** Menu gestión prestamos ******");
        System.out.println("************************************");
        System.out.println("1.- Prestar libro");
        System.out.println("2.- Devolver libro");

        System.out.println("9.- Salir");

        
        String opcion = Utilidades.leerString("Opción: ");

        switch (opcion) {
            case "1":
                prestarLibro();
                break;
            case "2":
                devolverLibro();
                break;
            case "9":
                salir = true;
                break;
            default:
                break;
        }
        return salir;
    }

    private void prestarLibro(){
        int codigoUsuario = Utilidades.leerEntero("código usuario: ");
        Usuario usuario = buscarUsuario(codigoUsuario);

        if (usuario != null){
            String titulo = Utilidades.leerString("título del libro: ");
            Libro libro = buscarLibro(titulo);
            if (libro != null){
                //libro.prestarA(usuario);
                usuario.prestar(libro);
            }
            else{
                System.out.println("El libro no existe");
            }
        }
        else{
            System.out.println("El usuario no existe");
        }
    }

    private void devolverLibro(){
        int codigoUsuario = Utilidades.leerEntero("código usuario: ");
        Usuario usuario = buscarUsuario(codigoUsuario);

        if (usuario != null){
            String titulo = Utilidades.leerString("título del libro: ");
            Libro libro = buscarLibro(titulo);
            if (libro != null){
                if (libro.prestadoA(usuario)){
                    usuario.devolver(libro);
                }
                else{
                    System.out.println("El usuario no tiene ese libro prestado.");
                }
            }
            else{
                System.out.println("El libro no existe");
            }
        }
        else{
            System.out.println("El usuario no existe");
        }
    }

    private ArrayList<String> listarCategorias(ArrayList<Libro> libros){
        ArrayList<String> categorias = new ArrayList<>();

        for (Libro it: libros) {
            if (!categorias.contains(it.getCategoria())){
                categorias.add(it.getCategoria());
            }
        }

        return categorias;
    }

    private ArrayList<Libro> darLibrosDisponibles(){
        ArrayList<Libro> librosDisponibles = new ArrayList<>();

        for (Libro libro : libros) {
         if(!libro.estaPrestado()){
             librosDisponibles.add(libro);
         }
        }
        return librosDisponibles;
    }


    public void listarLibrosDisponibles() {
        ArrayList<Libro> librosDisponibles = darLibrosDisponibles();
       ArrayList<String> categoriasLibrosDisponibles = listarCategorias(librosDisponibles);

       for (String categoria : categoriasLibrosDisponibles) {
            System.out.println("Categoria listada: " + categoria);
            for (Libro libro : librosDisponibles) {
                if (libro.getCategoria().equals(categoria)){
                    libro.escribirDatos();
                }
            }
       }

    }


    public void listarLibrosPrestados() {
        int codigoUsuario = Utilidades.leerEntero("codigo usuario");
        Usuario usuario = buscarUsuario(codigoUsuario);
        if (usuario == null){
            System.out.println("Código de usuario inexistente.");
        }
        else{
            usuario.listarLibrosEnPrestamo();
        }
    }
}
