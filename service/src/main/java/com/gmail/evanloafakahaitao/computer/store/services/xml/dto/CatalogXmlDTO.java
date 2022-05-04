package com.gmail.evanloafakahaitao.computer.store.services.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "catalog")
public class CatalogXmlBinding {

    private List<ItemXmlBinding> items;

    public List<ItemXmlBinding> getItems() {
        return items;
    }

    @XmlElement(name = "item")
    public void setItems(List<ItemXmlBinding> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CatalogXmlBinding{");
        sb.append("items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
