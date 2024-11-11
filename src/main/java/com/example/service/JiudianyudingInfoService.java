package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.JiudianyudingInfoDao;
import com.example.entity.Account;
import com.example.entity.JiudianyudingInfo;
import com.example.vo.JiudianyudingInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class JiudianyudingInfoService {

    @Resource
    private JiudianyudingInfoDao jiudianyudingInfoDao;

    public JiudianyudingInfo add(JiudianyudingInfo info) {
    jiudianyudingInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        jiudianyudingInfoDao.deleteByPrimaryKey(id);
    }

    public void update(JiudianyudingInfo info) {
        jiudianyudingInfoDao.updateByPrimaryKeySelective(info);
    }

    public JiudianyudingInfo findById(Long id) {
        return jiudianyudingInfoDao.selectByPrimaryKey(id);
    }

    public JiudianyudingInfo findByReserveId(Long id, Long parentId) {
        List<JiudianyudingInfoVo> list = jiudianyudingInfoDao.findByReserveId(id, parentId);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public PageInfo<JiudianyudingInfoVo> findPage(Integer pageNum, Integer pageSize, Integer flag, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (account == null) {
            return PageInfo.of(new ArrayList<>());
        }
        Integer level = account.getLevel();
        PageHelper.startPage(pageNum, pageSize);
        List<JiudianyudingInfoVo> info;
        if (1 == flag) {
            info = (1 == level) ? jiudianyudingInfoDao.findByPublishId(0L) : jiudianyudingInfoDao.findByPublishId(account.getId());
        } else {
            info = (1 == level) ? jiudianyudingInfoDao.findByReserveId(0L, null) : jiudianyudingInfoDao.findByReserveId(account.getId(), null);
        }
        return PageInfo.of(info);
    }

    public PageInfo<JiudianyudingInfoVo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JiudianyudingInfoVo> info = jiudianyudingInfoDao.findAllReserve();
        return PageInfo.of(info);
    }

    public List<JiudianyudingInfoVo> findAll() {
        return jiudianyudingInfoDao.findAllReserve();
    }
}
