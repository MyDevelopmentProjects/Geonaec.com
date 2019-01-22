package ge.mgl.dao;

import ge.mgl.entities.GSeparatorSection;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MJaniko on 3/8/2017.
 */
@Repository
public class GSeparatorDAO extends PaginationAndFullSearchQuery<GSeparatorSection>{

    public GSeparatorDAO() {
        super(GSeparatorSection.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        return null;
    }

}
