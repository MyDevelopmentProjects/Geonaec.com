package ge.mgl.service;

import ge.mgl.dao.GPermissionDAO;
import ge.mgl.entities.GPermission;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MJaniko on 3/9/2017.
 */
@Service
public class GPermissionService {

    @Autowired
    private GPermissionDAO permissionDAO;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return permissionDAO.getPaginatedList(searchExpression,
                sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional
    public GPermission save(GPermission permission) {
        if (permission.getId() != null) {
            return permissionDAO.update(permission);
        }
        return permissionDAO.create(permission);
    }

    @Transactional
    public boolean delete(Long id) {
        return permissionDAO.delete(id);
    }
}
