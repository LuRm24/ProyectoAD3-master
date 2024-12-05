package dao;

import clases.Contrato;
import clases.Empleado;
import clases.Producto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EmpleadoDAO {

    /**
     * Este método crea un empleado
     * @param empleado el empleado a meter en la base de datos
     */
    public static void create(Empleado empleado) {
        EntityManager em = ConexionODB.connect();
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
    }

    /**
     * Este método lee un empleado
     * @param idEmpleado el id del empleado para encontrarlo en la base de datos
     * @return null en caso de que el empleado no exista o el empleado en caso de que exista
     */
    public static Empleado read(int idEmpleado) {
        Empleado devolver = null;
        EntityManager em = ConexionODB.connect();
        em.getTransaction().begin();
        devolver = em.find(Empleado.class,idEmpleado);
        em.getTransaction().commit();
        return devolver;
    }

    /**
     * Este método actualiza un empleado
     * @param idEmpleado el id del empleado para encontrarlo en la base de datos
     * @param nombre del empleado
     * @param apellido del empleado
     */
    public static void update(int idEmpleado, String nombre, String apellido) {
        EntityManager em = ConexionODB.connect();
        em.getTransaction().begin();
        Empleado e = em.find(Empleado.class,idEmpleado);
        e.setNombre(nombre);
        e.setApellido(apellido);
        em.getTransaction().commit();
    }

    /**
     * Este método elimina un empleado de la base de datos
     * @param idEmpleado el id del empleado para encontrarlo en la base de datos
     */
    public static void delete(int idEmpleado) {
        EntityManager em = ConexionODB.connect();
        em.getTransaction().begin();
        Empleado e = em.find(Empleado.class,idEmpleado);
        em.remove(e);
        em.getTransaction().commit();
    }
}
