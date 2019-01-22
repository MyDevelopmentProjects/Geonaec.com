package ge.mgl.controller;

import ge.mgl.entities.GAbout;
import ge.mgl.entities.GNews;
import ge.mgl.service.*;
import ge.mgl.utils.GeneralUtil;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.pagination.PaginationAndFullSearchQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static ge.mgl.utils.constants.Constants.ControllerCodes.*;
import static ge.mgl.utils.constants.Constants.ControllerCodes.PAGE_NUMBER_DEFAULT_VALUE;
import static ge.mgl.utils.constants.Constants.ControllerCodes.PAGE_SIZE_DEFAULT_VALUE;

/**
 * Created by MJaniko on 3/9/2017.
 */
@RequestMapping("/news")
@Controller
public class GNewsController {

    @Autowired
    private GNewsService service;

    @Autowired
    private GMenuService menuService;

    @Autowired
    private GVariableService variableService;

    @Autowired
    private GGalleryService galleryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = SLASH + LIST, method = RequestMethod.GET)
    @ResponseBody
    public PaginationAndFullSearchQueryResult getList(
            @RequestParam(required = false, defaultValue = STRING_EMPTY) String searchExpression,
            @RequestParam(required = false, defaultValue = STRING_EMPTY) String sortField,
            @RequestParam(required = false, defaultValue = IS_ASCENDING_DEFAULT_VALUE) boolean isAscending,
            @RequestParam(value = PAGE_NUMBER, required = false, defaultValue = PAGE_NUMBER_DEFAULT_VALUE) Integer pageNumber,
            @RequestParam(required = false, defaultValue = PAGE_SIZE_DEFAULT_VALUE) int pageSize) {
        return service.getList(searchExpression, sortField, isAscending, pageNumber, pageSize);
    }

    @RequestMapping(value = {"/all"}, method = RequestMethod.GET)
    public String home(Model model,
                       @RequestParam(required = false, defaultValue = STRING_EMPTY) String searchExpression,
                       @RequestParam(required = false, defaultValue = STRING_EMPTY) String sortField,
                       @RequestParam(required = false, defaultValue = IS_ASCENDING_DEFAULT_VALUE) boolean isAscending,
                       @RequestParam(value = "currPage", required = false, defaultValue = PAGE_NUMBER_DEFAULT_VALUE) Integer currPage,
                       @RequestParam(required = false, defaultValue = PAGE_SIZE_DEFAULT_VALUE) int pageSize) {
        model.addAttribute("content", "news");
        model.addAttribute("pageTitle", "News");

        model.addAttribute("searchExpression", searchExpression);
        model.addAttribute("sortField", sortField);
        model.addAttribute("isAscending", isAscending);
        model.addAttribute("pageNumber", currPage);
        model.addAttribute("pageSize", pageSize);

        model.addAttribute("list", service.getList(searchExpression, sortField, isAscending, currPage, pageSize));
        model.addAttribute("variable", variableService.getList());
        model.addAttribute("lastnews", service.lastX(6));
        model.addAttribute("gallery", galleryService.first());
        model.addAttribute("menu", menuService.buildMenu());
        return "index";
    }

    @RequestMapping(value = {"/detail/{id}"}, method = RequestMethod.GET)
    public String detail(Model model,
                         @PathVariable("id") Long id) {
        GNews news = service.find(id);
        if (news == null) {
            model.addAttribute("pageTitle", "Not Found");
            model.addAttribute("content", "404");
        } else {
            model.addAttribute("content", "newsdetail");
            model.addAttribute("pageTitle", "News");
            model.addAttribute("object", news);
        }
        model.addAttribute("variable", variableService.getList());
        model.addAttribute("lastnews", service.lastX(6));
        model.addAttribute("menu", menuService.buildMenu());
        return "index";
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
    public RequestResponse save(@RequestBody GNews news) {
        service.save(news);
        return GeneralUtil.RequestSuccess();
    }
}
