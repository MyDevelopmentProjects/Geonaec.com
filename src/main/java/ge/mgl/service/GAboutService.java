package ge.mgl.service;

import ge.mgl.dao.GAboutDAO;
import ge.mgl.entities.GAbout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MJaniko on 3/9/2017.
 */
@Service
public class GAboutService {

    @Autowired
    private GAboutDAO aboutDAO;

    @Transactional(readOnly = true)
    public GAbout find(Long id){
        return aboutDAO.find(id);
    }

    @Transactional(readOnly = true)
    public GAbout first(){
        return aboutDAO.first();
    }

    @Transactional
    public GAbout save(GAbout about) {
        if (about.getId() != null) {
            return aboutDAO.update(about);
        }
        return aboutDAO.create(about);
    }

    @Transactional
    public boolean delete(Long id) {
        return aboutDAO.delete(id);
    }
}
