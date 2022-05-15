package com.example.openevents.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssistEventResponse implements Serializable {

    @SerializedName("fieldCount")
    private int fieldCount;

    @SerializedName("affectedRows")
    private int affectedRows;

    @SerializedName("insertId")
    private int insertId;

    @SerializedName("info")
    private String info;

    @SerializedName("serverStatus")
    private int serverStatus;

    @SerializedName("warningStatus")
    private int warningStatus;


    public int getAffectedRows() {
        return this.affectedRows;
    }
}
