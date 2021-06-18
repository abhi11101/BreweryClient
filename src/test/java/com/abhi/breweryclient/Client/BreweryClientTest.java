package com.abhi.breweryclient.Client;

import com.abhi.breweryclient.Model.BeerDTO;
import com.abhi.breweryclient.Model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerById(){
        BeerDTO beerDTO = breweryClient.getBeerById(UUID.randomUUID()) ;
        assertNotNull(beerDTO);
    }

    @Test
    void saveBeer(){
        BeerDTO beerDTO = BeerDTO.builder().beerName("New Beer").build();
        System.out.println(beerDTO);
        URI uri = breweryClient.saveBeer(beerDTO);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void updateBeer(){
        BeerDTO beerDTO = BeerDTO.builder().beerName("New Beer").build();
        breweryClient.updateBeer(UUID.randomUUID(),beerDTO);
    }

    @Test
    void deleteBeer(){
       UUID uuid =UUID.randomUUID();
       log.info("Test Delete with Id: " + uuid);
        breweryClient.deleteBeer(uuid);
    }

    @Test
    void getCustomerById() {
        log.info("Get Customer method test started..");
        CustomerDTO customerDTO = breweryClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDTO);
    }

    @Test
    void saveCustomer() {
        log.info("Save Test Started");
        CustomerDTO customerDTO = CustomerDTO.builder().name("New Customer").build();
        URI uri = breweryClient.saveCustomer(customerDTO);
        assertNotNull(uri);
        log.info("URI is: " + uri);

    }

    @Test
    void updateCustomer() {
        log.info("Update Customer method  test started");
        CustomerDTO customerDTO = CustomerDTO.builder().name("New Customer").build();
        breweryClient.updateCustomer(UUID.randomUUID(),customerDTO);

    }

    @Test
    void deleteCustomer() {
        UUID uuid = UUID.randomUUID();
        log.info("Delete method test started...");
        breweryClient.deleteCustomer(uuid);
        log.info("UUID is : " + uuid);
    }
}