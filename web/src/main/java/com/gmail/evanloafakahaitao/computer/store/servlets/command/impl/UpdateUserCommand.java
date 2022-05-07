//package com.gmail.evanloafakahaitao.computer.store.servlets.command.impl;
//
//import com.gmail.evanloafakahaitao.computer.store.config.ConfigurationManager;
//import com.gmail.evanloafakahaitao.computer.store.config.properties.PageProperties;
//import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
//import com.gmail.evanloafakahaitao.computer.store.services.UserService;
//import com.gmail.evanloafakahaitao.computer.store.services.impl.UserServiceImpl;
//import com.gmail.evanloafakahaitao.computer.store.servlets.command.Command;
//import com.gmail.evanloafakahaitao.computer.store.servlets.model.CommandEnum;
//import com.gmail.evanloafakahaitao.computer.store.servlets.util.FieldTrimmer;
//import com.gmail.evanloafakahaitao.computer.store.servlets.util.UserValidator;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class UpdateUserCommand implements Command {
//
//    private FieldTrimmer fieldTrimmer = new FieldTrimmer();
//    private UserValidator userValidator = new UserValidator();
//    private UserService userService = new UserServiceImpl();
//    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Long id = Long.parseLong(request.getParameter("id"));
//        String email =  request.getParameter("email");
//        String password = request.getParameter("password");
//        String firstName = request.getParameter("first_name");
//        String lastName = request.getParameter("last_name");
//        String phoneNumber = request.getParameter("phone_number");
//        String additionalInfo = request.getParameter("additional_info");
//        User user = User.newBuilder()
//                .withId(id)
//                .withEmail(fieldTrimmer.trim(email))
//                .withFirstName(fieldTrimmer.trim(firstName))
//                .withLastName(fieldTrimmer.trim(lastName))
//                .withPhoneNumber(fieldTrimmer.trim(phoneNumber))
//                .withAdditionalInfo(fieldTrimmer.trim(additionalInfo))
//                .withPassword(fieldTrimmer.trim(password))
//                .build();
//        boolean isValid = userValidator.validate(user);
//        if (isValid) {
//            int updatedRows = userService.update(user);
//            if (updatedRows == 1) {
//                response.sendRedirect(request.getContextPath() + CommandEnum.USERS.getUrl());
//            } else {
//                request.setAttribute("error", "No fields changed");
//                String oldEmail = request.getParameter("old_email");
//                User oldUser = userService.findByEmail(oldEmail);
//                request.setAttribute("user", oldUser);
//                request.setAttribute("password", password);
//                request.setAttribute("email", email);
//                request.setAttribute("first_name", firstName);
//                request.setAttribute("last_name", lastName);
//                request.setAttribute("phone_number", phoneNumber);
//                request.setAttribute("additional_info", additionalInfo);
//                return configurationManager.getProperty(PageProperties.UPDATE_USER_MENU_PAGE_PATH);
//            }
//        } else {
//            request.setAttribute("error", "Invalid fields");
//            String oldEmail = request.getParameter("old_email");
//            User oldUser = userService.findByEmail(oldEmail);
//            request.setAttribute("user", oldUser);
//            request.setAttribute("password", password);
//            request.setAttribute("email", email);
//            request.setAttribute("first_name", firstName);
//            request.setAttribute("last_name", lastName);
//            request.setAttribute("phone_number", phoneNumber);
//            request.setAttribute("additional_info", additionalInfo);
//            return configurationManager.getProperty(PageProperties.UPDATE_USER_MENU_PAGE_PATH);
//        }
//        return null;
//    }
//}
