package com.jtspringproject.JtSpringProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.jtspringproject.JtSpringProject.services.categoryService;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class CategoryServiceTest {

    @InjectMocks
    categoryService categoryService;

    @Mock
    categoryDao categoryDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addCategoryTest() {
        String name = "test";
        Category category = new Category();
        when(categoryDao.addCategory(name)).thenReturn(category);

        Category createdCategory = categoryService.addCategory(name);

        assertEquals(category, createdCategory);
        verify(categoryDao, times(1)).addCategory(name);
    }

    @Test
    public void getCategoriesTest() {
        Category category1 = new Category();
        Category category2 = new Category();
        when(categoryDao.getCategories()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.getCategories();

        assertEquals(2, categories.size());
        verify(categoryDao, times(1)).getCategories();
    }

    @Test
    public void deleteCategoryTest() {
        int id = 1;
        when(categoryDao.deletCategory(id)).thenReturn(true);

        Boolean isDeleted = categoryService.deleteCategory(id);

        assertEquals(true, isDeleted);
        verify(categoryDao, times(1)).deletCategory(id);
    }

    @Test
    public void updateCategoryTest() {
        int id = 1;
        String name = "test";
        Category category = new Category();
        when(categoryDao.updateCategory(id, name)).thenReturn(category);

        Category updatedCategory = categoryService.updateCategory(id, name);

        assertEquals(category, updatedCategory);
        verify(categoryDao, times(1)).updateCategory(id, name);
    }

    @Test
    public void getCategoryTest() {
        int id = 1;
        Category category = new Category();
        when(categoryDao.getCategory(id)).thenReturn(category);

        Category fetchedCategory = categoryService.getCategory(id);

        assertEquals(category, fetchedCategory);
        verify(categoryDao, times(1)).getCategory(id);
    }
}