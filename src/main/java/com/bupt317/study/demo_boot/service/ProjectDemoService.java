package com.bupt317.study.demo_boot.service;

import com.bupt317.study.demo_boot.mapper.ProjectDemoMapper;
import com.bupt317.study.demo_boot.pojo.mybatis.ProjectDemo;
import com.bupt317.study.demo_boot.pojo.mybatis.ProjectDemoExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 菲尼莫斯 on 2018/7/14 11:09
 *
 * @Description : ProjectDemoService
 * @Program : demo_boot
 */
@Service
public class ProjectDemoService {

    private ProjectDemoMapper projectDemoMapper;

    @Autowired
    public ProjectDemoService(ProjectDemoMapper projectDemoMapper) {
        this.projectDemoMapper = projectDemoMapper;
    }

    public Integer insert(ProjectDemo projectDemo) {
        return projectDemoMapper.insert(projectDemo);
    }

    public ProjectDemo getById(Integer id) {
        return projectDemoMapper.selectByPrimaryKey(id);
    }


    public List<ProjectDemo> getByName(String name) {
        ProjectDemoExample example = new ProjectDemoExample();
        ProjectDemoExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + name + "%");
        return projectDemoMapper.selectByExample(example);
    }

    public List<ProjectDemo> getAllOrderByTime() {
        ProjectDemoExample example = new ProjectDemoExample();
        example.setOrderByClause("time desc");
        ProjectDemoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return projectDemoMapper.selectByExample(example);
    }

    public List<ProjectDemo> getAllWithLimit(Integer page, Integer limit) {
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        ProjectDemoExample example = new ProjectDemoExample();
        example.setOrderByClause("id");
        ProjectDemoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return projectDemoMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    public Integer getAllCount() {
        ProjectDemoExample example = new ProjectDemoExample();
        ProjectDemoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        Long count = projectDemoMapper.countByExample(example);
        return count.intValue();
    }

    public Integer delById(Integer id) {
        return projectDemoMapper.deleteByPrimaryKey(id);
    }

    public Integer updateById(ProjectDemo projectDemo) {
        return projectDemoMapper.updateByPrimaryKeySelective(projectDemo);
    }

}
