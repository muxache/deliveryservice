package com.mtuci.delivery.api.deliveryservice.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.mtuci.delivery.api.deliveryservice.model.Host;

import org.springframework.stereotype.Service;

@Service
public class HostService {
    public Host getHostName() throws IOException {
        try {
            Host h = new Host();
            String hostname = InetAddress.getLocalHost().getHostName();
            h.setHostname(hostname);
            return h;
        } 
        catch (UnknownHostException e){
            System.out.println(e);
        }
        return null;
        
    }
    
}
