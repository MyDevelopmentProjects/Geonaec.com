package ge.mgl.dao;

import ge.mgl.entities.GUserPasswordReset;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by user on 11/26/17.
 */
@Repository
public class GUserPasswordResetDAO extends PaginationAndFullSearchQuery<GUserPasswordReset> {

    public GUserPasswordResetDAO() {
        super(GUserPasswordReset.class);
    }

    public GUserPasswordReset findByHashCode(String hashCode){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GUserPasswordReset> cq = cb.createQuery(GUserPasswordReset.class);
        Root<GUserPasswordReset> rootEntry = cq.from(GUserPasswordReset.class);
        CriteriaQuery<GUserPasswordReset> all = cq.select(rootEntry).where(
            cb.equal(rootEntry.get("finished"), false),
            cb.equal(rootEntry.get("hashCode"), hashCode)
        );
        TypedQuery<GUserPasswordReset> allQuery = getEntityManager()
                .createQuery(all)
                .setMaxResults(1);
        List<GUserPasswordReset> resultList = allQuery.getResultList();
        return  (!resultList.isEmpty())? resultList.get(0):null;
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        return null;
    }
}
