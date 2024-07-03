package TestEntidades;

import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.TblProductocl3;

public class TestEntidadProducto {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            // Crear EntityManagerFactory basado en la unidad de persistencia (persistence unit) especificada
            emf = Persistence.createEntityManagerFactory("LPII_CL3_LIMA_QUISPE");

            // Crear EntityManager
            em = emf.createEntityManager();

            // Iniciar una transacción
            tx = em.getTransaction();
            tx.begin();

            // Crear un objeto TblProductocl2 y establecer algunos valores
            TblProductocl3 producto = new TblProductocl3();
            producto.setNombrecl3("");
            producto.setPreciocompcl3(.0);
            producto.setPrecioventacl3(.0);
            producto.setEstadocl3("");
            producto.setDescripcl3("");

            // Guardar el producto en la base de datos
            em.persist(producto);

            // Commit de la transacción
            tx.commit();

            // Imprimir mensaje de éxito
            System.out.println("Producto guardado exitosamente con ID: " + producto.getIdproductoscl3());

        } catch (Exception e) {
            // Manejo de excepciones
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar EntityManager y EntityManagerFactory
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
