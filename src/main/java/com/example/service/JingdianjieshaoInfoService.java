package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.JingdianjieshaoInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.JingdianjieshaoInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.JingdianjieshaoInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class JingdianjieshaoInfoService {

    @Resource
    private JingdianjieshaoInfoDao jingdianjieshaoInfoDao;

    public JingdianjieshaoInfo add(JingdianjieshaoInfo info) {
        jingdianjieshaoInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        jingdianjieshaoInfoDao.deleteByPrimaryKey(id);
    }

    public void update(JingdianjieshaoInfo info) {
        jingdianjieshaoInfoDao.updateByPrimaryKeySelective(info);
    }

    public JingdianjieshaoInfo findById(Long id) {
        return jingdianjieshaoInfoDao.selectByPrimaryKey(id);
    }

    public List<JingdianjieshaoInfoVo> findAll() {
        return jingdianjieshaoInfoDao.findByName("all");
    }

    public PageInfo<JingdianjieshaoInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<JingdianjieshaoInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<JingdianjieshaoInfoVo> findAllPage(HttpServletRequest request, String name) {
		return jingdianjieshaoInfoDao.findByName(name);
    }

}
