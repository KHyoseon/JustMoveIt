package com.example.justmoveit.model.kakaoDto;

public class PayApproveParam {
    private String cid;
    private String tid;
    private String partner_order_id;
    private String partner_user_id;
    private String pg_token;
    private Integer total_amount;

    public PayApproveParam() {
    }

    public PayApproveParam(String cid, String tid, String partner_order_id, String partner_user_id, String pg_token, Integer total_amount) {
        this.cid = cid;
        this.tid = tid;
        this.partner_order_id = partner_order_id;
        this.partner_user_id = partner_user_id;
        this.pg_token = pg_token;
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "PayApproveParam{" +
                "cid='" + cid + '\'' +
                "\n, tid='" + tid + '\'' +
                "\n, partner_order_id='" + partner_order_id + '\'' +
                "\n, partner_user_id='" + partner_user_id + '\'' +
                "\n, pg_token='" + pg_token + '\'' +
                "\n, total_amount=" + total_amount +
                '}';
    }
}
