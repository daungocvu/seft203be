package com.kms.seft203.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportApi {

    @GetMapping("_countBy/{collection}/{field}")
    public Map<String, Integer> countBy(@PathVariable String collection, @PathVariable String field) {
        Map<String, Integer> data = new HashMap<>();

        data.put("EM", 20);
        data.put("TE", 50);
        data.put("SE", 100);
        data.put("BA", 12);

        return data;
    }
}
