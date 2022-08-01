package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepository {

    private final EntityManager em;

    public void save(Ticket ticket) {
        em.persist(ticket);
    }

    public Ticket findOne(Long id) {
        return em.find(Ticket.class, id);
    }

    public List<Ticket> findAll() {
        return em.createQuery("select t from Ticket t", Ticket.class)
                .getResultList();
    }

    public List<Ticket> findByTitle(String title) {
        return em.createQuery("select t from Ticket t where t.moviePlayingInfo.movie.title = :title", Ticket.class)
                .setParameter("title", title)
                .getResultList();
    }

    public List<Ticket> findByStartTime(String startTime) {
        return em.createQuery("select s from Ticket s where s.moviePlayingInfo.startTime = :startTime", Ticket.class)
                .setParameter("startTime", startTime)
                .getResultList();
    }
}
