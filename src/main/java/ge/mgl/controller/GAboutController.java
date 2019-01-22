package ge.mgl.controller;

import ge.mgl.entities.GAbout;
import ge.mgl.service.GAboutService;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static ge.mgl.utils.constants.Constants.ControllerCodes.PUT;
import static ge.mgl.utils.constants.Constants.ControllerCodes.SLASH;

/**
 * Created by MJaniko on 3/9/2017.
 */
@RestController
@RequestMapping("/about-us")
public class GAboutController {

    @Autowired
    private GAboutService aboutService;

    @RequestMapping(value = SLASH + "first", method = RequestMethod.POST)
    public GAbout getFirsRecord(){
        return aboutService.first();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    public RequestResponse save(@RequestBody GAbout about) {
        aboutService.save(about);
        return GeneralUtil.RequestSuccess();
    }
}
