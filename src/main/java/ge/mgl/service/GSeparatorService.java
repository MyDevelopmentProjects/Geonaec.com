package ge.mgl.service;

import ge.mgl.dao.GSeparatorDAO;
import ge.mgl.entities.GSeparatorSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MJaniko on 3/9/2017.
 */
@Service
public class GSeparatorService {

    @Autowired
    private GSeparatorDAO dao;

    @Transactional(readOnly = true)
    public GSeparatorSection find(Long id){
        return dao.find(id);
    }

    @Transactional(readOnly = true)
    public GSeparatorSection first(){
        return dao.first();
    }

    @Transactional(readOnly = true)
    public List<GSeparatorSection> all(){
        return dao.all();
    }

    @Transactional
    public GSeparatorSection save(GSeparatorSection separator) {
        if (separator.getId() != null) {
            return dao.update(separator);
        }
        return dao.create(separator);
    }

    @Transactional
    public boolean delete(Long id) {
        return dao.delete(id);
    }
}
