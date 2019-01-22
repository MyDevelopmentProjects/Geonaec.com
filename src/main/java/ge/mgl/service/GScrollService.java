package ge.mgl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.mgl.dao.GScrollObjectDAO;
import ge.mgl.entities.GScrollObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MJaniko on 3/7/2017.
 */
@Service
public class GScrollService {

    @Autowired
    private GScrollObjectDAO dao;

    @Transactional(readOnly = true)
    public List<GScrollObject> getAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<GScrollObject> list = dao.all();
        list.forEach(obj -> {
            if (obj.getFiles() != null) {
                try {
                    List<String> values = mapper.readValue(obj.getFiles(), new TypeReference<List<String>>(){});
                    obj.setArray(values);
                } catch (Exception e) {
                }
            }
        });
        return list;
    }

    @Transactional(readOnly = true)
    public GScrollObject first() {
        return dao.first();
    }

    @Transactional
    public GScrollObject save(GScrollObject object) {
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
