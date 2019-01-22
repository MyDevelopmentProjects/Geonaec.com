package ge.mgl.dao;

import ge.mgl.entities.GPermission;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MJaniko on 3/9/2017.
 */
@Repository
public class GPermissionDAO extends PaginationAndFullSearchQuery<GPermission>{

    public GPermissionDAO() {
        super(GPermission.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        return null;
    }
}
