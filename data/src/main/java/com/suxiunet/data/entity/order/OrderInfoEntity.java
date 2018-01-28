package com.suxiunet.data.entity.order;

import com.suxiunet.data.entity.base.BasicDataEntity;

/**
 * author : chenzhi
 * time   : 2018/01/19
 * desc   : 订单信息
 */
public class OrderInfoEntity extends BasicDataEntity {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 公司名称
     */
    private String company;
    /**
     * 公司地址
     */
    private String companyAdr;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String contactTel;
    /**
     * 服务方式
     */
    private String serviceMode;
    /**
     * 机器类型  台式，笔记本，服务器，其它
     */
    private String machineMode;
    /**
     * 机器型号
     */
    private String machineType;
    private String maintenancePlan;
    private String orderSource;
    private String maintenanceAmt;
    private String maintenanceMaster;
    private String warranty;
    /**
     * 预约时间
     */
    private String appointmentTime;
    private String payType;
    private String creatTime;
    private String updateTime;
    private String status;
    private String protStatus;
    /**
     * 故障描述
     */
    private String faultDesc;
    /**
     * 师傅工号
     */
    private String masterId;
    
    

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyAdr() {
        return companyAdr;
    }

    public void setCompanyAdr(String companyAdr) {
        this.companyAdr = companyAdr;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getServiceMode() {
        return serviceMode;
    }

    public void setServiceMode(String serviceMode) {
        this.serviceMode = serviceMode;
    }

    public String getMachineMode() {
        return machineMode;
    }

    public void setMachineMode(String machineMode) {
        this.machineMode = machineMode;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getMaintenancePlan() {
        return maintenancePlan;
    }

    public void setMaintenancePlan(String maintenancePlan) {
        this.maintenancePlan = maintenancePlan;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getMaintenanceAmt() {
        return maintenanceAmt;
    }

    public void setMaintenanceAmt(String maintenanceAmt) {
        this.maintenanceAmt = maintenanceAmt;
    }

    public String getMaintenanceMaster() {
        return maintenanceMaster;
    }

    public void setMaintenanceMaster(String maintenanceMaster) {
        this.maintenanceMaster = maintenanceMaster;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProtStatus() {
        return protStatus;
    }

    public void setProtStatus(String protStatus) {
        this.protStatus = protStatus;
    }
}
