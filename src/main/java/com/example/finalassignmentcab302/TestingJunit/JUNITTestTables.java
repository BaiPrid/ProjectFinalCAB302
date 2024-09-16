//package com.example.finalassignmentcab302.TestingJunit;
//
//import com.example.finalassignmentcab302.Tables.Order;
//import org.junit.jupiter.api.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class JUNITTestTables {
//
//
//        private Order order1;
//        private Order order2;
//
//        @BeforeEach
//        public void setUp() {
//            // Initialize Order objects before each test
//            order1 = new Order(1, 100, 200, "2023-09-10 14:30", 150.50f, "123 Example St, City, Country");
//            order2 = new Order(101, 201, "2023-09-11 15:00", 250.75f, "456 Another St, Town, Region");
//        }
//
//        @Test
//        public void testOrderConstructorWithId() {
//
//            assertEquals(1, order1.getOrderId());
//            assertEquals(100, order1.getUserId());
//            assertEquals(200, order1.getOrganisationId());
//            assertEquals("2023-09-10 14:30", order1.getOrderDateTime());
//            assertEquals(150.50f, order1.getAmount());
//            assertEquals("123 Example St, City, Country", order1.getBillingAddress());
//        }
//
//        @Test
//        public void testOrderConstructorWithoutId() {
//
//            assertEquals(101, order2.getUserId());
//            assertEquals(201, order2.getOrganisationId());
//            assertEquals("2023-09-11 15:00", order2.getOrderDateTime());
//            assertEquals(250.75f, order2.getAmount());
//            assertEquals("456 Another St, Town, Region", order2.getBillingAddress());
//
//
//            assertEquals(0, order2.getOrderId());
//        }
//
//        @Test
//        public void testGetOrderId() {
//            // Test getter for orderId
//            assertEquals(1, order1.getOrderId());
//            assertEquals(0, order2.getOrderId());
//        }
//
//        @Test
//        public void testGetUserId() {
//            // Test getter for userId
//            assertEquals(100, order1.getUserId());
//            assertEquals(101, order2.getUserId());
//        }
//
//        @Test
//        public void testGetOrganisationId() {
//            // Test getter for organisationId
//            assertEquals(200, order1.getOrganisationId());
//            assertEquals(201, order2.getOrganisationId());
//        }
//
//        @Test
//        public void testGetOrderDateTime() {
//            // Test getter for orderDateTime
//            assertEquals("2023-09-10 14:30", order1.getOrderDateTime());
//            assertEquals("2023-09-11 15:00", order2.getOrderDateTime());
//        }
//
//        @Test
//        public void testGetAmount() {
//            // Test getter for amount
//            assertEquals(150.50f, order1.getAmount());
//            assertEquals(250.75f, order2.getAmount());
//        }
//
//        @Test
//        public void testGetBillingAddress() {
//            // Test getter for billingAddress
//            assertEquals("123 Example St, City, Country", order1.getBillingAddress());
//            assertEquals("456 Another St, Town, Region", order2.getBillingAddress());
//        }
//
//        @Test
//        public void testSetBillingAddress() {
//
//            order1.setBillingAddress("789 New Address St, New City, New Country");
//            assertEquals("789 New Address St, New City, New Country", order1.getBillingAddress());
//
//
//            assertEquals("456 Another St, Town, Region", order2.getBillingAddress());
//        }
//    }


