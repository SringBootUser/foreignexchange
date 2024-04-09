package com.project.foreignexchange.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange-client", url = "${api.url}")
public interface ExchangeClient {

    @GetMapping("/live")
    String getExchangeRates(@RequestParam("access_key") String apiKey,
                            @RequestParam("currencies") String currencies,
                            @RequestParam("source") String source);
}