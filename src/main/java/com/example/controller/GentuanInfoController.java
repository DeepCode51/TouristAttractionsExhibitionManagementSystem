package com.example.controller;

import com.example.common.Result;
import com.example.dao.GentuanInfoDao;
import com.example.dao.GtOrderInfoDao;
import com.example.entity.Account;
import com.example.entity.GentuanInfo;
import com.example.entity.GtOrderInfo;
import com.example.exception.CustomException;
import com.example.service.GentuanInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/gentuanInfo")
public class GentuanInfoController {
    @Resource
    private GentuanInfoService gentuanInfoService;
    @Resource
    private GtOrderInfoDao gtOrderInfoDao;

    @PostMapping
    public Result<GentuanInfo> add(@RequestBody GentuanInfo gentuanInfo, HttpServletRequest request) {
        gentuanInfoService.add(gentuanInfo);
        return Result.success(gentuanInfo);
    }

    @PostMapping("/gentuan")
    public Result<GtOrderInfo> gentuan(@RequestBody GtOrderInfo orderInfo, HttpServletRequest request) {
        GentuanInfo gentuanInfo = gentuanInfoService.findById(orderInfo.getId());
        if (gentuanInfo.getNum() - gentuanInfo.getGentuan() < orderInfo.getNum()) {
            throw new CustomException("-1", "您输入的跟团人数过大");
        }
        orderInfo.setId(null);
        gtOrderInfoDao.insertSelective(orderInfo);

        gentuanInfo.setGentuan(gentuanInfo.getGentuan() + orderInfo.getNum());
        gentuanInfoService.update(gentuanInfo);
        return Result.success(orderInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        gentuanInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody GentuanInfo gentuanInfo) {
        gentuanInfoService.update(gentuanInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<GentuanInfo> detail(@PathVariable Long id) {
        GentuanInfo gentuanInfo = gentuanInfoService.findById(id);
        return Result.success(gentuanInfo);
    }

    @GetMapping
    public Result<List<GentuanInfo>> all() {
        return Result.success(gentuanInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<GentuanInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @PathVariable String name,
                                            HttpServletRequest request) {
        return Result.success(gentuanInfoService.findPage(pageNum, pageSize, name, request));
    }



    @GetMapping("/searchGoods")
    public Result<List<GentuanInfo>> searchGoods(@RequestParam String text) {
        return Result.success(gentuanInfoService.searchGoods(text));
    }
}
