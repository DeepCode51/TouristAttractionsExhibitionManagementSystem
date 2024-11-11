package com.example.controller;

import com.example.common.Result;
import com.example.entity.JiudianyudingInfoComment;
import com.example.vo.JiudianyudingInfoCommentVo;
import com.example.service.JiudianyudingInfoCommentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/jiudianyudingInfoComment")
public class JiudianyudingInfoCommentController {
    @Resource
    private JiudianyudingInfoCommentService jiudianyudingInfoCommentService;

    @PostMapping
    public Result<JiudianyudingInfoComment> add(@RequestBody JiudianyudingInfoComment commentInfo, HttpServletRequest request) {
        jiudianyudingInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        jiudianyudingInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody JiudianyudingInfoComment commentInfo) {
        jiudianyudingInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<JiudianyudingInfoComment> detail(@PathVariable Long id) {
        JiudianyudingInfoComment commentInfo = jiudianyudingInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<JiudianyudingInfoCommentVo>> all() {
        return Result.success(jiudianyudingInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<JiudianyudingInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(jiudianyudingInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<JiudianyudingInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(jiudianyudingInfoCommentService.findByForeignId(id));
    }
}
