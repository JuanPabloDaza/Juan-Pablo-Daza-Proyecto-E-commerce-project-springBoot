package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.controller.UserController;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.productService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    userService userService;

    @Mock
    productService productService;

    @Mock
    Model model;

    @Mock
    HttpServletResponse response;

    @Test
    public void testRegisterUser() {
        String view = userController.registerUser();
        assertEquals("register", view);
    }

    @Test
    public void testBuy() {
        String view = userController.buy();
        assertEquals("buy", view);
    }

    @Test
    public void testUserLogin() {
        String view = userController.userlogin(model);
        assertEquals("userLogin", view);
    }

    @Test
    public void testUserLoginValidate() {
        User user = new User();
        user.setUsername("testUser");
        when(userService.checkLogin("testUser", "testPass")).thenReturn(user);

        ModelAndView modelAndView = userController.userlogin("testUser", "testPass", model, response);

        assertEquals("index", modelAndView.getViewName());
        verify(response, times(1)).addCookie(any());
    }

    @Test
    public void testUserLoginValidateInvalid() {
        when(userService.checkLogin("testUser", "testPass")).thenReturn(new User());

        ModelAndView modelAndView = userController.userlogin("testUser", "testPass", model, response);

        assertEquals("userLogin", modelAndView.getViewName());
        verify(response, times(0)).addCookie(any());
    }

}