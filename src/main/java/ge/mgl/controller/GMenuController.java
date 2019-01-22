package ge.mgl.controller;

import ge.mgl.entities.GMenu;
import ge.mgl.service.GMenuService;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ge.mgl.utils.constants.Constants.ControllerCodes.PUT;
import static ge.mgl.utils.constants.Constants.ControllerCodes.SLASH;

/**
 * Created by MJaniko on 3/9/2017.
 */
@RestController
@RequestMapping("/menu")
public class GMenuController {

    @Autowired
    private GMenuService service;

    @RequestMapping(value = SLASH + "/all", method = RequestMethod.GET)
    public List<GMenu> getAll(){
        return service.all();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    public RequestResponse save(@RequestBody GMenu menu) {
        service.save(menu);
        return GeneralUtil.RequestSuccess();
    }
}
