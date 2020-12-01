/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.dtos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "OrderDetail", catalog = "MoonCakeShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o")
    , @NamedQuery(name = "OrderDetail.findByDetailID", query = "SELECT o FROM OrderDetail o WHERE o.detailID = :detailID")
    , @NamedQuery(name = "OrderDetail.findByPrice", query = "SELECT o FROM OrderDetail o WHERE o.price = :price")
    , @NamedQuery(name = "OrderDetail.findByQuantity", query = "SELECT o FROM OrderDetail o WHERE o.quantity = :quantity")})
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DetailID", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer detailID;
    @Basic(optional = false)
    @Column(name = "Price", nullable = false)
    private int price;
    @Basic(optional = false)
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @JoinColumn(name = "IDCake", referencedColumnName = "IDCake", nullable = false)
    @ManyToOne(optional = false)
    private MoonCake iDCake;
    @JoinColumn(name = "OrderId", referencedColumnName = "OrderId", nullable = false)
    @ManyToOne(optional = false)
    private ProductOrder orderId;

    public OrderDetail() {
    }

    public OrderDetail(Integer detailID) {
        this.detailID = detailID;
    }

    public OrderDetail(int price, int quantity, MoonCake iDCake) {
        this.price = price;
        this.quantity = quantity;
        this.iDCake = iDCake;
    }

    public OrderDetail(Integer detailID, int price, int quantity) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getDetailID() {
        return detailID;
    }

    public void setDetailID(Integer detailID) {
        this.detailID = detailID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MoonCake getIDCake() {
        return iDCake;
    }

    public void setIDCake(MoonCake iDCake) {
        this.iDCake = iDCake;
    }

    public ProductOrder getOrderId() {
        return orderId;
    }

    public void setOrderId(ProductOrder orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailID != null ? detailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.detailID == null && other.detailID != null) || (this.detailID != null && !this.detailID.equals(other.detailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "khanghv.dtos.OrderDetail[ detailID=" + detailID + " ]";
    }
    
}
