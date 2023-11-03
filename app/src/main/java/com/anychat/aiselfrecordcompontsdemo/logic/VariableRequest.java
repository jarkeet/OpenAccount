package com.anychat.aiselfrecordcompontsdemo.logic;

import java.util.List;

public class VariableRequest {

    /**
     * appId : 00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1
     * idList : [55,206]
     * sceneType : 1
     */

    private String appId;
    private int sceneType;
    private List<Integer> idList;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getSceneType() {
        return sceneType;
    }

    public void setSceneType(int sceneType) {
        this.sceneType = sceneType;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
