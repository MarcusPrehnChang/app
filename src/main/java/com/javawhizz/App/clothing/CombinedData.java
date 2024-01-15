package com.javawhizz.App.clothing;
import java.util.List;
public class CombinedData {
    private List<Tag> tagList;
    private List<String> filter;

    public CombinedData() {
        // default constructor
    }

    public CombinedData(List<Tag> tags, List<String> filt) {
        this.tagList = tags;
        this.filter = filt;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }
}
