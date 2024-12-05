package main;

import clases.Contrato;
import clases.Empleado;
import clases.Producto;
import dao.EmpleadoDAO;
import dao.ProductoDAO;

import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean salir = true;
        while (salir) {
            System.out.println("1. Crear empleado");
            System.out.println("2. Crear producto");
            System.out.println("3. Leer empleado por id");
            System.out.println("4. Leer producto por referencia");
            System.out.println("5. Actualizar empleado");
            System.out.println("6. Actualizar producto");
            System.out.println("7. Eliminar empleado");
            System.out.println("8. Eliminar producto");
            System.out.println("9. Buscar producto superior a una cantidad");
            System.out.println("10. Buscar productos por rango de precios");
            System.out.println("0. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    crearProducto();
                    break;
                case 3:
                    leerEmpleado();
                    break;
                case 4:
                    leerProducto();
                    break;
                case 5:
                    actualizarEmpleado();
                    break;
                case 6:
                    actualizarProducto();
                    break;
                case 7:
                    eliminarEmpleado();
                    break;
                case 8:
                    eliminarProducto();
                    break;
                case 9:
                    buscarProdSuperior();
                    break;
                case 10:
                    buscarProductoPorRango();
                    break;
                case 0:
                    salir = false;
                    break;
            }
        }
    }

    /**
     * Método que crea un empleado pidiendo su id, nombre, apellido, id de contrato y tipo de contrato
     */
    public static void crearEmpleado(){
        System.out.println("Introduce id");
        int id = pedirInt();
        System.out.println("Introduce nombre");
        String nombre = pedirString();
        System.out.println("Introduce apellido");
        String apellido = pedirString();
        System.out.println("Introduce un id de contrato");
        int idContrato = pedirInt();
        System.out.println("Introduce tipo de contrato");
        String tipo = pedirString();
        Empleado emp = new Empleado(id, nombre, apellido, new Contrato(idContrato,tipo));
        try {
            EmpleadoDAO.create(emp);
        }catch(RollbackException e){
            System.out.println("Ese empleado ya existe");
        }
    }

    /**
     * Método para crear un producto mediante referencia, nombre y precio
     */
    public static void crearProducto(){
        System.out.println("Introduce referencia");
        String ref = pedirString();
        System.out.println("Introduce nombre");
        String nombre = pedirString();
        System.out.println("Introduce precio");
        double cantidad = pedirDouble();
        Producto prod = new Producto(ref, nombre, cantidad);
        try {
            ProductoDAO.create(prod);
        }catch(RollbackException e){
            System.out.println("Ese producto ya existe");
        }
    }

    /**
     * Método para leer un empleado mediante su id
     */
    public static void leerEmpleado(){
        System.out.println("Introduce id");
        int id = pedirInt();
        try {
            Empleado emp = EmpleadoDAO.read(id);
            System.out.println(emp.toString());
        }catch(RollbackException e){
            System.out.println("No existe empleado con ese id");
        }catch (NullPointerException e){
            System.out.println("No existe empleado con ese id");
        }
    }

    /**
     * Método para leer un producto mediante su referencia
     */
    public static void leerProducto(){
        System.out.println("Introduce referencia");
        String ref = pedirString();
        try {
            Producto prod = ProductoDAO.read(ref);
            System.out.println(prod.toString());
        }catch(RollbackException e){
            System.out.println("No existe producto con esa referencia");
        }catch (NullPointerException e) {
            System.out.println("No existe producto con esa referencia");
        }
    }

    /**
     * Método para cambiar datos de un empleado
     */
    public static void actualizarEmpleado(){
        System.out.println("Introduce id del empleado a actualizar:");
        int id = pedirInt();
        System.out.println("Introduce nuevo nombre");
        String nombre = pedirString();
        System.out.println("Introduce nuevo apellido");
        String apellido = pedirString();
        try {
            EmpleadoDAO.update(id,nombre,apellido);
        }catch(RollbackException e){
            System.out.println("No se ha podido actualizar ese empleado");
        }
    }

    /**
     * Método para cambiar datos de un producto
     */
    public static void actualizarProducto(){
        System.out.println("Introduce referencia del producto a actualizar:");
        String ref = pedirString();
        System.out.println("Introduce nuevo nombre");
        String nombre = pedirString();
        System.out.println("Introduce nuevo precio");
        double precio = pedirDouble();
        try {
            ProductoDAO.update(ref,nombre,precio);
        }catch(RollbackException e){
            System.out.println("No se ha podido actualizar ese empleado");
        }
    }

    /**
     * Método para eliminar un empleado mediante su id
     */
    public static void eliminarEmpleado(){
        System.out.println("Introduce id del empleado a eliminar:");
        int id = pedirInt();
        try {
            EmpleadoDAO.delete(id);
        }catch(RollbackException e){
            System.out.println("No se ha podido eliminar ese empleado");
        }
    }

    /**
     * Método para eliminar un prodcuto mediante su referencia
     */
    public static void eliminarProducto(){
        System.out.println("Introduce la referencia del producto a eliminar:");
        String ref = pedirString();
        try {
            ProductoDAO.delete(ref);
        }catch(RollbackException e){
            System.out.println("No se ha podido eliminar ese empleado");
        }
    }

    /**
     * Método para buscar productos más caros que el precio introducido
     */
    public static void buscarProdSuperior(){
        System.out.println("Introduce un precio para buscar productos mas caros ");
        double precio = pedirDouble();
        try {
            ArrayList<Producto> productos = (ArrayList<Producto>) ProductoDAO.buscarPorPrecio(precio);
            for (Producto p : productos){
                System.out.println(p.toString());
            }
        }catch(RollbackException e){
            System.out.println("No se ha podido buscar");
        }
    }

    /**
     * Método para buscar productos dentro de un rango de precios
     */
    public static void buscarProductoPorRango(){
        System.out.println("Introduce un precio minimo por el que buscar productos");
        double precioMin = pedirDouble();
        System.out.println("Introduce un precio maximo por el que buscar productos");
        double precioMax = pedirDouble();
        try {
            ArrayList<Producto> productos = (ArrayList<Producto>) ProductoDAO.buscarPorRango(precioMin,precioMax);
            for (Producto p : productos){
                System.out.println(p.toString());
            }
        }catch(RollbackException e){
            System.out.println("No se ha podiddo buscar");
        }
    }


    /**
     * Solicita un número entero al usuario mediante la consola.
     * El método utiliza un bucle para garantizar que el usuario introduzca un valor correcto.
     * En caso de que el usuario ingrese un valor no válido, se solicita nuevamente.
     * @return Un número entero proporcionado por el usuario.
     */
    public static int pedirInt(){
        boolean salir = true;
        int devolver = 0;
        while (salir){
            try {
                Scanner sc = new Scanner(System.in);
                devolver = sc.nextInt();
                salir = false;
            }catch (Exception e){
                System.out.println("Introduzca un valor correcto");
            }
        }
        return devolver;
    }

    /**
     * Solicita un número decimal al usuario mediante la consola.
     * El método utiliza un bucle para garantizar que el usuario introduzca un valor correcto.
     * En caso de que el usuario ingrese un valor no válido, se solicita nuevamente.
     * @return Un número decimal proporcionado por el usuario.
     */
    public static double pedirDouble(){
        boolean salir = true;
        double devolver = 0;
        while (salir){
            try {
                Scanner sc = new Scanner(System.in);
                devolver = sc.nextDouble();
                salir = false;
            }catch (Exception e){
                System.out.println("Introduzca un valor correcto");
            }
        }
        return devolver;
    }

    /**
     * Solicita una cadena de caracteres al usuario mediante la consola.
     * El método utiliza un bucle para garantizar que el usuario introduzca un valor correcto.
     * En caso de que el usuario ingrese un valor no válido, se solicita nuevamente.
     * @return Una cadena de caracteres proporcionado por el usuario.
     */
    public static String pedirString(){
        boolean salir = true;
        String devolver = "";
        while (salir){
            try {
                Scanner sc = new Scanner(System.in);
                devolver = sc.nextLine();
                salir = false;
            }catch (Exception e){
                System.out.println("Introduzca un valor correcto");
            }
        }
        return devolver;
    }
}

