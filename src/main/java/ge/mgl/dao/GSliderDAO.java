package ge.mgl.dao;

import ge.mgl.entities.GSlider;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MJaniko on 3/7/2017.
 */
@Repository
public class GSliderDAO extends PaginationAndFullSearchQuery<GSlider> {

    public GSliderDAO() {
        super(GSlider.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        return null;
    }

}
