package com.project.foreignexchange.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "conversion-api", url = "${api.url}")
public interface ConversionApiClient {

    @GetMapping("/convert")
    String getConversionResponse(@RequestParam("access_key") String accessKey,
                                 @RequestParam("from") String sourceCurrency,
                                 @RequestParam("to") String targetCurrency,
                                 @RequestParam("amount") String amount);
}