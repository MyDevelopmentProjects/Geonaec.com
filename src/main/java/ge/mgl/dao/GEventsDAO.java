package ge.mgl.dao;

import ge.mgl.entities.GEvent;
import ge.mgl.entities.GSlider;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MJaniko on 3/7/2017.
 */
@Repository
public class GEventsDAO extends PaginationAndFullSearchQuery<GEvent> {

    public GEventsDAO() {
        super(GEvent.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        return null;
    }

}
