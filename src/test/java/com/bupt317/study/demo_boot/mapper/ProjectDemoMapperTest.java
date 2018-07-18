package com.bupt317.study.demo_boot.mapper;

import com.bupt317.study.demo_boot.DemoBootApplication;
import com.bupt317.study.demo_boot.pojo.mybatis.ProjectDemo;
import com.bupt317.study.demo_boot.pojo.mybatis.ProjectDemoExample;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by 菲尼莫斯 on 2018/7/13 22:14
 *
 * @Description : ProjectDemoMapperTest
 * @Program : demo_boot
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoBootApplication.class)
public class ProjectDemoMapperTest {

    @Autowired
    private ProjectDemoMapper projectDemoMapper;

    private ProjectDemo projectDemo;

    private Integer selectId;

    private Integer greaterId;

    @Before
    public void setUp() throws Exception {

        selectId = 1;
        //2018-07-10 20:45:11
        greaterId = 4;

        projectDemo = new ProjectDemo();
        projectDemo.setName("Circle");
        projectDemo.setDescription("my favorite live house");
        projectDemo.setTime(new Date());
    }

    @Test
    public void insert() {
        int insert = projectDemoMapper.insert(projectDemo);
        System.out.println("已成功插入" + insert + "条数据");

    }

    @Test
    public void selectByPrimaryKey() {
        ProjectDemo demo = projectDemoMapper.selectByPrimaryKey(selectId);
        System.out.println("id: " + demo.getId() + "  名称: " + demo.getName() + "  描述: " + demo.getDescription());
    }

    @Test
    public void selectByExample() {
        ProjectDemoExample example = new ProjectDemoExample();
        example.setOrderByClause("time desc");
        ProjectDemoExample.Criteria criteria = example.createCriteria();
        criteria.andIdLessThanOrEqualTo(greaterId);
        List<ProjectDemo> list = projectDemoMapper.selectByExample(example);
        for (ProjectDemo demo : list) {
            System.out.println("id: " + demo.getId() + "  名称: " + demo.getName() + "  描述: " + demo.getDescription());
        }
    }


}