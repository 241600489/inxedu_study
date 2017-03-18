package com.inxedu.os.nstar.appointment.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by karak on 16-8-5.
 */
public interface AppointmentBaseController {
    @RequestMapping("/list")
    public String list(final HttpServletRequest request);
    @RequestMapping("/page/{id}/{type}")
    public String editPage(final HttpServletRequest request, @PathVariable("id") String id, @PathVariable("edit") String type);
    @RequestMapping("/op/{id}/{type}")
    public String edit(final HttpServletRequest request, @PathVariable("edit") String type);
    @RequestMapping("/op/delete/{id}")
    public String delete(final HttpServletRequest request, @PathVariable("id") String id);
    @RequestMapping("/op/del")
    public String del(final HttpServletRequest request);
}
