package com.bill.springbootproject.domain;


import java.io.Serializable;

/**
 * 视频表
 */
public class Video implements Serializable {

  private Long id;
  private String title;
  private String summary;
  private String coverImg;
  private Long viewNum;
  private Double price;
  private java.sql.Timestamp createTime;
  private Long online;
  private Double point;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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


  public Long getViewNum() {
    return viewNum;
  }

  public void setViewNum(Long viewNum) {
    this.viewNum = viewNum;
  }


  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public Long getOnline() {
    return online;
  }

  public void setOnline(Long online) {
    this.online = online;
  }


  public Double getPoint() {
    return point;
  }

  public void setPoint(Double point) {
    this.point = point;
  }

}
