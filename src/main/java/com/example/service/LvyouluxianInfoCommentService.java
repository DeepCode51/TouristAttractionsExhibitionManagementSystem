package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.LvyouluxianInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.LvyouluxianInfoComment;
import com.example.vo.LvyouluxianInfoCommentVo;
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
public class LvyouluxianInfoCommentService {

    @Resource
    private LvyouluxianInfoCommentDao lvyouluxianInfoCommentDao;

    public LvyouluxianInfoComment add(LvyouluxianInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        lvyouluxianInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        lvyouluxianInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(LvyouluxianInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        lvyouluxianInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public LvyouluxianInfoComment findById(Long id) {
        return lvyouluxianInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<LvyouluxianInfoCommentVo> findAll() {
        return lvyouluxianInfoCommentDao.findAllVo(null);
    }

    public PageInfo<LvyouluxianInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LvyouluxianInfoCommentVo> all = lvyouluxianInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<LvyouluxianInfoCommentVo> findByForeignId (Long id) {
        List<LvyouluxianInfoCommentVo> all = lvyouluxianInfoCommentDao.findByForeignId(id, 0L);
        for (LvyouluxianInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<LvyouluxianInfoCommentVo> children = new ArrayList<>(lvyouluxianInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}
