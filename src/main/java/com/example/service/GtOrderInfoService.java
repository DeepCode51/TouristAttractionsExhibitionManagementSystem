package com.example.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.common.ResultCode;
import com.example.dao.OrderGoodsRelDao;
import com.example.dao.GtOrderInfoDao;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GtOrderInfoService {

    @Resource
    private GtOrderInfoDao gtOrderInfoDao;
    @Resource
	private AdminInfoService adminInfoService;
	@Resource
	private UserInfoService userInfoService;


    /**
     * 分页查询订单信息
     */
    public PageInfo<GtOrderInfo> findEndPages(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<GtOrderInfo> gtOrderInfos = gtOrderInfoDao.selectAll();
        for (GtOrderInfo gtOrderInfo : gtOrderInfos) {
            Account account = getUserInfo(gtOrderInfo.getUserId(), gtOrderInfo.getLevel());
            gtOrderInfo.setUserName(account.getName());
        }
        return PageInfo.of(gtOrderInfos);
    }



    public void delete(Long id) {
        gtOrderInfoDao.deleteByPrimaryKey(id);
    }

    private Account getUserInfo(Long userId, Integer level) {
        Account account = new Account();
		if (level == 1) {
			account = adminInfoService.findById(userId);
		}
		if (level == 3) {
			account = userInfoService.findById(userId);
		}

        return account;
    }
}
