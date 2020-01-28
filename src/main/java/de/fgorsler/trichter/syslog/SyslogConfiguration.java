package de.fgorsler.trichter.syslog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.Deserializer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.syslog.MessageConverter;
import org.springframework.integration.syslog.RFC5424MessageConverter;
import org.springframework.integration.syslog.inbound.RFC6587SyslogDeserializer;
import org.springframework.integration.syslog.inbound.TcpSyslogReceivingChannelAdapter;

@Configuration
public class SyslogConfiguration {
    @Bean
    public MessageConverter getRFC5424MessageConverter() {
        return new RFC5424MessageConverter();
    }

    @Bean
    public Deserializer getRFC6587Deserializer() {
        return new RFC6587SyslogDeserializer();
    }

    @Bean
    public AbstractServerConnectionFactory getConnectionFactory() {
        TcpNetServerConnectionFactory connectionFactory = new TcpNetServerConnectionFactory(1514);

        connectionFactory.setDeserializer(getRFC6587Deserializer());
        connectionFactory.setBacklog(1000);

        return connectionFactory;
    }

    @Bean
    public TcpSyslogReceivingChannelAdapter getTcpSyslog() {
        TcpSyslogReceivingChannelAdapter adapter = new TcpSyslogReceivingChannelAdapter();

        adapter.setAutoStartup(true);
        adapter.setConverter(getRFC5424MessageConverter());
        adapter.setConnectionFactory(getConnectionFactory());

        return adapter;
    }

    @Bean
    public IntegrationFlow syslogFlow() {
        return IntegrationFlows.from(getTcpSyslog())
                .log(LoggingHandler.Level.INFO)
                .channel("syslogChannel")
                .aggregate(a -> a.correlationStrategy(m -> "syslogCorrelationBuffer").releaseStrategy(s -> s.size() >= 10))
                .handle(new SyslogMessageHandler())
                .get();
    }
}
