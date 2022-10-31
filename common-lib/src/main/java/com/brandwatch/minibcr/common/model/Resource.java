package com.brandwatch.minibcr.common.model;

public class Resource {

  private String title;
  private String text;

  public Resource() {
  }

  public Resource(String title, String text) {
    this.title = title;
    this.text = text;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }
}
