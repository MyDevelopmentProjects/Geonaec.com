package ge.mgl.service;

import ge.mgl.dao.GSponsorsDAO;
import ge.mgl.entities.GSponsors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MJaniko on 3/9/2017.
 */
@Service
public class GSponsorsService {

    @Autowired
    private GSponsorsDAO dao;

    @Transactional(readOnly = true)
    public GSponsors find(Long id){
        return dao.find(id);
    }

    @Transactional(readOnly = true)
    public GSponsors first(){
        return dao.first();
    }

    @Transactional
    public GSponsors save(GSponsors object) {
        if (object.getId() != null) {
            return dao.update(object);
        }
        return dao.create(object);
    }

    @Transactional
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}
