package com.brandwatch.minibcr.common.model;

public class Resource {

  private final String title;
  private final String text;

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
