package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TraceService;

/**
 * @author yangkun
 *         generate on 16/11/11
 */
@RestController
@RequestMapping(value = "/trace")
public class TraceResource {
    @Autowired
    private TraceService traceService;

    @RequestMapping(value = "/start")
    public void startTrace(@RequestParam String orderNo) {
        traceService.exe(orderNo);
    }

    @RequestMapping(value = "/end")
    public void endTrace(@RequestParam(required = false) String orderNo) throws Exception{
        traceService.terminite(orderNo);
    }
}
