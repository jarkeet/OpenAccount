package com.anychat.aiselfrecordcompontsdemo.logic;

import java.util.List;

public class VariableResponse {

    /**
     * msg : null
     * content : {"return_code":"SUCCESS","return_msg":"","errcode":0,"msg":"操作成功","pageNo":null,"pageSize":null,"totalPages":null,"totalElements":null,"list":[{"appId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","code":"promoterName","codeName":"营销人员名称","createdTime":"2020-11-30 18:45:28","describe":"营销人员名称","enable":1,"id":14,"offset":0,"pageSize":10,"qualityVariableExList":[],"sceneType":1,"type":1},{"appId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","code":"promoterBusDeptName","codeName":"营业厅网点","createdTime":"2020-11-30 18:50:39","describe":"营业厅网点","enable":1,"id":16,"offset":0,"pageSize":10,"qualityVariableExList":[],"sceneType":1,"type":1},{"appId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","code":"custName","codeName":"客户名称","createdTime":"2020-11-30 18:46:06","describe":"客户名称","enable":1,"id":15,"offset":0,"pageSize":10,"qualityVariableExList":[],"sceneType":1,"type":1},{"appId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","code":"promoterID","codeName":"工号","createdTime":"2020-11-30 18:52:06","describe":"工号","enable":1,"id":17,"offset":0,"pageSize":10,"qualityVariableExList":[],"sceneType":1,"type":1}]}
     * errorcode : 0
     */

    private Object msg;
    /**
     * return_code : SUCCESS
     * return_msg :
     * errcode : 0
     * msg : 操作成功
     * pageNo : null
     * pageSize : null
     * totalPages : null
     * totalElements : null
     * list : [{"appId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","code":"promoterName","codeName":"营销人员名称","createdTime":"2020-11-30 18:45:28","describe":"营销人员名称","enable":1,"id":14,"offset":0,"pageSize":10,"qualityVariableExList":[],"sceneType":1,"type":1},{"appId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","code":"promoterBusDeptName","codeName":"营业厅网点","createdTime":"2020-11-30 18:50:39","describe":"营业厅网点","enable":1,"id":16,"offset":0,"pageSize":10,"qualityVariableExList":[],"sceneType":1,"type":1},{"appId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","code":"custName","codeName":"客户名称","createdTime":"2020-11-30 18:46:06","describe":"客户名称","enable":1,"id":15,"offset":0,"pageSize":10,"qualityVariableExList":[],"sceneType":1,"type":1},{"appId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","code":"promoterID","codeName":"工号","createdTime":"2020-11-30 18:52:06","describe":"工号","enable":1,"id":17,"offset":0,"pageSize":10,"qualityVariableExList":[],"sceneType":1,"type":1}]
     */

    private ContentBean content;
    private int errorcode;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public static class ContentBean {
        private String return_code;
        private String return_msg;
        private int errcode;
        private String msg;
        private Object pageNo;
        private Object pageSize;
        private Object totalPages;
        private Object totalElements;
        /**
         * appId : 00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1
         * code : promoterName
         * codeName : 营销人员名称
         * createdTime : 2020-11-30 18:45:28
         * describe : 营销人员名称
         * enable : 1
         * id : 14
         * offset : 0
         * pageSize : 10
         * qualityVariableExList : []
         * sceneType : 1
         * type : 1
         */

        private List<ListBean> list;

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public int getErrcode() {
            return errcode;
        }

        public void setErrcode(int errcode) {
            this.errcode = errcode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getPageNo() {
            return pageNo;
        }

        public void setPageNo(Object pageNo) {
            this.pageNo = pageNo;
        }

        public Object getPageSize() {
            return pageSize;
        }

        public void setPageSize(Object pageSize) {
            this.pageSize = pageSize;
        }

        public Object getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Object totalPages) {
            this.totalPages = totalPages;
        }

        public Object getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(Object totalElements) {
            this.totalElements = totalElements;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String appId;
            private String code;
            private String codeName;
            private String createdTime;
            private String describe;
            private int enable;
            private int id;
            private int offset;
            private int pageSize;
            private int sceneType;
            private int type;
            private List<?> qualityVariableExList;

            public String getAppId() {
                return appId;
            }

            public void setAppId(String appId) {
                this.appId = appId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCodeName() {
                return codeName;
            }

            public void setCodeName(String codeName) {
                this.codeName = codeName;
            }

            public String getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(String createdTime) {
                this.createdTime = createdTime;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getSceneType() {
                return sceneType;
            }

            public void setSceneType(int sceneType) {
                this.sceneType = sceneType;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<?> getQualityVariableExList() {
                return qualityVariableExList;
            }

            public void setQualityVariableExList(List<?> qualityVariableExList) {
                this.qualityVariableExList = qualityVariableExList;
            }
        }
    }
}
