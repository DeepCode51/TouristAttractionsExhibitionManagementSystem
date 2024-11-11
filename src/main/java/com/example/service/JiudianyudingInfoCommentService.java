package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.JiudianyudingInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.JiudianyudingInfoComment;
import com.example.vo.JiudianyudingInfoCommentVo;
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
public class JiudianyudingInfoCommentService {

    @Resource
    private JiudianyudingInfoCommentDao jiudianyudingInfoCommentDao;

    public JiudianyudingInfoComment add(JiudianyudingInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        jiudianyudingInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        jiudianyudingInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(JiudianyudingInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        jiudianyudingInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public JiudianyudingInfoComment findById(Long id) {
        return jiudianyudingInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<JiudianyudingInfoCommentVo> findAll() {
        return jiudianyudingInfoCommentDao.findAllVo(null);
    }

    public PageInfo<JiudianyudingInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JiudianyudingInfoCommentVo> all = jiudianyudingInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<JiudianyudingInfoCommentVo> findByForeignId (Long id) {
        List<JiudianyudingInfoCommentVo> all = jiudianyudingInfoCommentDao.findByForeignId(id, 0L);
        for (JiudianyudingInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<JiudianyudingInfoCommentVo> children = new ArrayList<>(jiudianyudingInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}
