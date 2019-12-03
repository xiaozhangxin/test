package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class AddCompleteDecBean implements Serializable{


    /**
     * report_type : 1
     * complete_dec_lines : [{"complete_qty":10,"pro_id":1001812260140003,"mac_code":"mac_code","qualified_qty":10,"item_id":1001812181054564,"operator_id":132,"class_team_id":1001601100177743,"mod_code":"ZJ-7507-PR-L45°20-002","wh_id":1001812260140003,"eve_weight":12,"speed":14.4,"mac_pre_hours":"14","labor_hours":"14","mac_hours":14.4,"scrap_weight":14.4,"mod_weight":14.4}]
     */

    private String report_type;
    private List<CompleteDecLinesBean> complete_dec_lines;

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public List<CompleteDecLinesBean> getComplete_dec_lines() {
        return complete_dec_lines;
    }

    public void setComplete_dec_lines(List<CompleteDecLinesBean> complete_dec_lines) {
        this.complete_dec_lines = complete_dec_lines;
    }

}
