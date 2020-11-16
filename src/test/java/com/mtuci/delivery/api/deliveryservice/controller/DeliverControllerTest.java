package com.mtuci.delivery.api.deliveryservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mtuci.delivery.api.deliveryservice.AbstractTest;
import com.mtuci.delivery.api.deliveryservice.model.Deliver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class DeliverControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mvc;

    Deliver deliver  = new Deliver();

    @Test
    public void createListDeleteTest() throws Exception {
        createTestDeliver();
        listDeliverTest();
        deleteTestDeliver();
    }

    public void listDeliverTest() throws Exception {
        String uri = "/api/deliver";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Deliver[] delivers = super.mapFromJson(content, Deliver[].class);
        assertTrue(delivers.length > 0);
    }

     @Test
     public void createAndDeleteDeliver() throws Exception {
        createTestDeliver();
        deleteTestDeliver();
     }

     @Test
     public void deleteNotCreatedTestDeliver() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete("/api/deliver/-1")).andReturn();
        assertEquals(204, mvcResultDelete.getResponse().getStatus());
    }
     
    @Test
     public void getNotCreatedTestDeliver() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.get("/api/deliver/-1")).andReturn();
        assertEquals(204, mvcResultDelete.getResponse().getStatus());
    }
    
    public void createTestDeliver() throws Exception {
        Deliver d = new Deliver();
        d.setFirstname("Ivan");
        d.setLastname("Ivanov");
        String inputJson = super.mapToJson(d);
        MvcResult mvcResultPost = mvc.perform(MockMvcRequestBuilders.post("/api/deliver")
           .contentType(MediaType.APPLICATION_JSON_VALUE)
           .content(inputJson)).andReturn();

        assertEquals(201, mvcResultPost.getResponse().getStatus());
        String content = mvcResultPost.getResponse().getContentAsString();

        this.deliver =  super.mapFromJson(content, Deliver.class);
    }

   
    public void deleteTestDeliver() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete("/api/deliver/" + this.deliver.getId())).andReturn();
        assertEquals(200, mvcResultDelete.getResponse().getStatus());
    }

    

}


