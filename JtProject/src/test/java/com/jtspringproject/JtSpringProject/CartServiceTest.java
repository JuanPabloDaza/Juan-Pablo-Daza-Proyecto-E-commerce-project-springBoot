package com.jtspringproject.JtSpringProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.jtspringproject.JtSpringProject.dao.cartDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.jtspringproject.JtSpringProject.services.cartService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CartServiceTest {

    @InjectMocks
    cartService cartService;

    @Mock
    cartDao cartDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addCartTest() {
        Cart cart = new Cart();
        when(cartDao.addCart(cart)).thenReturn(cart);

        Cart createdCart = cartService.addCart(cart);

        assertEquals(cart, createdCart);
        verify(cartDao, times(1)).addCart(cart);
    }

    @Test
    public void getCartsTest() {
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        when(cartDao.getCarts()).thenReturn(Arrays.asList(cart1, cart2));

        List<Cart> carts = cartService.getCarts();

        assertEquals(2, carts.size());
        verify(cartDao, times(1)).getCarts();
    }

    @Test
    public void updateCartTest() {
        Cart cart = new Cart();
        doNothing().when(cartDao).updateCart(cart);

        cartService.updateCart(cart);

        verify(cartDao, times(1)).updateCart(cart);
    }

    @Test
    public void deleteCartTest() {
        Cart cart = new Cart();
        doNothing().when(cartDao).deleteCart(cart);

        cartService.deleteCart(cart);

        verify(cartDao, times(1)).deleteCart(cart);
    }
}