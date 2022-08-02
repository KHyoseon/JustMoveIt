package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.domain.Ticket;
import com.ssafy.CommonPJT.dto.req.TicketDto;
import com.ssafy.CommonPJT.dto.res.TicketResDto;
import com.ssafy.CommonPJT.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("TicketController")
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;


    /**
    * GET 매핑
    */
    @ApiOperation(value = "티켓 목록", notes = "티켓 목록을 반환", response = Ticket.class)
    @GetMapping("/")
    public ResponseEntity<?> getTicketList() {
        return new ResponseEntity<>(ticketService.findTickets(), HttpStatus.OK);
    }

    @ApiOperation(value = "티켓", notes = "티켓 정보를 출력한다.", response = Ticket.class)
    @GetMapping("/{phoneNumber}")
    public List<TicketResDto> getTicketByNum(@PathVariable String phoneNumber) {
        return ticketService.findByNum(phoneNumber);
    }

    /**
     * POST 매핑
     */
    @ApiOperation(value = "티켓 예매", notes = "티켓을 예매한다.", response = Ticket.class)
    @PostMapping("/{moviePlayingInfoId}")
    public void save(@RequestBody TicketDto ticket, @PathVariable Long moviePlayingInfoId) {
        ticketService.save(ticket, moviePlayingInfoId);
    }


    /**
     * DELETE 매핑
     */
    @ApiOperation(value = "티켓 취소", notes = "티켓 예매를 취소한다.", response = Ticket.class)
    @DeleteMapping("/{id}")
    public void cancelTicket(@PathVariable Long id) {
        ticketService.delete(id);
    }
}
