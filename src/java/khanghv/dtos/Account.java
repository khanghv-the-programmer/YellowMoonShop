/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.dtos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "Account", catalog = "MoonCakeShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByUserID", query = "SELECT a FROM Account a WHERE a.userID = :userID")
    , @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password")
    , @NamedQuery(name = "Account.findByPhone", query = "SELECT a FROM Account a WHERE a.phone = :phone")
    , @NamedQuery(name = "Account.findByFullname", query = "SELECT a FROM Account a WHERE a.fullname = :fullname")})
public class Account implements Serializable {

    @OneToMany(mappedBy = "userID")
    private Collection<ProductOrder> productOrderCollection;

    @Basic(optional = false)
    @Column(name = "Address", nullable = false, length = 200)
    private String address;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserID", nullable = false, length = 25)
    private String userID;
    @Basic(optional = false)
    @Column(name = "Password", nullable = false, length = 25)
    private String password;
    @Basic(optional = false)
    @Column(name = "Phone", nullable = false, length = 11)
    private String phone;
    @Basic(optional = false)
    @Column(name = "Fullname", nullable = false, length = 50)
    private String fullname;
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID", nullable = false)
    @ManyToOne(optional = false)
    private Role roleID;

    public Account() {
    }

    public Account(String userID) {
        this.userID = userID;
    }

    public Account(String userID, String password, String phone, String fullname) {
        this.userID = userID;
        this.password = password;
        this.phone = phone;
        this.fullname = fullname;
    }

    public String getUserID() {
        return userID.trim();
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone.trim();
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname.trim();
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Role getRoleID() {
        return roleID;
    }

    public void setRoleID(Role roleID) {
        this.roleID = roleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "khanghv.dtos.Account[ userID=" + userID + " ]";
    }

    public String getAddress() {
        return address.trim();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public Collection<ProductOrder> getProductOrderCollection() {
        return productOrderCollection;
    }

    public void setProductOrderCollection(Collection<ProductOrder> productOrderCollection) {
        this.productOrderCollection = productOrderCollection;
    }
    
}
