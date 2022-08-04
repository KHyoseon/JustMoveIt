package com.example.justmoveit.model.kakaoDto;

import com.google.gson.annotations.SerializedName;

public class ReadyParam {
    // 가맹점 코드
    @SerializedName("cid")
    private String cid;
    // 가맹점 주문번호
    @SerializedName("partner_order_id")
    private String partner_order_id;
    // 가맹점 회원 id
    @SerializedName("partner_user_id")
    private String partner_user_id;
    // 상품명
    @SerializedName("item_name")
    private String item_name;
    // 상품 수량
    @SerializedName("quantity")
    private Integer quantity;
    // 상품 총액
    @SerializedName("total_amount")
    private Integer total_amount;
    // 상품 비과세 금액
    @SerializedName("tax_free_amount")
    private Integer tax_free_amount;
    // 결제 성공 시 redirect url
    @SerializedName("approval_url")
    private String approval_url;
    // 결제 취소 시 redirect url
    @SerializedName("cancel_url")
    private String cancel_url;
    // 결제 실패 시 redirect url
    @SerializedName("fail_url")
    private String fail_url;

    public ReadyParam(){}

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPartner_order_id() {
        return partner_order_id;
    }

    public void setPartner_order_id(String partner_order_id) {
        this.partner_order_id = partner_order_id;
    }

    public String getPartner_user_id() {
        return partner_user_id;
    }

    public void setPartner_user_id(String partner_user_id) {
        this.partner_user_id = partner_user_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

    public Integer getTax_free_amount() {
        return tax_free_amount;
    }

    public void setTax_free_amount(Integer tax_free_amount) {
        this.tax_free_amount = tax_free_amount;
    }

    public String getApproval_url() {
        return approval_url;
    }

    public void setApproval_url(String approval_url) {
        this.approval_url = approval_url;
    }

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }

    public String getFail_url() {
        return fail_url;
    }

    public void setFail_url(String fail_url) {
        this.fail_url = fail_url;
    }

    @Override
    public String toString() {
        return "KakaoPayParam{" +
                "cid='" + cid + '\'' +
                ", partner_order_id='" + partner_order_id + '\'' +
                ", partner_user_id='" + partner_user_id + '\'' +
                ", item_name='" + item_name + '\'' +
                ", quantity=" + quantity +
                ", total_amount=" + total_amount +
                ", tax_free_amount=" + tax_free_amount +
                ", approval_url='" + approval_url + '\'' +
                ", cancel_url='" + cancel_url + '\'' +
                ", fail_url='" + fail_url + '\'' +
                '}';
    }
}
