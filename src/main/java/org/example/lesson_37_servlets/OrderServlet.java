package org.example.lesson_37_servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private static final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private static long idCounter = 0;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        super.init();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {
            Order order = mapper.readValue(reader, Order.class);
            if (order.getId() == 0) {
                order.setId(++idCounter);
            } else {
                if (orders.containsKey(order.getId())) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order with this id already exists");
                    return;
                }
                idCounter = Math.max(idCounter, order.getId());
            }
            orders.put(order.getId(), order);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), order);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter 'id' is required");
            return;
        }
        try {
            long id = Long.parseLong(idParam);
            Order order = orders.get(id);
            if (order == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
            } else {
                response.setContentType("application/json");
                mapper.writeValue(response.getWriter(), order);
            }
        } catch (NumberFormatException ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'id' parameter");
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter 'id' is required");
            return;
        }
        try {
            long id = Long.parseLong(idParam);
            if (!orders.containsKey(id)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                return;
            }
            try (BufferedReader reader = request.getReader()) {
                Order order = mapper.readValue(reader, Order.class);
                order.setId(id);
                orders.put(id, order);
                response.setContentType("application/json");
                mapper.writeValue(response.getWriter(), order);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON");
            }
        } catch (NumberFormatException ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'id' parameter");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter 'id' is required");
            return;
        }
        try {
            long id = Long.parseLong(idParam);
            Order removed = orders.remove(id);
            if (removed == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (NumberFormatException ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'id' parameter");
        }

    }

    public static void clearOrders() {
        orders.clear();
        idCounter = 0;
    }
}
