package com.bill.springbootproject.domain;


import java.io.Serializable;

/**
 * 视频集实体类
 */
public class Episode implements Serializable {

  private long id;
  private String title;
  private long num;
  private String duration;
  private String coverImg;
  private long videoId;
  private String summary;
  private java.sql.Timestamp createTime;
  private long chapterId;


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


  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }


  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }


  public String getCoverImg() {
    return coverImg;
  }

  public void setCoverImg(String coverImg) {
    this.coverImg = coverImg;
  }


  public long getVideoId() {
    return videoId;
  }

  public void setVideoId(long videoId) {
    this.videoId = videoId;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getChapterId() {
    return chapterId;
  }

  public void setChapterId(long chapterId) {
    this.chapterId = chapterId;
  }

}
