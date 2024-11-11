package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.JingdianjieshaoInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.JingdianjieshaoInfoComment;
import com.example.vo.JingdianjieshaoInfoCommentVo;
import com.example.entity.Account;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JingdianjieshaoInfoCommentService {

    @Resource
    private JingdianjieshaoInfoCommentDao jingdianjieshaoInfoCommentDao;

    public JingdianjieshaoInfoComment add(JingdianjieshaoInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        jingdianjieshaoInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        jingdianjieshaoInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(JingdianjieshaoInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        jingdianjieshaoInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public JingdianjieshaoInfoComment findById(Long id) {
        return jingdianjieshaoInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<JingdianjieshaoInfoCommentVo> findAll() {
        return jingdianjieshaoInfoCommentDao.findAllVo(null);
    }

    public PageInfo<JingdianjieshaoInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JingdianjieshaoInfoCommentVo> all = jingdianjieshaoInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<JingdianjieshaoInfoCommentVo> findByForeignId (Long id) {
        List<JingdianjieshaoInfoCommentVo> all = jingdianjieshaoInfoCommentDao.findByForeignId(id, 0L);
        for (JingdianjieshaoInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<JingdianjieshaoInfoCommentVo> children = new ArrayList<>(jingdianjieshaoInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}
