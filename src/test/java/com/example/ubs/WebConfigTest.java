package com.example.ubs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WebConfigTest {

    @Autowired
    private WebConfig webConfig;

    @Autowired
    private MockMvc mockMvc; // Ensure MockMvc is available if needed for integration testing

    @Test
    public void testViewResolver() throws Exception {
        ViewResolver viewResolver = webConfig.viewResolver();
        assertTrue(viewResolver instanceof InternalResourceViewResolver, "ViewResolver is not InternalResourceViewResolver");
        InternalResourceViewResolver internalResolver = (InternalResourceViewResolver) viewResolver;

        // Assert on the resolved view class
        assertEquals(InternalResourceViewResolver.class, internalResolver.getClass());

        // Mock view name and validate resolution
        String viewName = "testViewName";
        View view = internalResolver.resolveViewName(viewName, null); // Resolve the view

        // Validate view resolution
        assertTrue(view instanceof InternalResourceView, "View is not InternalResourceView");
        String resolvedViewName = ((InternalResourceView) view).getUrl();

        // Check if the resolved view name contains the expected prefix and suffix
        assertTrue(resolvedViewName.startsWith("/WEB-INF/views/"), "Prefix does not match");
        assertTrue(resolvedViewName.endsWith(".jsp"), "Suffix does not match");
    }
}
