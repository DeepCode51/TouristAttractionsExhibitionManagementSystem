package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.JingdianjieshaoInfo;
import com.example.service.*;
import com.example.vo.JingdianjieshaoInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/jingdianjieshaoInfo")
public class JingdianjieshaoInfoController {
    @Resource
    private JingdianjieshaoInfoService jingdianjieshaoInfoService;

    @PostMapping
    public Result<JingdianjieshaoInfo> add(@RequestBody JingdianjieshaoInfoVo info) {
        jingdianjieshaoInfoService.add(info);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        jingdianjieshaoInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody JingdianjieshaoInfoVo info) {
        jingdianjieshaoInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<JingdianjieshaoInfo> detail(@PathVariable Long id) {
        JingdianjieshaoInfo info = jingdianjieshaoInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<JingdianjieshaoInfoVo>> all() {
        return Result.success(jingdianjieshaoInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<JingdianjieshaoInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(jingdianjieshaoInfoService.findPage(name, pageNum, pageSize, request));
    }
}
