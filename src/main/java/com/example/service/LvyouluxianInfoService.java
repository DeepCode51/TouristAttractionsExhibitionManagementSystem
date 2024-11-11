package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.LvyouluxianInfoDao;
import com.example.entity.Account;
import com.example.entity.LvyouluxianInfo;
import com.example.vo.LvyouluxianInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class LvyouluxianInfoService {

    @Resource
    private LvyouluxianInfoDao lvyouluxianInfoDao;

    public LvyouluxianInfo add(LvyouluxianInfo info) {
    lvyouluxianInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        lvyouluxianInfoDao.deleteByPrimaryKey(id);
    }

    public void update(LvyouluxianInfo info) {
        lvyouluxianInfoDao.updateByPrimaryKeySelective(info);
    }

    public LvyouluxianInfo findById(Long id) {
        return lvyouluxianInfoDao.selectByPrimaryKey(id);
    }

    public LvyouluxianInfo findByReserveId(Long id, Long parentId) {
        List<LvyouluxianInfoVo> list = lvyouluxianInfoDao.findByReserveId(id, parentId);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public PageInfo<LvyouluxianInfoVo> findPage(Integer pageNum, Integer pageSize, Integer flag, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (account == null) {
            return PageInfo.of(new ArrayList<>());
        }
        Integer level = account.getLevel();
        PageHelper.startPage(pageNum, pageSize);
        List<LvyouluxianInfoVo> info;
        if (1 == flag) {
            info = (1 == level) ? lvyouluxianInfoDao.findByPublishId(0L) : lvyouluxianInfoDao.findByPublishId(account.getId());
        } else {
            info = (1 == level) ? lvyouluxianInfoDao.findByReserveId(0L, null) : lvyouluxianInfoDao.findByReserveId(account.getId(), null);
        }
        return PageInfo.of(info);
    }

    public PageInfo<LvyouluxianInfoVo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LvyouluxianInfoVo> info = lvyouluxianInfoDao.findAllReserve();
        return PageInfo.of(info);
    }

    public List<LvyouluxianInfoVo> findAll() {
        return lvyouluxianInfoDao.findAllReserve();
    }
}
