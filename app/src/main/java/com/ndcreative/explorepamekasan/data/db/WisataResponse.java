package com.ndcreative.explorepamekasan.data.db;

import java.util.List;

public class WisataResponse {
    private List<WisataItems> data;
    private String status;

    public void setData(List<WisataItems> data) {
        this.data = data;
    }

    public List<WisataItems> getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}