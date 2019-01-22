package ge.mgl.controller;

import ge.mgl.entities.GSlider;
import ge.mgl.service.GSliderService;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ge.mgl.utils.constants.Constants.ControllerCodes.*;

/**
 * Created by user on 11/17/17.
 */
@RestController
@RequestMapping("/slider")
public class GSliderController {

    @Autowired
    private GSliderService sliderService;

    @RequestMapping(value = SLASH + "list", method = RequestMethod.GET)
    public List<GSlider> getAllActive(){
        return sliderService.getAllActive();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "all", method = RequestMethod.GET)
    public List<GSlider> getAll(){
        return sliderService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    public RequestResponse save(@RequestBody GSlider user) {
        sliderService.save(user);
        return GeneralUtil.RequestSuccess();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + DELETE, method = RequestMethod.POST)
    public RequestResponse delete(@RequestBody Long id) {
        boolean deleted = sliderService.delete(id);
        if (deleted) {
            return GeneralUtil.RequestSuccess();
        }
        return GeneralUtil.RequestError();
    }
}
