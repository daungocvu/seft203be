package com.kms.seft203.service;

import com.kms.seft203.dto.DashboardDto;
import com.kms.seft203.entity.Contact;
import com.kms.seft203.entity.Dashboard;
import com.kms.seft203.exception.ContactNotFoundException;
import com.kms.seft203.repository.ContactRepository;
import com.kms.seft203.repository.DashboardRepository;
import javassist.tools.web.BadHttpRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service @Transactional
public class DashboardServiceImp implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DashboardDto save(DashboardDto dashboardDto) throws ContactNotFoundException, BadHttpRequest {
        String email = dashboardDto.getEmail();
        Optional<Contact> contactOptional = contactRepository.findByEmail(email);
        if (contactOptional.isEmpty()) {
            throw new ContactNotFoundException(
                    "Contact not be found for user " + email);
        }
        Contact contact = contactOptional.get();
        Optional<Dashboard> dashboardFromDb = dashboardRepository.findByContact(contact);
        if (dashboardFromDb.isPresent()) {
            throw new BadHttpRequest(new Exception(email + " does exist"));
        }
        Dashboard dashboard = modelMapper.map(dashboardDto, Dashboard.class);
        dashboard.setContact(contact);
        Dashboard savedDashboard = dashboardRepository.save(dashboard);
        return modelMapper.map(savedDashboard, DashboardDto.class);
    }
}
