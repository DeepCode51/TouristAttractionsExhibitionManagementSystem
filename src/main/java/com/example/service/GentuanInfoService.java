package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.dao.GentuanInfoDao;
import com.example.entity.GentuanInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GentuanInfoService {

    @Resource
    private GentuanInfoDao gentuanInfoDao;

    public GentuanInfo add(GentuanInfo gentuanInfo) {
        List<Long> fileList = gentuanInfo.getFileList();
        if (!CollectionUtil.isEmpty(fileList)) {
            gentuanInfo.setFileIds(fileList.toString());
        }
        gentuanInfoDao.insertSelective(gentuanInfo);
        return gentuanInfo;
    }

    public void delete(Long id) {
        gentuanInfoDao.deleteByPrimaryKey(id);
    }

    public void update(GentuanInfo gentuanInfo) {
        List<Long> fileList = gentuanInfo.getFileList();
        if (!CollectionUtil.isEmpty(fileList)) {
            gentuanInfo.setFileIds(fileList.toString());
        }
        gentuanInfoDao.updateByPrimaryKeySelective(gentuanInfo);
    }

    public GentuanInfo findById(Long id) {
        return gentuanInfoDao.selectByPrimaryKey(id);
    }

    public List<GentuanInfo> findAll() {
        return gentuanInfoDao.selectAll();
    }

    public PageInfo<GentuanInfo> findPage(Integer pageNum, Integer pageSize, String name, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<GentuanInfo> all;
        if (ObjectUtil.isEmpty(name) || "all".equals(name)) {
            all = gentuanInfoDao.selectAll();
        } else {
            all = gentuanInfoDao.findByText(name);
        }
        return PageInfo.of(all);
    }



    public List<GentuanInfo> searchGoods(String text) {
        return gentuanInfoDao.findByText(text);
    }

}
