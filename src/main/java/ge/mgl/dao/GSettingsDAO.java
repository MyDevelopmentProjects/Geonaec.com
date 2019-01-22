package ge.mgl.dao;

import ge.mgl.entities.GSettings;
import ge.mgl.utils.pagination.PaginationAndFullSearchQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikheil on 11/10/2017.
 */
@Repository
public class GSettingsDAO extends PaginationAndFullSearchQuery<GSettings> {

    public GSettingsDAO(){
        super(GSettings.class);
    }

    @Override
    public <T> List<String> getFieldsAvailableForFullTextSearch(Class<T> resultClass) {
        List<String> resultList = new ArrayList<>();
        if(resultClass == GSettings.class){
            resultList.add("path");
            resultList.add("translation.titleGE");
            resultList.add("translation.titleEN");
            resultList.add("translation.titleRU");
        }
        return resultList;
    }
}
