package com.example.controller;

import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.GoodsInfo;
import com.example.entity.GtOrderInfo;
import com.example.exception.CustomException;
import com.example.service.GtOrderInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/gtOrderInfo")
public class GtOrderInfoController {
    @Resource
    private GtOrderInfoService gtOrderInfoService;


    /**
     * 查询所有信息（分页）
     */
    @GetMapping("/page")
    public Result<PageInfo<GtOrderInfo>> findPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(gtOrderInfoService.findEndPages(pageNum, pageSize));
    }
    

    /**
     * 删除历史订单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        gtOrderInfoService.delete(id);
        return Result.success();
    }
}
