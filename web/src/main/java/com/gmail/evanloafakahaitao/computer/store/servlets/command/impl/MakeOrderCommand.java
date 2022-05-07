//package com.gmail.evanloafakahaitao.computer.store.servlets.command.impl;
//
//import com.gmail.evanloafakahaitao.computer.store.config.ConfigurationManager;
//import com.gmail.evanloafakahaitao.computer.store.config.properties.PageProperties;
//import com.gmail.evanloafakahaitao.computer.store.dao.model.Item;
//import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
//import com.gmail.evanloafakahaitao.computer.store.services.impl.ItemServiceImpl;
//import com.gmail.evanloafakahaitao.computer.store.servlets.command.Command;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class MakeOrderCommand implements Command {
//
//    private ItemService itemService = new ItemServiceImpl();
//    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String vendorCode = request.getParameter("vendor_code");
//        Item item = itemService.findByVendorCode(vendorCode);
//        if (item == null) {
//            request.setAttribute("error", "Currently unable to order this item");
//        } else {
//            request.setAttribute("item", item);
//        }
//        return configurationManager.getProperty(PageProperties.MAKE_ORDER_PAGE_PATH);
//    }
//}
