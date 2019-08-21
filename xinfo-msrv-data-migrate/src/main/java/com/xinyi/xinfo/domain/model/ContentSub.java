package com.xinyi.xinfo.domain.model;

import java.util.Map;

public class ContentSub {

    private Map<String,Object> reader ;
    private Map<String,Object> writer ;

    public Map<String, Object> getReader() {
        return reader;
    }

    public void setReader(Map<String, Object> reader) {
        this.reader = reader;
    }

    public Map<String, Object> getWriter() {
        return writer;
    }

    public void setWriter(Map<String, Object> writer) {
        this.writer = writer;
    }
}
