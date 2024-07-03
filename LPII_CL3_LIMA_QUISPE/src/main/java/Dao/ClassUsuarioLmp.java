package Dao;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Interfaces.lUsuario;
import model.TblUsuariocl3;

public class ClassUsuarioLmp implements lUsuario{
    
    private EntityManagerFactory emf;
    
    public ClassUsuarioLmp() {
        emf = Persistence.createEntityManagerFactory("LPII_CL3_LIMA_QUISPE");
    }
    
    public boolean validarUsuario(String usuario, String password) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TblUsuariocl3 usuarioDB = em.find(TblUsuariocl3.class, usuario);
            if (usuarioDB != null && usuarioDB.getPasswordcl3().equals(password)) {
                return true;
            }
            return false;
        } finally {
            em.close();
        }
    }
}
