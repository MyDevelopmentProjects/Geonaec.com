package ge.mgl.service;

import ge.mgl.dao.GGalleryDAO;
import ge.mgl.entities.GAbout;
import ge.mgl.entities.GGallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MJaniko on 3/9/2017.
 */
@Service
public class GGalleryService {

    @Autowired
    private GGalleryDAO dao;

    @Transactional(readOnly = true)
    public GGallery find(Long id){
        return dao.find(id);
    }

    @Transactional(readOnly = true)
    public GGallery first(){
        return dao.first();
    }

    @Transactional
    public GGallery save(GGallery object) {
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
