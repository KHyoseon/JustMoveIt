package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public List<Ticket> findTicketsByPhoneNumber(String phoneNumber);
}
