package com.xing.recyclerviewsample;

/**
 * Created by Administrator on 2017/8/20.
 */

public class Bean {

    private String url;

    private String title;

    public Bean() {

    }

    public Bean(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    @Override
    public String toString() {
        return "Bean{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
