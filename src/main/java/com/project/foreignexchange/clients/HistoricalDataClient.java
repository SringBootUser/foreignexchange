package com.project.foreignexchange.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "historical-data-client", url = "${api.url}")
public interface HistoricalDataClient {

    @GetMapping("/historical")
    String getHistoricalRates(@RequestParam("access_key") String apiKey,
                            @RequestParam("date") String historicalDate,
                            @RequestParam(name = "source", required = false) String source,
                            @RequestParam(name = "currencies", required = false) List<String> currencies);
}