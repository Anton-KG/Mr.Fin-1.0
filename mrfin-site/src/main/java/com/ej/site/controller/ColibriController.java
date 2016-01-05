package com.ej.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class is responsible for preparing a model map with data and selecting a view to be rendered.
 */
@Controller
public class ColibriController {

    /**
     * This field represents the environment in which application is running.
     */
    @Autowired
    private Environment env;

    /**
     * This method mapped on {@code url} "/" which .
     *
     * @param model uses build model data with UI
     * @return String which maps to the file index.html
     */
    @RequestMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("router_url", env.getProperty("router.url"));
        return "index";
    }
}
