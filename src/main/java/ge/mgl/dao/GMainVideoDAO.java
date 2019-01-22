package ge.mgl.dao;

import ge.mgl.entities.GMainVideo;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mikheil on 11/25/2017.
 */
@Repository
public class GMainVideoDAO extends PaginationAndFullSearchQuery<GMainVideo> {

    public GMainVideoDAO() {
        super(GMainVideo.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        return null;
    }
}
