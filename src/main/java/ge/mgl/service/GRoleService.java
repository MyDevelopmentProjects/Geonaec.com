package ge.mgl.service;

import ge.mgl.dao.GRoleDAO;
import ge.mgl.entities.GRole;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MJaniko on 3/9/2017.
 */
@Service
public class GRoleService {

    @Autowired
    private GRoleDAO roleDAO;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return roleDAO.getPaginatedList(searchExpression,
                sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional
    public GRole save(GRole role) {
        if (role.getId() != null) {
            return roleDAO.update(role);
        }
        return roleDAO.create(role);
    }

    @Transactional
    public boolean delete(Long id) {
        return roleDAO.delete(id);
    }
}
