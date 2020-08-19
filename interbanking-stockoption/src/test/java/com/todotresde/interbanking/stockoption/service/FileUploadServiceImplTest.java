package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.StockOptionManagementApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        StockOptionManagementApplication.class
})
@AutoConfigureMockMvc
@TestPropertySource(
        locations = {"classpath:application-test.properties","classpath:stockoption-test.properties"})
public class FileUploadServiceImplTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileUploadService fileUploadService;

    private final String username = "leonardo";
    private final String filename = "filename.txt";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void save() throws Exception{

        String fileContent = "Acción, Fecha, Precio\nGGAL, 11/8/2020, $280,22";
        MockMultipartFile file = new MockMultipartFile("file", filename, "text/plain", fileContent.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/stockoption/file/upload/" + username)
                .file(file))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void getListFiles() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/stockoption/file/files/" + username))
                .andExpect(status().is(200));
    }
}
