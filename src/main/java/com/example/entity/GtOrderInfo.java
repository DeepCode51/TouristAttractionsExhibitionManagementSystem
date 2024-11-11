package com.example.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "gtorder_info")
public class GtOrderInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "fileIds")
    private String fileIds;
    private Double price;
    private Integer day;
    private Integer num;
    private String daoyou;
    private String jiaotong;
    private String tag;
    private String time;
    @Column(name = "gentuanId")
    private Long gentuanId;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "level")
    private Integer level;
    @Transient
    private List<Long> fileList;
    @Transient
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDaoyou() {
        return daoyou;
    }

    public void setDaoyou(String daoyou) {
        this.daoyou = daoyou;
    }

    public String getJiaotong() {
        return jiaotong;
    }

    public void setJiaotong(String jiaotong) {
        this.jiaotong = jiaotong;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Long> getFileList() {
        return fileList;
    }

    public void setFileList(List<Long> fileList) {
        this.fileList = fileList;
    }

    public Long getGentuanId() {
        return gentuanId;
    }

    public void setGentuanId(Long gentuanId) {
        this.gentuanId = gentuanId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}