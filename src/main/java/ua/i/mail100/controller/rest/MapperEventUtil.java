package ua.i.mail100.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.model.Event;
import ua.i.mail100.service.CountryService;
import ua.i.mail100.service.FranchiseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperEventUtil {
    @Autowired
    FranchiseService franchiseService;

    @Autowired
    CountryService countryService;

    @Autowired
    ObjectMapper objectMapper;

    public Event toObject(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setFranchise(franchiseService.getById(eventDTO.getFranchiseId()));
        event.setOrganizer(eventDTO.getOrganizer());
        event.setName(eventDTO.getName());
        event.setDateFrom(eventDTO.getDateFrom());
        event.setDateTo(eventDTO.getDateTo());
        event.setCountry(countryService.getById(eventDTO.getCountryId()));
        event.setVenue(eventDTO.getVenue());
        event.setUrl(eventDTO.getUrl());
        event.setComment(eventDTO.getComment());
        return event;
    }

    public EventDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, EventDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EventDTO toDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setFranchiseId(event.getFranchise().getId());
        eventDTO.setOrganizer(event.getOrganizer());
        eventDTO.setName(event.getName());
        eventDTO.setDateFrom(event.getDateFrom());
        eventDTO.setDateTo(event.getDateTo());
        eventDTO.setCountryId(event.getCountry().getId());
        eventDTO.setVenue(event.getVenue());
        eventDTO.setUrl(event.getUrl());
        eventDTO.setComment(event.getComment());
        return eventDTO;
    }

    public List<EventDTO> toDTOList(List<Event> events) {
        List<EventDTO> orderDTOs = new ArrayList<>();
        for (Event each : events) {
            EventDTO eventDTO = toDTO(each);
            orderDTOs.add(eventDTO);
        }
        return orderDTOs;
    }

//    public OrderDTO toOrderDTOFromOrder(Order order) {
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(order.getId());
//        orderDTO.setItemId(order.getItem().getId());
//        orderDTO.setCartId(order.getCart().getId());
//        orderDTO.setAmount(order.getAmount());
//        orderDTO.setItemName(order.getItem().getName());
//        orderDTO.setItemPrice(order.getItem().getPrice());
//        return orderDTO;
//
//    }
//
//
//    public String jsonOrderDTOSimpleFormat(Order order) {
//        OrderDTO orderDTO = toOrderDTOFromOrder(order);
//        return "{" + System.lineSeparator() +
//                "  \"" + "id" + "\" : " + orderDTO.getId() + "," + System.lineSeparator() +
//                "  \"" + "itemId" + "\" : " + orderDTO.getItemId() + "," + System.lineSeparator() +
//                "  \"" + "cartId" + "\" : " + orderDTO.getCartId() + "," + System.lineSeparator() +
//                "  \"" + "amount" + "\" : " + orderDTO.getAmount() + System.lineSeparator() +
//                "}";
//    }
//
//    public String jsonOrderDTOSimpleFormatList(List<Order> orders) {
//        List<String> list = new ArrayList<>();
//        for (Order each : orders) {
//            String string = System.lineSeparator() + jsonOrderDTOSimpleFormat(each);
//            list.add(string);
//        }
//        return list.toString();
//    }
//
//    public String jsonOrderDTOItemFormat(Order order) {
//        OrderDTO orderDTO = toOrderDTOFromOrder(order);
//        return "{" + System.lineSeparator() +
//                "  \"" + "id" + "\" : " + orderDTO.getId() + "," + System.lineSeparator() +
//                "  \"" + "itemId" + "\" : " + orderDTO.getItemId() + "," + System.lineSeparator() +
//                "  \"" + "cartId" + "\" : " + orderDTO.getCartId() + "," + System.lineSeparator() +
//                "  \"" + "amount" + "\" : " + orderDTO.getAmount() + System.lineSeparator() +
//                "  \"" + "itemName" + "\" : " + orderDTO.getItemName() + System.lineSeparator() +
//                "  \"" + "itemPrice" + "\" : " + orderDTO.getItemPrice() + System.lineSeparator() +
//                "}";
//    }
//
//    public String jsonOrderDTOItemFormatList(List<Order> orders) {
//        List<String> list = new ArrayList<>();
//        for (Order each : orders) {
//            String string = System.lineSeparator() + jsonOrderDTOItemFormat(each);
//            list.add(string);
//        }
//        return list.toString();
//    }
}
