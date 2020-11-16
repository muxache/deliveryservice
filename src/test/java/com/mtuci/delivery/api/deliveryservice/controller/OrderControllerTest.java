package com.mtuci.delivery.api.deliveryservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mtuci.delivery.api.deliveryservice.AbstractTest;
import com.mtuci.delivery.api.deliveryservice.model.Order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class OrderControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mvc;

    Order order  = new Order();


    @Test
    public void createListDeleteTest() throws Exception {
       createTestOrder();
       listOrderTest();
       deleteTestOrder();
    }

    
    public void listOrderTest() throws Exception {
        String uri = "/api/order";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Order[] orders = super.mapFromJson(content, Order[].class);
        assertTrue(orders.length > 0);
    }

     @Test
     public void createAndDeleteOrder() throws Exception {
        createTestOrder();
        deleteTestOrder();
     }

     @Test
     public void createPutAndDeleteOrder() throws Exception {
        createTestOrder();
        updateNotCreatedTestOrder();
        deleteTestOrder();
     }

     @Test
     public void createTakeAndDeleteOrder() throws Exception {
        createTestOrder();
        takeTestOrder();
        deleteTestOrder();
     }

     @Test
     public void deleteNotCreatedTestOrder() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete("/api/order/-1")).andReturn();
        assertEquals(204, mvcResultDelete.getResponse().getStatus());
    }

    @Test
     public void getNotCreatedTestOrder() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.get("/api/order/-1")).andReturn();
        assertEquals(204, mvcResultDelete.getResponse().getStatus());
    }
     
    @Test
    public void updateNotCreatedTestOrder() throws Exception {
        Order o = new Order();
        o.setAddress("NewTestAddress");
        o.setProductId((long)20);
        String inputJson = super.mapToJson(o);
        MvcResult mvcResultPut = mvc.perform(MockMvcRequestBuilders.put("/api/order/-1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

     assertEquals(204, mvcResultPut.getResponse().getStatus());
    }

    @Test
    public void getTestOrder() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.get("/api/order/get")).andReturn();
        assertEquals(200, mvcResultDelete.getResponse().getStatus());
    }
    
    public void createTestOrder() throws Exception {
        Order o = new Order();
        o.setAddress("TestAddress");
        o.setProductId((long)10);
        String inputJson = super.mapToJson(o);
        MvcResult mvcResultPost = mvc.perform(MockMvcRequestBuilders.post("/api/order/new")
           .contentType(MediaType.APPLICATION_JSON_VALUE)
           .content(inputJson)).andReturn();

        assertEquals(201, mvcResultPost.getResponse().getStatus());
        String content = mvcResultPost.getResponse().getContentAsString();

        this.order =  super.mapFromJson(content, Order.class);
    }

    public void updateTestOrder() throws Exception {
        this.order.setAddress("NewTestAddress");
        String inputJson = super.mapToJson(this.order);
        MvcResult mvcResultPost = mvc.perform(MockMvcRequestBuilders.put("/api/order/"+this.order.getId())
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

        assertEquals(200, mvcResultPost.getResponse().getStatus());
        String content = mvcResultPost.getResponse().getContentAsString();

        this.order =  super.mapFromJson(content, Order.class);
    }



   
    public void deleteTestOrder() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete("/api/order/" + this.order.getId())).andReturn();
        assertEquals(200, mvcResultDelete.getResponse().getStatus());
    }

    public void takeTestOrder() throws Exception {
        this.order.setDeliverId((long)100);
        String inputJson = super.mapToJson(this.order);
        MvcResult mvcResultPost = mvc.perform(MockMvcRequestBuilders.put("/api/order/take/"+this.order.getId())
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

        assertEquals(200, mvcResultPost.getResponse().getStatus());
        String content = mvcResultPost.getResponse().getContentAsString();

        this.order =  super.mapFromJson(content, Order.class);
    }
    

}



