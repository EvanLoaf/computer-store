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
//import java.util.List;
//
//public class ItemsCommand implements Command {
//
//    private ItemService itemService = new ItemServiceImpl();
//    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        List<Item> items = itemService.findAll();
//        if (items != null) {
//            request.setAttribute("items", items);
//        } else {
//            request.setAttribute("error", "Error retrieving items");
//        }
//        return configurationManager.getProperty(PageProperties.ITEMS_PAGE_PATH);
//    }
//}
