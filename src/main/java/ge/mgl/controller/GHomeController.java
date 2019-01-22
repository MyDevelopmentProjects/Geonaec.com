package ge.mgl.controller;

import ge.mgl.entities.GGallery;
import ge.mgl.entities.GMenu;
import ge.mgl.entities.GScrollObject;
import ge.mgl.entities.GSeparatorSection;
import ge.mgl.service.*;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ge.mgl.utils.constants.Constants.ControllerCodes.SLASH;

/**
 * Created by MJaniko on 3/10/2017.
 */
@Controller
public class GHomeController {

    @Autowired
    private GSliderService sliderService;

    @Autowired
    private GEventsService eventsService;

    @Autowired
    private GAboutService aboutService;

    @Autowired
    private GSeparatorService separatorService;

    @Autowired
    private GScrollService scrollService;

    @Autowired
    private GGalleryService galleryService;

    @Autowired
    private GSponsorsService sponsorsService;

    @Autowired
    private GNewsService newsService;

    @Autowired
    private GVariableService variableService;

    @Autowired
    private GMenuService service;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("content", "main");
        model.addAttribute("pageTitle", "GeoNaec.com");
        model.addAttribute("topSlider", sliderService.first());
        model.addAttribute("topEvents", eventsService.first());
        model.addAttribute("separator", separatorService.first());
        model.addAttribute("about", aboutService.first());
        model.addAttribute("scroll", scrollService.getAll());
        model.addAttribute("gallery", galleryService.first());
        model.addAttribute("sponsors", sponsorsService.first());
        model.addAttribute("news", newsService.lastX(3));
        model.addAttribute("variable", variableService.getList());
        model.addAttribute("lastnews", newsService.lastX(6));
        model.addAttribute("menu", service.buildMenu());
        return "index";
    }

    @RequestMapping(value = {"/item/{id}"}, method = RequestMethod.GET)
    public String menuDetails(Model model, @PathVariable Long id) {
        model.addAttribute("pageTitle", "GeoNaec.com");
        model.addAttribute("menu", service.buildMenu());
        GMenu menu = service.find(id);
        if (menu != null) {
            model.addAttribute("content", "menu-details");
            model.addAttribute("data", menu);
        } else {
            model.addAttribute("content", "404");
        }
        return "index";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "get/separator", method = RequestMethod.GET)
    @ResponseBody
    public GSeparatorSection getSeparator(){
        return separatorService.first();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "put/separator", method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse putSeparator(@RequestBody GSeparatorSection separatorSection){
        separatorService.save(separatorSection);
        return GeneralUtil.RequestSuccess();
    }


    /****SCROLL****/
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "get/scroll", method = RequestMethod.GET)
    @ResponseBody
    public List<GScrollObject> getScroll(){
        return scrollService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "put/scroll", method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse putScroll(@RequestBody GScrollObject scrollObject){
        scrollService.save(scrollObject);
        return GeneralUtil.RequestSuccess();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "delete/scroll", method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse deleteScroll(@RequestBody Long id){
        scrollService.delete(id);
        return GeneralUtil.RequestSuccess();
    }
    /********SCROLL********/


    /********GALLERY********/
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "get/gallery", method = RequestMethod.GET)
    @ResponseBody
    public GGallery getGallery(){
        return galleryService.first();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + "put/gallery", method = RequestMethod.POST)
    @ResponseBody
    public RequestResponse putGallery(@RequestBody GGallery gallery){
        galleryService.save(gallery);
        return GeneralUtil.RequestSuccess();
    }
    /********GALLERY********/


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "security/index";
    }

}

