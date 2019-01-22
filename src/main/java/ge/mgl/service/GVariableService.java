package ge.mgl.service;

import ge.mgl.dao.GVariableDAO;
import ge.mgl.entities.GVariable;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GVariableService {
    @Autowired
    private GVariableDAO variableDAO;

    @Transactional(readOnly = true)
    public PaginationAndFullSearchQueryResult getList() {
        return variableDAO.getPaginatedList(null, "id", true, 0, 20);
    }

    @Transactional
    public GVariable getById(Long id) {
        return variableDAO.getById(id);
    }

    @Transactional
    public GVariable getSingleResultByName(String name) {
        return variableDAO.getSingleResultByName(name);
    }

    @Transactional
    public void save(GVariable variable) {
        variableDAO.save(variable);
    }

}

