package com.kms.seft203.config;

import com.kms.seft203.dto.ContactRequestDto;
import com.kms.seft203.dto.DashboardDto;
import com.kms.seft203.entity.AppVersion;
import com.kms.seft203.entity.Contact;
import com.kms.seft203.entity.Dashboard;
import com.kms.seft203.repository.AppVersionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CustomConfig {
    @Bean
    CommandLineRunner runner(AppVersionRepository repo, SecurityDataConfig securityDataConfig) {

        return args -> {
            repo.save(new AppVersion(1L, "SEFT Program", "1.0.0"));
            securityDataConfig.initSecurityData();
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS")
                        .allowedOrigins("*");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Config mapping for Contact -> ContactRequestDTO
        modelMapper.typeMap(Contact.class, ContactRequestDto.class).addMappings(mapper -> {
           mapper.map(src -> src.getUser().getEmail(), ContactRequestDto::setEmail);
        });

        // Config mapping for Dashboard -> DashboardDto
        modelMapper.typeMap(Dashboard.class, DashboardDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getContact().getUser().getEmail(), DashboardDto::setEmail);
        });
        return modelMapper;
    }
}
