package ge.mgl.controller;

import ge.mgl.entities.GEvent;
import ge.mgl.entities.GSlider;
import ge.mgl.service.GEventsService;
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
@RequestMapping("/events")
public class GEventsController {

    @Autowired
    private GEventsService service;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "all", method = RequestMethod.GET)
    public List<GEvent> getAll(){
        return service.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    public RequestResponse save(@RequestBody GEvent user) {
        service.save(user);
        return GeneralUtil.RequestSuccess();
    }

}
