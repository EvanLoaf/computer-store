package com.gmail.evanloafakahaitao.computer.store.services.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ItemXmlDTO implements Serializable {

    private String name;
    private String vendorCode;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    @XmlElement(name = "vendor_code")
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemXmlDTO that = (ItemXmlDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(vendorCode, that.vendorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vendorCode);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemXmlDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", vendorCode='").append(vendorCode).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
