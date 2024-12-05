package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionODB {

    /**
     * Establece una conexión con la base de datos y devuelve un objeto EntityManager.
     * @return Un objeto EntityManager para interactuar con la base de datos.
     */
    public static EntityManager connect() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/tienda.odb");
        EntityManager em = emf.createEntityManager();
        return em;
    }
}