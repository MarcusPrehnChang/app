package com.javawhizz.App.clothing;
import java.util.List;
public class CombinedData {
    List<Tag> tagList;
    List<String> filter;

    public CombinedData(List<Tag> tags, List<String> filt){
        this.tagList = tags;
        this.filter = filt;
    }

}