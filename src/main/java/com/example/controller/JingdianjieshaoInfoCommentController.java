package com.example.controller;

import com.example.common.Result;
import com.example.entity.JingdianjieshaoInfoComment;
import com.example.vo.JingdianjieshaoInfoCommentVo;
import com.example.service.JingdianjieshaoInfoCommentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/jingdianjieshaoInfoComment")
public class JingdianjieshaoInfoCommentController {
    @Resource
    private JingdianjieshaoInfoCommentService jingdianjieshaoInfoCommentService;

    @PostMapping
    public Result<JingdianjieshaoInfoComment> add(@RequestBody JingdianjieshaoInfoComment commentInfo, HttpServletRequest request) {
        jingdianjieshaoInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        jingdianjieshaoInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody JingdianjieshaoInfoComment commentInfo) {
        jingdianjieshaoInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<JingdianjieshaoInfoComment> detail(@PathVariable Long id) {
        JingdianjieshaoInfoComment commentInfo = jingdianjieshaoInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<JingdianjieshaoInfoCommentVo>> all() {
        return Result.success(jingdianjieshaoInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<JingdianjieshaoInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(jingdianjieshaoInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<JingdianjieshaoInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(jingdianjieshaoInfoCommentService.findByForeignId(id));
    }
}
