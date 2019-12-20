package com.bill.springbootproject.domain;

import java.io.Serializable;

/**
 *
 * 订单表
 */
public class VideoOrder implements Serializable {

  private long id;
  private String openid;
  private String outTradeNo;
  private long state;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp notifyTime;
  private long totalFee;
  private String nickname;
  private String headImg;
  private long videoId;
  private String videoTitle;
  private String videoImg;
  private long userId;
  private String ip;
  private long del;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }


  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getNotifyTime() {
    return notifyTime;
  }

  public void setNotifyTime(java.sql.Timestamp notifyTime) {
    this.notifyTime = notifyTime;
  }


  public long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(long totalFee) {
    this.totalFee = totalFee;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public long getVideoId() {
    return videoId;
  }

  public void setVideoId(long videoId) {
    this.videoId = videoId;
  }


  public String getVideoTitle() {
    return videoTitle;
  }

  public void setVideoTitle(String videoTitle) {
    this.videoTitle = videoTitle;
  }


  public String getVideoImg() {
    return videoImg;
  }

  public void setVideoImg(String videoImg) {
    this.videoImg = videoImg;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public long getDel() {
    return del;
  }

  public void setDel(long del) {
    this.del = del;
  }

}
