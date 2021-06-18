package com.abhi.breweryclient.Client;

import com.abhi.breweryclient.Model.BeerDTO;
import com.abhi.breweryclient.Model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "abi.brewery",ignoreUnknownFields = false)
@Slf4j
public class BreweryClient {

    private String apiHost;

    private String BEER_PATH= "/api/beer/";
    private String CUSTOMER_PATH="/api/customer/";
    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder){
        restTemplate=restTemplateBuilder.build();
    }
    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public BeerDTO getBeerById(UUID uuid){
        return restTemplate.getForObject(apiHost+BEER_PATH+uuid.toString(),BeerDTO.class);
    }

    public URI saveBeer(BeerDTO beerDTO){
        return restTemplate.postForLocation(apiHost+BEER_PATH,beerDTO);
    }

    public void updateBeer(UUID uuid, BeerDTO beerDTO){
         restTemplate.put(apiHost+BEER_PATH+uuid.toString(),beerDTO);
    }

    public void deleteBeer(UUID uuid){
        log.info("Brewery Client Controller Delete with id: " + uuid);
        restTemplate.delete(apiHost+BEER_PATH+uuid.toString());

    }

    public CustomerDTO getCustomerById(UUID uuid){
        log.info("Customer Client get method with uuid: "+ uuid);
        return restTemplate.getForObject(apiHost+CUSTOMER_PATH+uuid.toString(),CustomerDTO.class);
    }

    public URI saveCustomer(CustomerDTO customerDTO){
        log.info("Customer Client save method with id: " + customerDTO.getUuid());
        return restTemplate.postForLocation(apiHost+CUSTOMER_PATH,customerDTO);
    }

    public void updateCustomer(UUID uuid, CustomerDTO customerDTO){
        log.info("Customer Client update method with id: " +uuid );
        restTemplate.put(apiHost+CUSTOMER_PATH+uuid.toString(),customerDTO);
    }

    public void deleteCustomer(UUID uuid){
        log.info("Customer Client Delete method with id: " + uuid);
        restTemplate.delete(apiHost+CUSTOMER_PATH+uuid.toString());
    }


}
