package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Interfaces.lProducto;
import model.TblProductocl3;

public class ClassProductoLmp implements lProducto {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LPII_CL3_LIMA_QUISPE");

    @Override
    public void RegistrarProducto(TblProductocl3 producto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void ActualizarProducto(TblProductocl3 producto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(producto);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void EliminarProducto(TblProductocl3 producto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TblProductocl3 elim = em.merge(producto);
        em.remove(elim);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public TblProductocl3 BuscarProducto(int idProducto) {
        EntityManager em = emf.createEntityManager();
        TblProductocl3 busProducto = em.find(TblProductocl3.class, idProducto);
        em.close();
        return busProducto;
    }

    @Override
    public List<TblProductocl3> ListadoProductos() {
        EntityManager em = emf.createEntityManager();
        List<TblProductocl3> listadoProductos = em.createNamedQuery("TblProductocl3.findAll", TblProductocl3.class).getResultList();
        em.close();
        return listadoProductos;
    }
}
