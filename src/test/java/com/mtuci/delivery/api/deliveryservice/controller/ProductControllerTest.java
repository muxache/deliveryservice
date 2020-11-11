package com.mtuci.delivery.api.deliveryservice.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mtuci.delivery.api.deliveryservice.AbstractTest;
import com.mtuci.delivery.api.deliveryservice.model.Product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ProductControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mvc;

    Product product  = new Product();

    @Test
    public void listDeliverTest() throws Exception {
        String uri = "/api/product";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Product[] products = super.mapFromJson(content, Product[].class);
        assertTrue(products.length > 0);
    }

     @Test
     public void createAndDeleteProduct() throws Exception {
        createTestProduct();
        deleteTestProduct();
     }

     @Test
     public void createPutAndDeleteProduct() throws Exception {
        createTestProduct();
        updateNotCreatedTestProduct();
        deleteTestProduct();
     }

     @Test
     public void deleteNotCreatedTestProduct() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete("/api/product/-1")).andReturn();
        assertEquals(204, mvcResultDelete.getResponse().getStatus());
    }

    @Test
     public void getNotCreatedTestProduct() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.get("/api/product/-1")).andReturn();
        assertEquals(204, mvcResultDelete.getResponse().getStatus());
    }
     
    @Test
    public void updateNotCreatedTestProduct() throws Exception {
        Product p = new Product();
        p.setBrand("NewTestBrandWithNotCreatedId");
        p.setType("NewTestTypeWithNotCreatedId");
        String inputJson = super.mapToJson(p);
        MvcResult mvcResultPut = mvc.perform(MockMvcRequestBuilders.put("/api/product/-1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

     assertEquals(204, mvcResultPut.getResponse().getStatus());
    }
    
    public void createTestProduct() throws Exception {
        Product p = new Product();
        p.setBrand("TestBrand");
        p.setType("TestType");
        String inputJson = super.mapToJson(p);
        MvcResult mvcResultPost = mvc.perform(MockMvcRequestBuilders.post("/api/product")
           .contentType(MediaType.APPLICATION_JSON_VALUE)
           .content(inputJson)).andReturn();

        assertEquals(201, mvcResultPost.getResponse().getStatus());
        String content = mvcResultPost.getResponse().getContentAsString();

        this.product =  super.mapFromJson(content, Product.class);
    }

    public void updateTestProduct() throws Exception {
        this.product.setType("NewTestType");
        String inputJson = super.mapToJson(this.product);
        MvcResult mvcResultPost = mvc.perform(MockMvcRequestBuilders.put("/api/product/"+this.product.getId())
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

     assertEquals(200, mvcResultPost.getResponse().getStatus());
     String content = mvcResultPost.getResponse().getContentAsString();

     this.product =  super.mapFromJson(content, Product.class);
    }



   
    public void deleteTestProduct() throws Exception {
        MvcResult mvcResultDelete = mvc.perform(MockMvcRequestBuilders.delete("/api/product/" + this.product.getId())).andReturn();
        assertEquals(200, mvcResultDelete.getResponse().getStatus());
    }

    

}



