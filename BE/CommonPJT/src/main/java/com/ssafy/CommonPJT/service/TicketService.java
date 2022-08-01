package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.Ticket;
import com.ssafy.CommonPJT.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    // 티켓 추가
    @Transactional
    public Long insert(Ticket ticket) {
        validateDuplicateSeat(ticket);
        ticketRepository.save(ticket);
        return ticket.getId();
    }

    private void validateDuplicateSeat(Ticket ticket) {
        List<Ticket> findTickets = ticketRepository.findByStartTime(ticket.getMoviePlayingInfo().getStartTime());
        for (Ticket findTicket : findTickets) {
            if (findTicket.getSeat().equals(ticket.getSeat())) {
                throw new IllegalStateException("Seat already booked!");
            }
        }
    }

    // 티켓 리스트 조회
    @Transactional(readOnly = true)
    public List<Ticket> findTickets() {
        return ticketRepository.findAll();
    }

    // 특정 티켓 조회
    @Transactional(readOnly = true)
    public Ticket findOne(Long ticketId) {
        return ticketRepository.findOne(ticketId);
    }
}
