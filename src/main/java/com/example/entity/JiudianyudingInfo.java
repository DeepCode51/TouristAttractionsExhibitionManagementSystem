package com.example.entity;

import javax.persistence.*;

@Table(name = "jiudianyuding_info")
public class JiudianyudingInfo  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "fangjia")
	private String fangjia;
	@Column(name = "time")
	private String time;
	@Column(name = "publishName")
	private String publishName;
	@Column(name = "publishId")
	private Long publishId;
	@Column(name = "publishStatus")
	private String publishStatus;
	@Column(name = "publishReason")
	private String publishReason;
	@Column(name = "publishVerifyName")
	private String publishVerifyName;
	@Column(name = "reserveName")
	private String reserveName;
	@Column(name = "reserveId")
	private Long reserveId;
	@Column(name = "reserveStatus")
	private String reserveStatus;
	@Column(name = "reserveReason")
	private String reserveReason;
	@Column(name = "reserveVerifyName")
	private String reserveVerifyName;
	@Column(name = "fileName")
	private String fileName;
	@Column(name = "fileId")
	private Long fileId;
	@Column(name = "userName")
	private String userName;
	@Column(name = "level")
	private Integer level;
	@Column(name = "userId")
	private Long userId;
	@Column(name = "parentId")
	private Long parentId;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFangjia() {
		return fangjia;
	}
	public void setFangjia(String fangjia) {
		this.fangjia = fangjia;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPublishName() {
		return publishName;
	}
	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}
	public Long getPublishId() {
		return publishId;
	}
	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}
	public String getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getPublishReason() {
		return publishReason;
	}
	public void setPublishReason(String publishReason) {
		this.publishReason = publishReason;
	}
	public String getPublishVerifyName() {
		return publishVerifyName;
	}
	public void setPublishVerifyName(String publishVerifyName) {
		this.publishVerifyName = publishVerifyName;
	}
	public String getReserveName() {
		return reserveName;
	}
	public void setReserveName(String reserveName) {
		this.reserveName = reserveName;
	}
	public Long getReserveId() {
		return reserveId;
	}
	public void setReserveId(Long reserveId) {
		this.reserveId = reserveId;
	}
	public String getReserveStatus() {
		return reserveStatus;
	}
	public void setReserveStatus(String reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	public String getReserveReason() {
		return reserveReason;
	}
	public void setReserveReason(String reserveReason) {
		this.reserveReason = reserveReason;
	}
	public String getReserveVerifyName() {
		return reserveVerifyName;
	}
	public void setReserveVerifyName(String reserveVerifyName) {
		this.reserveVerifyName = reserveVerifyName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

}
