package ge.mgl.dao;

import ge.mgl.entities.GEvent;
import ge.mgl.entities.GVariable;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by MJaniko on 1/6/2017.
 */
@Repository
public class GVariableDAO extends PaginationAndFullSearchQuery {

    public GVariableDAO() {
        super(GVariable.class);
    }

    @PersistenceContext
    private EntityManager em;

    public GVariable getById(Long id) {
        GVariable variable = em.find(GVariable.class, id);
        if (variable != null) {
            return variable;
        }
        return null;
    }

    public GVariable getSingleResultByName(String name){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GVariable> cq = cb.createQuery(GVariable.class);
        Root<GVariable> rootEntry = cq.from(GVariable.class);
        CriteriaQuery<GVariable> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("name"), name));
        TypedQuery<GVariable> allQuery = em.createQuery(all);

        if (allQuery.getResultList() != null && allQuery.getResultList().size() > 0) {
            return allQuery.getResultList().get(0);
        }
        return  null;
    }

    public void save(GVariable variable) {
        if (variable.getId() != null) {
            em.merge(variable);
        } else {
            em.persist(variable);
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<String> getFieldsAvailableForFullTextSearch(Class resultClass) {
        return null;
    }

}
