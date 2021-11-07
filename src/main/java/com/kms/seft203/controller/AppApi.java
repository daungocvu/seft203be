package com.kms.seft203.controller;

import com.kms.seft203.entity.AppVersion;
import com.kms.seft203.repository.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppApi {
    @Autowired
    private AppVersionRepository appVersionRepo;

    @GetMapping("/version")
    public AppVersion getCurrentVersion() {
        return appVersionRepo
                .findById(1L)
                .orElse(new AppVersion());
    }
}
