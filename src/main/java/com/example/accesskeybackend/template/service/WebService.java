package com.example.accesskeybackend.template.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Objects;

@Service
@Log4j2
public class WebService {

    public Boolean isIPv6(final String siteUrl){
        Boolean isIPv6;
        String domainName = takeDomainName(siteUrl);
        try {
            InetAddress[] addresses = InetAddress.getAllByName(domainName);
            isIPv6 = Arrays.stream(addresses)
                    .filter(address->isIPv6(address))
                    .anyMatch(Objects::nonNull);
        } catch (final java.net.UnknownHostException ignored) {
            throw new com.example.accesskeybackend.exception.IllegalArgumentException(String.format(
                    "Bad siteUrl: %s", siteUrl)
                    , ignored);
        }

        return isIPv6;
    }

    private String takeDomainName(final String path){
        return path.replaceAll("http(s)?://|www\\.|/.*", "");
    }

    private boolean isIPv6(InetAddress inetAddress) {
        return (inetAddress != null) && (inetAddress instanceof Inet6Address);
    }
}
