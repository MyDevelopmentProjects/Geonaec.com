package ge.mgl.service;

import ge.mgl.dao.GSettingsDAO;
import ge.mgl.entities.GSettings;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mikheil on 11/10/2017.
 */
@Service
public class GSettingsService {

    @Autowired
    private GSettingsDAO settingsDAO;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return settingsDAO.getPaginatedList(searchExpression,
                sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional
    public GSettings save(GSettings settings) {
        if (settings.getId() != null) {
            return settingsDAO.update(settings);
        }
        return settingsDAO.create(settings);
    }

    @Transactional
    public List<GSettings> getAll(){
        return settingsDAO.all();
    }

    @Transactional(readOnly = true)
    public GSettings findById(Long id){
        return settingsDAO.find(id);
    }

    @Transactional
    public boolean delete(Long id) {
        return settingsDAO.delete(id);
    }
}
