package com.bupt317.study.demo_boot.mapper;

import com.bupt317.study.demo_boot.pojo.mybatis.ProjectDemo;
import com.bupt317.study.demo_boot.pojo.mybatis.ProjectDemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDemoMapper {
    long countByExample(ProjectDemoExample example);

    int deleteByExample(ProjectDemoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectDemo record);

    int insertSelective(ProjectDemo record);

    List<ProjectDemo> selectByExampleWithRowbounds(ProjectDemoExample example, RowBounds rowBounds);

    List<ProjectDemo> selectByExample(ProjectDemoExample example);

    ProjectDemo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectDemo record, @Param("example") ProjectDemoExample example);

    int updateByExample(@Param("record") ProjectDemo record, @Param("example") ProjectDemoExample example);

    int updateByPrimaryKeySelective(ProjectDemo record);

    int updateByPrimaryKey(ProjectDemo record);
}