package ge.mgl.dao;

import ge.mgl.entities.GAbout;
import ge.mgl.entities.GNews;
import ge.mgl.entities.GUser;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MJaniko on 3/8/2017.
 */
@Repository
public class GNewsDAO extends PaginationAndFullSearchQuery<GNews>{

    public GNewsDAO() {
        super(GNews.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        List<String> fieldList = new ArrayList<>();
        if (resultClass == GNews.class) {
            fieldList.add("title");
            fieldList.add("description");
        }
        return fieldList;
    }

}
