package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
