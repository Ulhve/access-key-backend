package com.example.accesskeybackend.template.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.net.Inet6Address;
import java.net.InetAddress;

@Service
@Log4j2
public class WebService {

    public Boolean isIPv6(final String siteUrl){
        Boolean isIPv6 = false;
        String domainName = takeDomainName(siteUrl);
        try {
            InetAddress[] addresses = InetAddress.getAllByName(domainName);
            for(InetAddress address : addresses){
                if (isIPv6(address)){
                    isIPv6 = true;
                }
            }
        } catch (final java.net.UnknownHostException ignored) {
            throw new com.example.accesskeybackend.exception.IllegalArgumentException(String.format(
                    "Bad siteUrl: %s", siteUrl)
                    , ignored);
        }

        return isIPv6;
    }

    private String takeDomainName(final String path){
        String domainName =  path.replaceAll("http(s)?://|www\\.|/.*", "");
        return domainName;
    }

    private boolean isIPv6(InetAddress inetAddress) {
        boolean isIPv6 = false;

        if (inetAddress != null) {
            isIPv6 = (inetAddress instanceof Inet6Address);
        }

        return isIPv6;
    }
}
