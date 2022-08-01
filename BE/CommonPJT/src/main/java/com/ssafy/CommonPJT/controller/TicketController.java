package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.Ticket;
import com.ssafy.CommonPJT.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("TicketController")
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @ApiOperation(value = "티켓 목록", notes = "티켓 목록을 반환", response = Ticket.class)
    @GetMapping("/")
    public List<Ticket> getTicketList() {
        return ticketService.findTickets();
    }

    @ApiOperation(value = "티켓", notes = "티켓 정보를 출력한다.", response = Ticket.class)
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {

        return ticketService.findOne(id);
    }
}
