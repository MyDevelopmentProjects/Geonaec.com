package ge.mgl.controller;

import ge.mgl.entities.GSettings;
import ge.mgl.service.GSettingsService;
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
 * Created by user on 11/12/17.
 */
@RestController
@RequestMapping("/settings")
public class GSettingsController {

    @Autowired
    private GSettingsService settingsService;

    @RequestMapping(value = "/all",method = RequestMethod.POST)
    public List<GSettings> getAll(){
        return settingsService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + PUT, method = RequestMethod.POST)
    public RequestResponse save(@RequestBody GSettings settings) {
        settingsService.save(settings);
        return GeneralUtil.RequestSuccess();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + DELETE, method = RequestMethod.POST)
    public RequestResponse delete(@RequestBody Long id) {
        boolean deleted = settingsService.delete(id);
        if (deleted) {
            return GeneralUtil.RequestSuccess();
        }
        return GeneralUtil.RequestError();
    }
}
