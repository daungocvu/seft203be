package com.kms.seft203.service;

import com.kms.seft203.dto.DashboardDto;
import com.kms.seft203.entity.Contact;
import com.kms.seft203.entity.Dashboard;
import com.kms.seft203.entity.User;
import com.kms.seft203.repository.ContactRepository;
import com.kms.seft203.repository.DashboardRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@SpringBootTest
public class DashboardServiceTest {
    @Autowired
    private DashboardService dashboardService;

    @MockBean
    private ContactRepository contactRepository;

    @MockBean
    private DashboardRepository dashboardRepository;

    @Test
    public void testSave_whenSuccess_thenReturnDashboardDto() throws Exception {
        String email = "abc@gmail.com";
        String title = "Login page";
        String layoutType = "Light mode";
        String firstName = "A";
        String lastName = "Nguyen";
        String project = "GSS";
        String password = "1";
        DashboardDto mockDashboardDto = new DashboardDto(email, title, layoutType);
        User mockUser = new User(null, email, password, firstName + " " + lastName);
        Contact mockContact = new Contact(firstName, lastName, mockUser, title, project);
        Dashboard mockDashboard = new Dashboard(null, title, layoutType, mockContact);

        Dashboard dashboardFromDb = null;
        Mockito.when(contactRepository.findByEmail(email)).thenReturn(Optional.of(mockContact));
        Mockito.when(dashboardRepository.save(Mockito.any())).thenReturn(mockDashboard);
        Mockito.when(dashboardRepository.findByContact(Mockito.eq(mockContact))).thenReturn(Optional.ofNullable(dashboardFromDb));

        DashboardDto dashboardResponse = dashboardService.save(mockDashboardDto);
        assertEquals(mockDashboardDto.getEmail(), dashboardResponse.getEmail());
        assertEquals(mockDashboardDto.getTitle(), dashboardResponse.getTitle());
        assertEquals(mockDashboardDto.getLayoutType(), dashboardResponse.getLayoutType());
    }
}
