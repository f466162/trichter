package de.fgorsler.trichter.syslog;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SyslogMessage {
    private UUID internalId = UUID.randomUUID();

    private Integer headerRemotePort;
    private String headerConnectionId;
    private String headerLocalInetAddress;
    private UUID headerId;
    private String headerHostname;
    private LocalDateTime headerTimestamp;

    private Integer facility;
    private Integer severity;
    private String severityText;
    private LocalDateTime timestamp;
    private String hostname;
    private String appName;
    private String procId;
    private String msgId;
    private Integer version;
    private String message;
    private Boolean decodeErrors;
}
