package com.tomlockapps.userbrowser.data.source.remote.dailymotion.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tomlo on 26.10.2016.
 */

public class DailyMotionResponse {
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("limit")
    @Expose
    public Integer limit;
    @SerializedName("explicit")
    @Expose
    public Boolean explicit;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("has_more")
    @Expose
    public Boolean hasMore;
    @SerializedName("list")
    @Expose
    public java.util.List<DailyMotionUserModel> list = new ArrayList<>();
}
