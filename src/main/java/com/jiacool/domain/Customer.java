package com.jiacool.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private long custId;

    @Column(name = "cust_address")
    private String custAddress;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_phone")
    private String custPhone;

    @Column(name = "cust_source")
    private String custSource;

    //配置客户 和 联系人 之间的关系(一对多)
    /* 使用注解的形式配置多表关系
        1.声明关系：
            @OneToMany:  配置一对多  , 参数targetEntity： 对方对象的字节码对象
        2.配置外键（中间表）
            @JoinColumn:  配置外键
                name： 外键字段名称
                referencedColumnName： 参照的主表的主键字段名称
     */

//    @OneToMany(targetEntity = LinkMan.class)
//    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")//客户表：具备维护外键的作用
    /*
      放弃外键维护权
        mappedBy： 对方配置关系的属性名称
        cascade:  配置级联
        fetch:   配置关联对象的加载方式
            EAGER： 立即加载
            Lazy:  延迟加载
     */
    @OneToMany(mappedBy = "customer",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custAddress='" + custAddress + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custName='" + custName + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custSource='" + custSource + '\'' +
                ", linkMans=" + linkMans +
                '}';
    }
}
