package com.akan.qf.bean;

/**
 * Created by admin on 2018/10/23.
 */

public class StaffMessageBean {

    /**
     * msg_id : 1
     * msg_title : 消息标题
     * msg_content : 消息内容
     * staff_id : 1
     * create_time : 2018-10-22 16:46:33
     * is_read : 0
     * ids :
     * create_time_simple : 18/10/2
     */

    private String msg_id;
    private String msg_title;
    private String msg_content;
    private String staff_id;
    private String create_time;
    private String is_read;
    private String ids;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    private String type;
    private String create_time_simple;
    private String role_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getMsg_title() {
        return msg_title;
    }

    public void setMsg_title(String msg_title) {
        this.msg_title = msg_title;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }



    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCreate_time_simple() {
        return create_time_simple;
    }

    public void setCreate_time_simple(String create_time_simple) {
        this.create_time_simple = create_time_simple;
    }
}
