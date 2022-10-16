package com.juzhen.http.user.thrift;

import com.juzhen.api.message.MessageService;
import com.juzhen.api.sale.customer.CustomerRpcService;
import com.juzhen.api.sale.test.TestRpcService;
import com.juzhen.api.user.UserRpcService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Michael on 2017/10/30.
 */
@Component
public class ServiceProvider {

    @Value("${thrift.user.ip}")
    private String serverIp;

    @Value("${thrift.user.port}")
    private int serverPort;

    @Value("${thrift.sale.ip}")
    private String serverIpSale;

    @Value("${thrift.sale.port}")
    private int serverPortSale;

    @Value("${thrift.message.ip}")
    private String messageServerIp;
    @Value("${thrift.message.port}")
    private int messageServerPort;

    private enum ServiceType {
        USER,
        SALE_TEST,
        SALE_CUSTOMER1,
        MESSAGE
    }

    public UserRpcService.Client getUserService() {

        return getService(serverIp, serverPort, ServiceType.USER);
    }
    public CustomerRpcService.Client getCustomerService() {
        System.out.println(12121212);
        return getService(serverIpSale, serverPortSale, ServiceType.SALE_CUSTOMER1);
    }

    public TestRpcService.Client getTestService() {

        return getService(serverIpSale, serverPortSale, ServiceType.SALE_TEST);
    }

    public MessageService.Client getMessasgeService() {

        return getService(messageServerIp, messageServerPort, ServiceType.MESSAGE);
    }

    public <T> T getService(String ip, int port, ServiceType serviceType) {
        TSocket socket = new TSocket(ip, port, 3000);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        TServiceClient result = null;
        switch (serviceType) {
            case USER:
                result = new UserRpcService.Client(protocol);
                break;
            case SALE_TEST:
//                result = new TestRpcService.Client(protocol);
                TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,"TestRpcService");
                result= new TestRpcService.Client(mp1);
                break;
            case SALE_CUSTOMER1:
//                result = new com.juzhen.api.sale.customer.CustomerRpcService.Client(protocol);
                TMultiplexedProtocol mp11 = new TMultiplexedProtocol(protocol,"CustomerRpcService");
                result= new CustomerRpcService.Client(mp11);
                break;
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
        }
        return (T)result;
    }

}
