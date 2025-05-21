package org.example.lesson_37_servlets;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.*;

import java.io.*;

class OrderServletTest {
    private OrderServlet servlet;

    @BeforeEach
    void setUp() throws ServletException {
        servlet = new OrderServlet();
        servlet.init();
        OrderServlet.clearOrders();
    }

    @Test
    void testCreateOrderSuccess() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String json = """
                {
                  "date": "2025-05-20",
                  "cost": 123.45,
                  "products": [{"id":1,"name":"Prod","cost":123.45}]
                }
                """;

        BufferedReader reader = new BufferedReader(new StringReader(json));
        when(request.getReader()).thenReturn(reader);

        StringWriter out = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(out));

        servlet.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_CREATED);

        String result = out.toString();
        assertTrue(result.contains("\"id\":1"));
        assertTrue(result.contains("\"date\":\"2025-05-20\""));
    }

    @Test
    void testCreateOrderInvalidJson() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{invalid-json")));

        servlet.doPost(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON");
    }

    @Test
    void testGetOrderSuccess() throws Exception {

        HttpServletRequest postReq = mock(HttpServletRequest.class);
        HttpServletResponse postResp = mock(HttpServletResponse.class);

        String json = """
                {
                  "date": "2025-05-20",
                  "cost": 50.0,
                  "products": []
                }
                """;

        BufferedReader reader = new BufferedReader(new StringReader(json));
        when(postReq.getReader()).thenReturn(reader);

        when(postResp.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.doPost(postReq, postResp);

        HttpServletRequest getReq = mock(HttpServletRequest.class);
        HttpServletResponse getResp = mock(HttpServletResponse.class);

        when(getReq.getParameter("id")).thenReturn("1");

        StringWriter out = new StringWriter();
        when(getResp.getWriter()).thenReturn(new PrintWriter(out));

        servlet.doGet(getReq, getResp);

        String result = out.toString();
        assertTrue(result.contains("\"id\":1"), "JSON should contain id=1");
        assertTrue(result.contains("\"cost\":50.0"), "JSON should contain cost=50.0");
        assertTrue(result.contains("\"date\":\"2025-05-20\""), "JSON should contain the correct date");
    }

    @Test
    void testGetOrderNotFound() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("999");

        servlet.doGet(request, response);

        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
    }

    @Test
    void testUpdateOrderSuccess() throws Exception {

        HttpServletRequest createReq = mock(HttpServletRequest.class);
        HttpServletResponse createResp = mock(HttpServletResponse.class);

        String createJson = """
                {
                  "id": 1,
                  "date": "2025-05-20",
                  "cost": 100.0,
                  "products": []
                }
                """;

        when(createReq.getReader()).thenReturn(new BufferedReader(new StringReader(createJson)));

        when(createResp.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.doPost(createReq, createResp);

        HttpServletRequest putReq = mock(HttpServletRequest.class);
        HttpServletResponse putResp = mock(HttpServletResponse.class);

        when(putReq.getParameter("id")).thenReturn("1");

        String updateJson = """
                {
                  "date": "2025-06-01",
                  "cost": 150.0,
                  "products": []
                }
                """;

        when(putReq.getReader()).thenReturn(new BufferedReader(new StringReader(updateJson)));

        StringWriter out = new StringWriter();
        when(putResp.getWriter()).thenReturn(new PrintWriter(out));

        servlet.doPut(putReq, putResp);

        String result = out.toString();
        assertTrue(result.contains("\"date\":\"2025-06-01\""));
        assertTrue(result.contains("\"cost\":150.0"));
    }

    @Test
    void testDeleteOrderSuccess() throws Exception {

        HttpServletRequest createReq = mock(HttpServletRequest.class);
        HttpServletResponse createResp = mock(HttpServletResponse.class);

        String createJson = """
                {
                  "id": 1,
                  "date": "2025-05-20",
                  "cost": 200.0,
                  "products": []
                }
                """;

        when(createReq.getReader()).thenReturn(new BufferedReader(new StringReader(createJson)));

        when(createResp.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        servlet.doPost(createReq, createResp);

        HttpServletRequest deleteReq = mock(HttpServletRequest.class);
        HttpServletResponse deleteResp = mock(HttpServletResponse.class);

        when(deleteReq.getParameter("id")).thenReturn("1");

        servlet.doDelete(deleteReq, deleteResp);

        verify(deleteResp).setStatus(HttpServletResponse.SC_NO_CONTENT);

        HttpServletRequest getReq = mock(HttpServletRequest.class);
        HttpServletResponse getResp = mock(HttpServletResponse.class);

        when(getReq.getParameter("id")).thenReturn("1");

        servlet.doGet(getReq, getResp);

        verify(getResp).sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
    }
}
