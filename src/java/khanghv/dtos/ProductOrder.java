/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "ProductOrder", catalog = "MoonCakeShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductOrder.findAll", query = "SELECT p FROM ProductOrder p")
    , @NamedQuery(name = "ProductOrder.findByOrderId", query = "SELECT p FROM ProductOrder p WHERE p.orderId = :orderId")
    , @NamedQuery(name = "ProductOrder.findByOrderName", query = "SELECT p FROM ProductOrder p WHERE p.orderName = :orderName")
    , @NamedQuery(name = "ProductOrder.findByDateOrder", query = "SELECT p FROM ProductOrder p WHERE p.dateOrder = :dateOrder")
    , @NamedQuery(name = "ProductOrder.findByAddress", query = "SELECT p FROM ProductOrder p WHERE p.address = :address")
    , @NamedQuery(name = "ProductOrder.findByPhone", query = "SELECT p FROM ProductOrder p WHERE p.phone = :phone")
    , @NamedQuery(name = "ProductOrder.findByTotal", query = "SELECT p FROM ProductOrder p WHERE p.total = :total")})
public class ProductOrder implements Serializable {

    @Basic(optional = false)
    @Column(name = "PaymentStatus", nullable = false, length = 25)
    private String paymentStatus;

    @Basic(optional = false)
    @Column(name = "Payment", nullable = false, length = 20)
    private String payment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private List<OrderDetail> orderDetailList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "OrderId", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer orderId;
    @Basic(optional = false)
    @Column(name = "OrderName", nullable = false, length = 30)
    private String orderName;
    @Basic(optional = false)
    @Column(name = "dateOrder", nullable = false, length = 10)
    private String dateOrder;
    @Basic(optional = false)
    @Column(name = "Address", nullable = false, length = 200)
    private String address;
    @Basic(optional = false)
    @Column(name = "Phone", nullable = false, length = 11)
    private String phone;
    @Basic(optional = false)
    @Column(name = "Total", nullable = false)
    private int total;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne
    private Account userID;

    public ProductOrder(String orderName, String dateOrder, String address, String phone) {
        this.orderName = orderName;
        this.dateOrder = dateOrder;
        this.address = address;
        this.phone = phone;
        this.orderDetailList = new ArrayList<>();
    }

    public ProductOrder() {
    }

    public ProductOrder(Integer orderId) {
        this.orderId = orderId;
        this.orderDetailList = new ArrayList<>();
    }

    public ProductOrder(Integer orderId, String orderName, String dateOrder, String address, String phone, int total) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.dateOrder = dateOrder;
        this.address = address;
        this.phone = phone;
        this.total = total;
        this.orderDetailList = new ArrayList<>();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Account getUserID() {
        return userID;
    }

    public void setUserID(Account userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductOrder)) {
            return false;
        }
        ProductOrder other = (ProductOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductOrder{" + "payment=" + payment + ", orderDetailList=" + orderDetailList.toString() + ", orderId=" + orderId + ", orderName=" + orderName + ", dateOrder=" + dateOrder + ", address=" + address + ", phone=" + phone + ", total=" + total + ", userID=" + userID + '}';
    }

    

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
}
