package com.bill.springbootproject.domain;


import java.io.Serializable;

/**
 * 视频章实体类
 */
public class Chapter implements Serializable {

  private long id;
  private long videoId;
  private String title;
  private long ordered;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getVideoId() {
    return videoId;
  }

  public void setVideoId(long videoId) {
    this.videoId = videoId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public long getOrdered() {
    return ordered;
  }

  public void setOrdered(long ordered) {
    this.ordered = ordered;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
