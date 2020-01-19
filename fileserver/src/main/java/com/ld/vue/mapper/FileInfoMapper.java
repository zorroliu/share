package com.ld.vue.mapper;

import com.ld.vue.pojo.FileInfo;
import com.ld.vue.pojo.FileInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoMapper {
    long countByExample(FileInfoExample example);

    int deleteByExample(FileInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    List<FileInfo> selectByExample(FileInfoExample example);

    FileInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByExample(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    List<String> selectEnableCode();

    List<String> selectAllCode();
}