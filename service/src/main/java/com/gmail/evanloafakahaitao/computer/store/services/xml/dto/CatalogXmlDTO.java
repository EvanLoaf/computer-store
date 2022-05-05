package com.gmail.evanloafakahaitao.computer.store.services.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "catalog")
public class CatalogXmlDTO {

    private List<ItemXmlDTO> items;

    public List<ItemXmlDTO> getItems() {
        return items;
    }

    @XmlElement(name = "item")
    public void setItems(List<ItemXmlDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CatalogXmlDTO{");
        sb.append("items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
