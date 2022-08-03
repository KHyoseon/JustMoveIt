package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
