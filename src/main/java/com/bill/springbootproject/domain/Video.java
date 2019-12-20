package com.bill.springbootproject.domain;


import java.io.Serializable;

/**
 * 视频表
 */
public class Video implements Serializable {

  private long id;
  private String title;
  private String summary;
  private String coverImg;
  private long viewNum;
  private long price;
  private java.sql.Timestamp createTime;
  private long online;
  private double point;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }


  public long getViewNum() {
    return viewNum;
  }

  public void setViewNum(long viewNum) {
    this.viewNum = viewNum;
  }


  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getOnline() {
    return online;
  }

  public void setOnline(long online) {
    this.online = online;
  }


  public double getPoint() {
    return point;
  }

  public void setPoint(double point) {
    this.point = point;
  }

}
