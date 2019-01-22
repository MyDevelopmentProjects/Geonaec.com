package ge.mgl.controller;

/**
 * Created by MJaniko on 1/6/2017.
 */

import ge.mgl.entities.GVariable;
import ge.mgl.service.GVariableService;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variable")
public class GVariableController {

    @Autowired
    private GVariableService variableService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PaginationAndFullSearchQueryResult getList() {
        return variableService.getList();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RequestResponse save(@RequestBody GVariable variable) {
        variableService.save(variable);
        return new RequestResponse();
    }

}
