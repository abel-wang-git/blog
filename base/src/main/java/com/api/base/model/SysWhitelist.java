package com.api.base.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_whitelist")
public class SysWhitelist implements Serializable {
    @Id
    private String url;

    private static final long serialVersionUID = 1L;

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}