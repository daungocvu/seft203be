package com.kms.seft203.controller;

import com.kms.seft203.dto.DashboardDto;
import com.kms.seft203.exception.ContactNotFoundException;
import com.kms.seft203.service.DashboardService;
import javassist.tools.web.BadHttpRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DashboardApi.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
public class DashboardControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DashboardService dashboardService;

    @Test
    public void testCreateDashboard_whenSuccess_thenReturnDashboardDto() throws Exception {
        String email = "abc@gmail.com";
        String title = "Home page";
        String layoutType = "Dark mode";
        DashboardDto dashboardDto = new DashboardDto(email, title, layoutType);
        Mockito.when(dashboardService.save(Mockito.eq(dashboardDto))).thenReturn(dashboardDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/dashboards")
                        .content(convertObjectToJsonString(dashboardDto))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.layoutType").value(layoutType));
    }

    @Test
    public void testCreateDashboard_whenDashboardDuplicated_thenReturnBadHttpRequest() throws Exception {
        String email = "abc@gmail.com";
        String title = "Home page";
        String layoutType = "Dark mode";
        DashboardDto dashboardDto = new DashboardDto(email, title, layoutType);
        Mockito.when(dashboardService.save(Mockito.eq(dashboardDto))).thenThrow(
                new BadHttpRequest(new Exception("Dashboard exists, pls change method to put"))
        );
        mockMvc.perform(MockMvcRequestBuilders.post("/dashboards")
                .content(convertObjectToJsonString(dashboardDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateDashboard_whenContactNotFound_thenReturnContactNotFoundException() throws Exception {
        String email = "abc@gmail.com";
        String title = "Home page";
        String layoutType = "Dark mode";
        String message = "Contact of user " + email + " not found!";
        DashboardDto dashboardDto = new DashboardDto(email, title, layoutType);
        Mockito.when(dashboardService.save(Mockito.eq(dashboardDto))).thenThrow(
                new ContactNotFoundException(message)
        );
        mockMvc.perform(MockMvcRequestBuilders.post("/dashboards")
                        .content(convertObjectToJsonString(dashboardDto))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(message));
    }

}