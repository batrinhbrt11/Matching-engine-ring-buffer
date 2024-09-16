package vn.demo.starter.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.demo.starter.service.MatchingEngineService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProcessOrderScheduler {

    private final MatchingEngineService matchingEngineService;

    @Scheduled(cron = "0 * * * * ?")
    @Transactional
    public void autoProcessOrders() {
        matchingEngineService.processOrders();
    }
}
