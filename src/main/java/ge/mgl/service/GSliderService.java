package ge.mgl.service;

import ge.mgl.dao.GSliderDAO;
import ge.mgl.entities.GSlider;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MJaniko on 3/7/2017.
 */
@Service
public class GSliderService {

    @Autowired
    private GSliderDAO sliderDAO;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult getList(String searchExpression, String sortField, boolean isAscending, Integer pageNumber, int pageSize) {
        return sliderDAO.getPaginatedList(searchExpression,
                sortField, isAscending, pageNumber, pageSize);
    }

    @Transactional(readOnly = true)
    public List<GSlider> getAll(){
        return sliderDAO.all();
    }

    @Transactional(readOnly = true)
    public GSlider first(){
        return sliderDAO.first();
    }

    @Transactional(readOnly = true)
    public List<GSlider> getAllActive(){
        return sliderDAO.all();
    }

    @Transactional
    public GSlider save(GSlider slider) {
        if (slider.getId() != null) {
            return sliderDAO.update(slider);
        }
        return sliderDAO.create(slider);
    }

    @Transactional
    public boolean delete(Long id) {
        return sliderDAO.delete(id);
    }
}
