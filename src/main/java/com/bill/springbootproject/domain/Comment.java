package com.bill.springbootproject.domain;


import java.io.Serializable;

/**
 * 评论实体类
 */
public class Comment implements Serializable {

  private long id;
  private String content;
  private long userId;
  private String headImg;
  private String name;
  private double point;
  private long up;
  private java.sql.Timestamp createTime;
  private long orderId;
  private long videoId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public double getPoint() {
    return point;
  }

  public void setPoint(double point) {
    this.point = point;
  }


  public long getUp() {
    return up;
  }

  public void setUp(long up) {
    this.up = up;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getVideoId() {
    return videoId;
  }

  public void setVideoId(long videoId) {
    this.videoId = videoId;
  }

}
