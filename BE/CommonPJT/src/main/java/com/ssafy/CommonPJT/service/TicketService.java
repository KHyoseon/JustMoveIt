package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.domain.Ticket;
import com.ssafy.CommonPJT.dto.req.TicketDto;
import com.ssafy.CommonPJT.dto.res.TicketResDto;
import com.ssafy.CommonPJT.repository.MoviePlayingInfoRepository;
import com.ssafy.CommonPJT.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MoviePlayingInfoRepository moviePlayingInfoRepository;
    // 티켓 추가
    @Transactional
    public void save(TicketDto requestDto, Long moviePlayingInfoId) {
        MoviePlayingInfo info = moviePlayingInfoRepository.findById(moviePlayingInfoId).get();
        List<Ticket> tickets = ticketRepository.findAll();
        Ticket saveTicket = requestDto.toEntity(info);
        for (Ticket ticket : tickets) {
            if (ticket.getSeat().equals(saveTicket.getSeat())) {
                throw new IllegalStateException("이미 예약된 좌석입니다.");
            }
        }
        ticketRepository.save(saveTicket);
    }

    // 티켓 리스트 조회
    @Transactional(readOnly = true)
    public List<TicketResDto> findTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketResDto::new).collect(Collectors.toList());
    }

    // 특정 티켓 조회
    @Transactional(readOnly = true)
    public List<TicketResDto> findByNum(String phoneNumber) {
        List<Ticket> tickets = ticketRepository.findAll();
        List<Ticket> ticketsByNum = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getPhoneNumber().equals(phoneNumber)) {
                ticketsByNum.add(ticket);
            }
        }
        return ticketsByNum.stream().map(TicketResDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Ticket target = ticketRepository.findById(id).get();
        ticketRepository.delete(target);
    }
}
