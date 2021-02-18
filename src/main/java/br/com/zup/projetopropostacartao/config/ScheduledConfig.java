package br.com.zup.projetopropostacartao.config;

import br.com.zup.projetopropostacartao.services.CartaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledConfig {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledConfig.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private CartaoService cartaoService;

    @Scheduled(fixedRateString = "${scheduler.executa-cartao.fixed-rate}")
    public void executaCartaoAsync() throws InterruptedException {
        logger.info("Executando executaCartaoAsync() {}", dateFormat.format(new Date()));
        cartaoService.verificarCartao();
        logger.info("executaCartaoAsync() terminada as {}", dateFormat.format(new Date()));
    }
}