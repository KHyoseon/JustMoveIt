package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.dto.Ticket.TicketResDto;
import com.ssafy.CommonPJT.dto.Ticket.TicketSaveDto;
import com.ssafy.CommonPJT.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api("TicketController")
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;


    /**
     * GET 매핑
     */
    @ApiOperation(value = "티켓 리스트", notes = "티켓 리스트를 반환")
    @GetMapping
    public ResponseEntity<List<TicketResDto>> getTicketList() {
        log.info("티켓 정보 리스트를 조회합니다.");
        return new ResponseEntity<>(ticketService.findTickets(), HttpStatus.OK);
    }

    @ApiOperation(value = "티켓 조회(휴대폰)", notes = "휴대폰 번호로 티켓 정보를 출력한다.")
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<List<TicketResDto>> getTicketByNum(@PathVariable String phoneNumber) {
        log.info("티켓 정보를 휴대폰 번호로 조회합니다.");
        return new ResponseEntity<>(ticketService.findByNum(phoneNumber), HttpStatus.OK);
    }

    @ApiOperation(value = "티켓 조회(id)", notes = "ticketId로 티켓 정보를 출력한다.")
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketResDto> getTicketById(@PathVariable Long ticketId) {
        log.info("티켓 정보를 ticketId로 조회합니다.");
        return new ResponseEntity<>(ticketService.findById(ticketId), HttpStatus.OK);
    }


    /**
     * POST 매핑
     */
    @ApiOperation(value = "티켓 예매", notes = "티켓을 예매한다.")
    @PostMapping
    public ResponseEntity<TicketResDto> save(@RequestBody TicketSaveDto ticket) {
        log.info("티켓 정보를 저장합니다.");
        return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.OK);
    }


    /**
     * DELETE 매핑
     */
    @ApiOperation(value = "티켓 취소", notes = "티켓 예매를 취소한다.")
    @DeleteMapping("/{id}")
    public void cancelTicket(@PathVariable Long id) {
        ticketService.delete(id);
        log.info("티켓 정보를 삭제합니다.");
    }
}
