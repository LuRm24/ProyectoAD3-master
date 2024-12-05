package dao;

import clases.Empleado;
import clases.Producto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductoDAO {

    /**
     * Este método crea un producto
     * @param producto el producto a meter en la base de datos
     */
    public static void create(Producto producto) {
            EntityManager em = ConexionODB.connect();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
    }

    /**
     * Este método lee un producto
     * @param referencia del producto
     * @return null si el producto no existe o el producto si este existe
     */
    public static Producto read(String referencia) {
        Producto devolver = null;
        EntityManager em = ConexionODB.connect();
        em.getTransaction().begin();
        devolver = em.find(Producto.class,referencia);
        em.getTransaction().commit();
        return devolver;
    }

    /**
     * Este método actualiza un producto
     * @param referencia del producto
     * @param producto el producto a actualizar en la base de datos
     * @param precio del producto
     */
    public static void update(String referencia, String producto , double precio) {
        EntityManager em = ConexionODB.connect();
        em.getTransaction().begin();
        Producto p = em.find(Producto.class,referencia);
        p.setNombreProducto(producto);
        p.setPrecio(precio);
        em.getTransaction().commit();
    }

    /**
     * Este método elimina un producto
     * @param referencia del producto a eliminar en la base de datos
     */
    public static void delete(String referencia) {
        EntityManager em = ConexionODB.connect();
        em.getTransaction().begin();
        Producto p = em.find(Producto.class, referencia);
        em.remove(p);
        em.getTransaction().commit();
    }

    /**
     * Este método busca productos con precio mayor al introducido
     * @param precio del producto
     * @return una colección de productos con precio mayor al introducido
     */
    public static List<Producto> buscarPorPrecio(double precio) {
        EntityManager em = ConexionODB.connect();
        String jpql = "SELECT p FROM Producto p WHERE p.precio > :prec" ;
        Query query = em.createQuery(jpql);
        query.setParameter("prec", precio);
        List<Producto> productos = query.getResultList();
        em.close();
        return productos;
    }

    /**
     * Método para buscar productos que tenga un precio dentro de un rango
     * @param precioMin Mínimo de precio que se busca
     * @param precioMax Máximo de precio que se busca
     * @return una colección de productos con precios entre el rango
     */
    public static List<Producto> buscarPorRango (double precioMin, double precioMax) {
        EntityManager em = ConexionODB.connect();
        String jpql = "SELECT p FROM Producto p WHERE p.precio > :precMin AND p.precio < :precMax" ;
        Query query = em.createQuery(jpql);
        query.setParameter("precMin", precioMin);
        query.setParameter("precMax", precioMax);
        List<Producto> productos = query.getResultList();
        em.close();
        return productos;
    }


}
