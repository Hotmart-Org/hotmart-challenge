package com.hotmart.challenge.web.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hotmart.challenge.web.controller.RanqueamentoController;

@Component
@EnableScheduling
public class ScheduledTasks {
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	@Autowired
	private RanqueamentoController ranqueamento;

	@Scheduled(cron = "0 0 1 \\* \\* \\*", zone = TIME_ZONE)
    public void scheduleTaskWithCronExpression() {
		ranqueamento.ranquearProdutos();
    }
}
