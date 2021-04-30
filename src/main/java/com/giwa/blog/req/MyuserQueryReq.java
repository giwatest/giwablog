package com.giwa.blog.req;

public class MyuserQueryReq extends PageReq{
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MyuserQueryReq{");
        sb.append("loginName='").append(loginName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}