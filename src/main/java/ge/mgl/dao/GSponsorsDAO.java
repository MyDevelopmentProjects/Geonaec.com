package ge.mgl.dao;

import ge.mgl.entities.GAbout;
import ge.mgl.entities.GSponsors;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MJaniko on 3/8/2017.
 */
@Repository
public class GSponsorsDAO extends PaginationAndFullSearchQuery<GSponsors>{

    public GSponsorsDAO() {
        super(GSponsors.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        return null;
    }

}
