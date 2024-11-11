package com.example.dao;

import com.example.entity.JingdianjieshaoInfoComment;
import com.example.vo.JingdianjieshaoInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface JingdianjieshaoInfoCommentDao extends Mapper<JingdianjieshaoInfoComment> {
    List<JingdianjieshaoInfoCommentVo> findAllVo(@Param("name") String name);
    List<JingdianjieshaoInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}
