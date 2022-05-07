package com.gmail.evanloafakahaitao.computer.store;

import com.gmail.evanloafakahaitao.computer.store.services.ItemService;
import com.gmail.evanloafakahaitao.computer.store.services.UserService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.ItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.UserDTO;
import com.gmail.evanloafakahaitao.computer.store.services.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.computer.store.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest 
{

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;


    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void hibernateTesting() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("mail1357@mail.com");
        userDTO.setPassword("12354234");
        userDTO.setFirstName("fndxd");
        userDTO.setLastName("lnxd");
        UserDTO savedUser = userService.save(userDTO);
        System.out.println(savedUser.getId());

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("supaitem");
        itemDTO.setDescription("supaitem very cool yaya");
        itemDTO.setPrice(BigDecimal.valueOf(300L));
        itemDTO.setVendorCode("0000055555");
        ItemDTO savedItem = itemService.save(itemDTO);
        System.out.println(savedItem.getId());

        SimpleItemDTO simpleItemDTO = new SimpleItemDTO();
        simpleItemDTO.setVendorCode(savedItem.getVendorCode());

        System.out.println("VC " + simpleItemDTO.getVendorCode());
        ItemDTO foundItem = itemService.findByVendorCode(simpleItemDTO);
        System.out.println(foundItem);

        Assertions.assertAll(
                () -> assertTrue(userDTO.getEmail().equals(savedUser.getEmail())),
                () -> assertTrue(itemDTO.getVendorCode().equals(foundItem.getVendorCode()))
        );
    }
}
