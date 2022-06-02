
package com.moringaschool.myapplication.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moringaschool.myapplication.models.Business;
import com.moringaschool.myapplication.models.Region;

import org.parceler.Parcel;

@Parcel
public class YelpApiBusinessSearchResponse {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("businesses")
    @Expose
    private List<Business> businesses = null;
    @SerializedName("region")
    @Expose
    private Region region;

    /**
     * No args constructor for use in serialization
     * 
     */
    public YelpApiBusinessSearchResponse() {
    }

    /**
     * 
     * @param total
     * @param region
     * @param businesses
     */
    public YelpApiBusinessSearchResponse(Integer total, List<Business> businesses, Region region) {
        super();
        this.total = total;
        this.businesses = businesses;
        this.region = region;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
