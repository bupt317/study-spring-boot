package com.bupt317.study.demo_boot.config;

import com.bupt317.study.demo_boot.DemoBootApplication;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Create by 菲尼莫斯 on 2018/7/12 19:09
 *
 * @Description : DataSourceConfigTest
 * @Program : demo_boot
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoBootApplication.class)
public class DataSourceConfigTest {

    @Autowired
    private DataSource dataSource;

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Test
    public void dataSource() throws SQLException {
        connection = dataSource.getConnection();
        preparedStatement = connection.prepareStatement("select * from project_demo");
        //preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("共有数据："+resultSet.getMetaData().getColumnCount()+" 条");

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            System.out.println("id: " + id + "  名称: " + name + "  描述: " + description);
        }

        resultSet.close();

    }

    @After
    public void tearDown() throws Exception {
        if(connection!=null){
            connection.close();
        }
        if (preparedStatement!=null){
            preparedStatement.close();
        }
    }
}