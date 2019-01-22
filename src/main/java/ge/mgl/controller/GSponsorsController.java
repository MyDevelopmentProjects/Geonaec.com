package ge.mgl.controller;

import ge.mgl.entities.GSponsors;
import ge.mgl.service.*;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static ge.mgl.utils.constants.Constants.ControllerCodes.*;

/**
 * Created by MJaniko on 3/9/2017.
 */
@RequestMapping("/sponsors")
@Controller
public class GSponsorsController {

    @Autowired
    private GSponsorsService service;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "first", method = RequestMethod.GET)
    @ResponseBody
    public GSponsors first() {
        return service.first();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + DELETE, method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse delete(@RequestBody Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return GeneralUtil.RequestSuccess();
        }
        return GeneralUtil.RequestError();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse save(@RequestBody GSponsors sponsor) {
        service.save(sponsor);
        return GeneralUtil.RequestSuccess();
    }
}
