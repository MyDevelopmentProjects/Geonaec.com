package ge.mgl.service;

import ge.mgl.dao.GMainVideoDAO;
import ge.mgl.entities.GMainVideo;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mikheil on 11/25/2017.
 */
@Service
public class GMainVideoService {

    @Autowired
    private GMainVideoDAO mainVideoDAO;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return mainVideoDAO.getPaginatedList(searchExpression,
                sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional(readOnly = true)
    public GMainVideo first(){
        return mainVideoDAO.first();
    }

    @Transactional
    public GMainVideo save(GMainVideo video) {
        if (video.getId() != null) {
            return mainVideoDAO.update(video);
        }
        return mainVideoDAO.create(video);
    }

    @Transactional
    public boolean delete(Long id) {
        return mainVideoDAO.delete(id);
    }
}
