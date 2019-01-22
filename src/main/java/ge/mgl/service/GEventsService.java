package ge.mgl.service;

import ge.mgl.dao.GEventsDAO;
import ge.mgl.entities.GEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MJaniko on 3/7/2017.
 */
@Service
public class GEventsService {

    @Autowired
    private GEventsDAO eventsDAO;


    @Transactional(readOnly = true)
    public List<GEvent> getAll(){
        return eventsDAO.all();
    }

    @Transactional(readOnly = true)
    public GEvent first(){
        return eventsDAO.first();
    }

    @Transactional
    public GEvent save(GEvent slider) {
        if (slider.getId() != null) {
            return eventsDAO.update(slider);
        }
        return eventsDAO.create(slider);
    }

    @Transactional
    public boolean delete(Long id) {
        return eventsDAO.delete(id);
    }
}
