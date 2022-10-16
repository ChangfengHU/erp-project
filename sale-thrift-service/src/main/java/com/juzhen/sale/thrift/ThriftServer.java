package com.juzhen.sale.thrift;

import com.juzhen.api.sale.customer.CustomerRpcService;
import com.juzhen.api.sale.test.TestRpcService;
import com.juzhen.api.sale.user.UserRpcService;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by Michael on 2017/10/28.
 */
@Configuration
public class ThriftServer {

    @Value("${service.port}")
    private int servicePort;

    @Autowired
    private TestRpcService.Iface testRpcService;

    @Autowired
    private CustomerRpcService.Iface  customerRpcService;

    @PostConstruct
    public void startThriftServer() {


        TMultiplexedProcessor processor = new TMultiplexedProcessor();


//        TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));

        processor.registerProcessor("TestRpcService", new TestRpcService.Processor(testRpcService));
        processor.registerProcessor("CustomerRpcService", new CustomerRpcService.Processor(customerRpcService));

//        TProcessor processor = new TestRpcService.Processor<>(testRpcService);
//        TProcessor tProcessor = new CustomerRpcService.Processor<>(customerRpcService);

        TNonblockingServerSocket socket = null;
        try {
            socket = new TNonblockingServerSocket(servicePort);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
        args.processor(processor);
        args.transportFactory(new TFramedTransport.Factory());
        args.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TNonblockingServer(args);
        System.out.println("用户服务启动,启动端口:"+servicePort);
        server.serve();
    }
}
