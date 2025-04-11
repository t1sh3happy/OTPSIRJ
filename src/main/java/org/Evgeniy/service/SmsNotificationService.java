package org.Evgeniy.service;

import org.jsmpp.bean.*;
import org.jsmpp.bean.BindType;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.extra.SessionState;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

public class SmsNotificationService {
    private final String host;
    private final int port;
    private final String systemId;
    private final String password;
    private final String systemType;
    private final String sourceAddr;
    private SMPPSession session;
    private final TimeFormatter timeFormatter = new AbsoluteTimeFormatter();

    public SmsNotificationService() {
        Properties config = loadConfig();
        this.host = config.getProperty("smpp.host");
        this.port = Integer.parseInt(config.getProperty("smpp.port"));
        this.systemId = config.getProperty("smpp.system_id");
        this.password = config.getProperty("smpp.password");
        this.systemType = config.getProperty("smpp.system_type");
        this.sourceAddr = config.getProperty("smpp.source_addr");

        this.session = new SMPPSession();
    }

    private Properties loadConfig() {
        try {
            Properties props = new Properties();
            props.load(SmsNotificationService.class.getClassLoader().getResourceAsStream("sms.properties"));
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load SMS configuration", e);
        }
    }

    public void connect() {
        try {
            session.connectAndBind(host, port, new BindParameter(
                    org.jsmpp.bean.BindType.BIND_TX, systemId, password, systemType,
                    TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null));
            System.out.println("Successfully connected to SMPP server!");
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect and bind to SMPP server", e);
        }
    }

    public void sendCode(String destinationNumber, String code) {
        try {
            String message = "Your OTP code is: " + code;
            if (session.getSessionState().equals(SessionState.BOUND_TX)) {
                session.submitShortMessage("CMT",
                        TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.ISDN, sourceAddr,
                        TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.ISDN, destinationNumber,
                        new ESMClass(), (byte) 0, (byte) 0, timeFormatter.format(new Date()), null,
                        new RegisteredDelivery(SMSCDeliveryReceipt.SUCCESS_FAILURE),
                        (byte) 0, new GeneralDataCoding(Alphabet.ALPHA_DEFAULT, MessageClass.CLASS1, false), (byte) 0,
                        message.getBytes(StandardCharsets.UTF_8));
                System.out.println("SMS sent successfully to " + destinationNumber);
            } else {
                System.err.println("Session is not bound.");
            }
        } catch (Exception e) {
            System.err.println("Failed to send SMS: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (session != null) {
            try {
                session.unbindAndClose();
                System.out.println("Disconnected from SMPP server.");
            } catch (Exception e) {
                System.err.println("Failed to disconnect from SMPP server.");
                e.printStackTrace();
            }
        }
    }

}