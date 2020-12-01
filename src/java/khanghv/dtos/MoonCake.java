/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.dtos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "MoonCake", catalog = "MoonCakeShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MoonCake.findAll", query = "SELECT m FROM MoonCake m")
    , @NamedQuery(name = "MoonCake.findByIDCake", query = "SELECT m FROM MoonCake m WHERE m.iDCake = :iDCake")
    , @NamedQuery(name = "MoonCake.findByCakeName", query = "SELECT m FROM MoonCake m WHERE m.cakeName = :cakeName")
    , @NamedQuery(name = "MoonCake.findByImage", query = "SELECT m FROM MoonCake m WHERE m.image = :image")
    , @NamedQuery(name = "MoonCake.findByDescription", query = "SELECT m FROM MoonCake m WHERE m.description = :description")
    , @NamedQuery(name = "MoonCake.findByPrice", query = "SELECT m FROM MoonCake m WHERE m.price = :price")
    , @NamedQuery(name = "MoonCake.findByCreateDate", query = "SELECT m FROM MoonCake m WHERE m.createDate = :createDate")
    , @NamedQuery(name = "MoonCake.findByExpireDate", query = "SELECT m FROM MoonCake m WHERE m.expireDate = :expireDate")
    , @NamedQuery(name = "MoonCake.findByStatus", query = "SELECT m FROM MoonCake m WHERE m.status = :status")
    , @NamedQuery(name = "MoonCake.findByUserUpdate", query = "SELECT m FROM MoonCake m WHERE m.userUpdate = :userUpdate")
    , @NamedQuery(name = "MoonCake.findByLastUpdate", query = "SELECT m FROM MoonCake m WHERE m.lastUpdate = :lastUpdate")
    , @NamedQuery(name = "MoonCake.findByQuantity", query = "SELECT m FROM MoonCake m WHERE m.quantity = :quantity")})
public class MoonCake implements Serializable, Comparable<MoonCake> {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDCake")
    private Collection<OrderDetail> orderDetailCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCake", nullable = false, length = 5)
    private String iDCake;
    @Basic(optional = false)
    @Column(name = "CakeName", nullable = false, length = 50)
    private String cakeName;
    @Basic(optional = false)
    @Column(name = "Image", nullable = false, length = 1000)
    private String image;
    @Basic(optional = false)
    @Column(name = "Description", nullable = false, length = 200)
    private String description;
    @Basic(optional = false)
    @Column(name = "Price", nullable = false)
    private int price;
    @Basic(optional = false)
    @Column(name = "CreateDate", nullable = false, length = 10)
    private String createDate;
    @Basic(optional = false)
    @Column(name = "ExpireDate", nullable = false, length = 10)
    private String expireDate;
    @Basic(optional = false)
    @Column(name = "Status", nullable = false, length = 15)
    private String status;
    @Basic(optional = false)
    @Column(name = "userUpdate", nullable = false, length = 25)
    private String userUpdate;
    @Basic(optional = false)
    @Column(name = "lastUpdate", nullable = false, length = 10)
    private String lastUpdate;
    @Basic(optional = false)
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID", nullable = false)
    @ManyToOne(optional = false)
    private Category categoryID;

    public MoonCake() {
    }

    public MoonCake(String iDCake) {
        this.iDCake = iDCake;
    }

    public MoonCake(String iDCake, int quantity) {
        this.iDCake = iDCake;
        this.quantity = quantity;
    }

    public MoonCake(String iDCake, String cakeName, String image, String description, int price, String createDate, String expireDate, String status, String userUpdate, String lastUpdate, int quantity) {
        this.iDCake = iDCake;
        this.cakeName = cakeName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.status = status;
        this.userUpdate = userUpdate;
        this.lastUpdate = lastUpdate;
        this.quantity = quantity;
    }

    public String getIDCake() {
        return iDCake.trim();
    }

    public void setIDCake(String iDCake) {
        this.iDCake = iDCake;
    }

    public String getCakeName() {
        return cakeName.trim();
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getImage() {
        return image.trim();
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description.trim();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCreateDate() {
        return createDate.trim();
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExpireDate() {
        return expireDate.trim();
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getStatus() {
        return status.trim();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserUpdate() {
        return userUpdate.trim();
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public String getLastUpdate() {
        return lastUpdate.trim();
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCake != null ? iDCake.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MoonCake)) {
            return false;
        }
        MoonCake other = (MoonCake) object;
        if ((this.iDCake == null && other.iDCake != null) || (this.iDCake != null && !this.iDCake.equals(other.iDCake))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MoonCake{" + "iDCake=" + iDCake + ", cakeName=" + cakeName + ", image=" + image + ", description=" + description + ", price=" + price + ", createDate=" + createDate + ", expireDate=" + expireDate + ", status=" + status + ", userUpdate=" + userUpdate + ", lastUpdate=" + lastUpdate + ", quantity=" + quantity + ", categoryID=" + categoryID + '}';
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }

    @Override
    public int compareTo(MoonCake t) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (sdf.parse(this.createDate).after(sdf.parse(t.createDate))) {
                return 1;
            } else {
                return -1;
            }
        } catch (ParseException ex) {
            return 1;
        }
    }

}
