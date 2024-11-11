package com.example.controller;

import com.example.common.Result;
import com.example.entity.LvyouluxianInfoComment;
import com.example.vo.LvyouluxianInfoCommentVo;
import com.example.service.LvyouluxianInfoCommentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/lvyouluxianInfoComment")
public class LvyouluxianInfoCommentController {
    @Resource
    private LvyouluxianInfoCommentService lvyouluxianInfoCommentService;

    @PostMapping
    public Result<LvyouluxianInfoComment> add(@RequestBody LvyouluxianInfoComment commentInfo, HttpServletRequest request) {
        lvyouluxianInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        lvyouluxianInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody LvyouluxianInfoComment commentInfo) {
        lvyouluxianInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<LvyouluxianInfoComment> detail(@PathVariable Long id) {
        LvyouluxianInfoComment commentInfo = lvyouluxianInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<LvyouluxianInfoCommentVo>> all() {
        return Result.success(lvyouluxianInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<LvyouluxianInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(lvyouluxianInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<LvyouluxianInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(lvyouluxianInfoCommentService.findByForeignId(id));
    }
}
