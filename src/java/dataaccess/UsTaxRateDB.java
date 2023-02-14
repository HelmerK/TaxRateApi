package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Location;
import models.UsTaxRate;

/**
 *
 * @author William Lau
 */
public class UsTaxRateDB {
    
    public UsTaxRate getUs(String locationCode) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Query queryCanLocCode = em.createNamedQuery("Location.findByLocationCode");
            queryCanLocCode.setParameter("locationCode", locationCode);
            Location Loc = (Location) queryCanLocCode.getSingleResult();
            
            UsTaxRate usTaxRate = Loc.getUsTaxRateList().get(0);
            
            return usTaxRate;
            
        } finally {
            em.close();
        }
    }
    
    public List<UsTaxRate> getAllUs() throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            
            List<UsTaxRate> taxRates = em.createNamedQuery("UsTaxRate.findAll", UsTaxRate.class).getResultList();
            return taxRates;
            
        } finally {
            em.close();
        }
    }
    
    public void insertUs(UsTaxRate usTaxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
         
            tran.begin();
            em.persist(usTaxRate);
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
    public void deleteUs(UsTaxRate usTaxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
           
            tran.begin();
            em.remove(em.merge(usTaxRate));
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
    public void updateUs(UsTaxRate usTaxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            
            tran.begin();
            em.merge(usTaxRate);
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
}
    
