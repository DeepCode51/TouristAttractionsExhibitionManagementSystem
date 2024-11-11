package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.JiudianyudingInfo;
import com.example.vo.JiudianyudingInfoVo;
import com.example.service.JiudianyudingInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/jiudianyudingInfo")
public class JiudianyudingInfoController {
    @Resource
    private JiudianyudingInfoService jiudianyudingInfoService;

    @PostMapping
    public Result add(@RequestBody JiudianyudingInfo info, HttpServletRequest request) {
    // 新增时候只有发布者，有两种用户，管理员和非管理员
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error("1001", "session已失效，请重新登录");
        }
        // 管理员新增，自动发布审核通过
        if (user.getLevel() == 1) {
            info.setPublishStatus("审核通过");
            info.setPublishReason("符合要求");
            info.setPublishId(user.getId());
            info.setPublishVerifyName(user.getName());
        } else {
            // 其他人新增，默认未提交，等待发布人提交
            info.setPublishStatus("未提交");
            info.setPublishId(user.getId());
        }
		info.setLevel(user.getLevel());
		info.setUserId(user.getId());
		info.setUserName(user.getName());

        info.setPublishName(user.getName());
        jiudianyudingInfoService.add(info);
        return Result.success(info);
    }

    /** 发布者或者管理员编辑发布信息后提交 */
    @PutMapping("/submit")
    public Result updateSubmit(@RequestBody JiudianyudingInfo info, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error("1001", "session已失效，请重新登录");
        }

        if (user.getLevel() == 1) {
            info.setPublishStatus("审核通过");
            info.setPublishReason("符合要求");
            info.setPublishId(0L);
            info.setPublishVerifyName(user.getName());
        } else {
            info.setPublishStatus("未提交");
            info.setPublishVerifyName("");
            info.setPublishReason("");
        }
        jiudianyudingInfoService.update(info);
        return Result.success();
    }

    /** 更新审核内容，用户管理员审核按钮 */
    @PutMapping("/verify")
    public Result updateReserve(@RequestBody JiudianyudingInfo info, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error("1001", "session已失效，请重新登录");
        }
        info.setPublishVerifyName(user.getName());
        jiudianyudingInfoService.update(info);

        // 更新一下父节点
        info.setId(info.getParentId());
        info.setParentId(0L);
        jiudianyudingInfoService.update(info);
        return Result.success();
    }

    /**
    * 发布提交审核，专门处理发布
    */
    @PostMapping("/submit")
    public Result submit(@RequestBody JiudianyudingInfo info, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error("1001", "session已失效，请重新登录");
        }
        if (1 == user.getLevel()) {
            // 如果是管理员发布审核，直接审核通过
            info.setPublishStatus("审核通过");
            info.setPublishVerifyName(user.getName());
            info.setPublishReason("管理员发布自动审核通");
        } else {
            info.setPublishStatus("待审核");
            info.setPublishId(user.getId());
            info.setPublishVerifyName("");
            info.setPublishReason("");
        }
        info.setPublishName(user.getName());
        jiudianyudingInfoService.update(info);
        return Result.success();
    }

    /**
    * 预约提交审核，专门处理预约
    */
    @PostMapping("/reserve")
    public Result reserve(@RequestBody JiudianyudingInfo info, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error("1001", "session已失效，请重新登录");
        }

        JiudianyudingInfo reserveInfo = jiudianyudingInfoService.findByReserveId(user.getId(), info.getId());
        if (reserveInfo != null) {
            return Result.error("1001", "请不要重复点击，耐心等待管理员审核，您可以在后台查看审核结果");
        }
        // 复制一份
        JiudianyudingInfo copyInfo = new JiudianyudingInfo();
        BeanUtils.copyProperties(info, copyInfo);
        copyInfo.setParentId(info.getId());
        copyInfo.setReserveStatus("待审核");
        copyInfo.setReserveReason("");
        copyInfo.setReserveName(user.getName());
        copyInfo.setReserveId(user.getId());
        copyInfo.setFileId(info.getFileId());
        copyInfo.setFileName(info.getFileName());
        copyInfo.setId(null);
        jiudianyudingInfoService.add(copyInfo);

        // 将原记录加个状态
        info.setReserveStatus("待审核");
        info.setReserveId(user.getId());
        info.setReserveName(user.getName());
        info.setFileId(null);
        info.setFileName(null);
        jiudianyudingInfoService.update(info);
        return Result.success();
    }

    @DeleteMapping("/publish/{id}")
    public Result deletePublish(@PathVariable Long id, HttpServletRequest request) {
        JiudianyudingInfo dbInfo = jiudianyudingInfoService.findById(id);
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error("1001", "session已失效，请重新登录");
        }
        if (user.getLevel() != 1) {
            // 非管理员只能删除自己的
            if (!user.getId().equals(dbInfo.getPublishId()) || !user.getName().equals(dbInfo.getPublishName())) {
                return Result.error("1001", "只能删除自己发布的信息");
            }
        }
        jiudianyudingInfoService.delete(id);
        return Result.success();
    }

    @DeleteMapping("/reserve/{id}")
    public Result deleteReserve(@PathVariable Long id, HttpServletRequest request) {
        JiudianyudingInfo dbInfo = jiudianyudingInfoService.findById(id);
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error("1001", "session已失效，请重新登录");
        }
        if (user.getLevel() != 1) {
            // 非管理员只能删除自己的
            if (!user.getId().equals(dbInfo.getReserveId()) || !user.getName().equals(dbInfo.getReserveName())) {
                return Result.error("1001", "只能删除自己预约的信息");
            }
        }
        jiudianyudingInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<JiudianyudingInfo> detail(@PathVariable Long id) {
        JiudianyudingInfo info = jiudianyudingInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<JiudianyudingInfoVo>> all() {
        return Result.success(jiudianyudingInfoService.findAll());
    }

    @GetMapping("/page/{flag}")
    public Result<PageInfo<JiudianyudingInfoVo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                                      @PathVariable Integer flag,
                                                      HttpServletRequest request) {
        return Result.success(jiudianyudingInfoService.findPage(pageNum, pageSize, flag, request));
    }

    @GetMapping("/page")
    public Result<PageInfo<JiudianyudingInfoVo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(jiudianyudingInfoService.findPage(pageNum, pageSize));
    }

}
