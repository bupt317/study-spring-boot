package com.bupt317.study.demo_boot.controller;

import com.bupt317.study.demo_boot.pojo.LayuiTableData;
import com.bupt317.study.demo_boot.pojo.Project;
import com.bupt317.study.demo_boot.pojo.mybatis.ProjectDemo;
import com.bupt317.study.demo_boot.service.ProjectDemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Create by 菲尼莫斯 on 2018/7/11 21:00
 *
 * @Description : ProjectController
 * @Program : demo_boot
 */

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectDemoService projectDemoService;

    @RequestMapping(value = {"/", ""})
    @ResponseBody
    public String toIndex() {
        return "Hello Happy World!";
    }

    @RequestMapping("/index")
    public String toIndexTemplates() {
        return "/project/index";
    }

    @RequestMapping("/normal")
    public String toNormal(Model model) {
        model.addAttribute("name", "Hello Happy World");
        model.addAttribute("description", "Happy! Lucky! Simile! Yeah!");
        return "/project/normal";
    }

    @RequestMapping("/{name}")
    public String toAuto(Model model, @PathVariable String name) {
        Project project = new Project();
        project.setName(name);
        project.setTime(new Date());
        model.addAttribute("project", project);
        return "/project/auto";
    }

    @RequestMapping("/var")
    public String toVar(Model model, String name, String description) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setTime(new Date());
        model.addAttribute("project", project);
        //model.addAttribute("success",100);
        return "/project/var";
    }

    @RequestMapping("/cycle")
    public String toCycle(Model model) {
        List<ProjectDemo> list = projectDemoService.getAllOrderByTime();
        model.addAttribute("list", list);
        return "/project/cycle";
    }

    @RequestMapping(value = {"/doRegister"}, method = RequestMethod.POST)
    public String doRegister(Model model, Project project) {
        //project.setTime(new Date());
        model.addAttribute("project", project);

        ProjectDemo projectDemo = new ProjectDemo();
        BeanUtils.copyProperties(project, projectDemo);
        Integer success = projectDemoService.insert(projectDemo);
        model.addAttribute("success", success);

        return "/project/var";
    }



    @RequestMapping("/getData")
    @ResponseBody
    public LayuiTableData getData(Integer page, Integer limit) {
        LayuiTableData layuiTableData = new LayuiTableData();
        layuiTableData.setCode(0);
        layuiTableData.setCount(projectDemoService.getAllCount());
        layuiTableData.setData(projectDemoService.getAllWithLimit(page, limit));
        return layuiTableData;
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del( @RequestParam(value = "idList[]") int[] idList) {
        int success = 0;
        for (int id : idList) {
            success += projectDemoService.delById(id);
        }
        return "删除" + idList.length + "条数据，成功" + success + "条。";
    }

    @RequestMapping("/edit")
    public String edit(int id,Model model) {
        ProjectDemo projectDemo = projectDemoService.getById(id);
        model.addAttribute("project",projectDemo);
        return "/project/edit";
    }

    @RequestMapping("/doEdit")
    @ResponseBody
    public String doEdit(Project project,int id){
        ProjectDemo projectDemo = new ProjectDemo();
        BeanUtils.copyProperties(project, projectDemo);
        projectDemo.setId(id);
        Integer success = projectDemoService.updateById(projectDemo);
        return "更新成功"+success+"条";
    }
}
