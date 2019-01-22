package ge.mgl.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.mgl.dao.GMenuDAO;
import ge.mgl.entities.GMenu;
import ge.mgl.entities.GScrollObject;
import ge.mgl.utils.MGLStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by MJaniko on 3/9/2017.
 */
@Service
public class GMenuService {

    String url = "https://geonaec.com";
    //String url = "http://localhost:8080";

    @Autowired
    private GMenuDAO dao;

    @Transactional(readOnly = true)
    public GMenu find(Long id) {

        GMenu obj = dao.find(id);
        if (obj != null && obj.getFiles() != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<String> values = mapper.readValue(obj.getFiles(), new TypeReference<List<String>>() {});
                obj.setArray(values);
            } catch (Exception ignored) {}
        }

        return obj;
    }

    @Transactional(readOnly = true)
    public GMenu first() {
        return dao.first();
    }

    @Transactional(readOnly = true)
    public List<GMenu> all() {
        return dao.all();
    }

    public String buildMenu() {
        List<GMenu> list = listToTree(dao.all());
        menu = "";
        menuHTML(list, true);
        return menu;
    }

    private String menu;

    private String menuHTML(List<GMenu> list, boolean isRoot) {
        if (isRoot)
            menu += "<ul class=\"nav navbar-nav\">";
        else menu += "<ul class=\"sub-menu\">";

        for (GMenu obj : list) {
            if (obj.nodes != null && obj.nodes.size() > 0) {
                menu += "<li class=\"menu-item-has-children\">";

                if(MGLStringUtils.IsNullOrBlank(obj.getDescr()) && MGLStringUtils.IsNullOrBlank(obj.getImageURL())){
                    menu += "<a href='javascript:void(0)'>";
                } else {
                    menu += "<a href=" + url + "/item/" + obj.getId() + ">";
                }

                menu += obj.getTitle();
                menu += "</a>";
                menuHTML(obj.nodes, false);
                menu += "</li>";
            } else {
                menu += "<li>";

                if(MGLStringUtils.IsNullOrBlank(obj.getDescr()) && MGLStringUtils.IsNullOrBlank(obj.getImageURL())){
                    menu += "<a href='javascript:void(0)'>";
                } else {
                    menu += "<a href=" + url + "/item/" + obj.getId() + ">";
                }

                menu += obj.getTitle();
                menu += "</a>";
                menu += "</li>";
            }
        }

        menu += "</ul>";
        return menu;
    }

    private List<GMenu> listToTree(List<GMenu> list) {
        Map<Long, Integer> map = new HashMap<>();
        GMenu node;
        List<GMenu> roots = new ArrayList<>();
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            map.put(list.get(i).getId(), i);
            list.get(i).nodes = new ArrayList<>();
        }
        for (i = 0; i < list.size(); i++) {
            node = list.get(i);
            if (node.getParentId() != null) {
                list.get(map.get(node.getParentId())).nodes.add(node);
            } else {
                roots.add(node);
            }
        }
        return roots;
    }

    @Transactional
    public GMenu save(GMenu menu) {
        if (menu.getId() != null) {
            return dao.update(menu);
        }
        return dao.create(menu);
    }

    @Transactional
    public boolean delete(Long id) {
        return dao.delete(id);
    }

}
