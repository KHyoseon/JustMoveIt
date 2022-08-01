package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final EntityManager em;

    public void save(Movie movie) {
        em.persist(movie);
    }

    public Movie findOne(Long id) {
        return em.find(Movie.class, id);
    }

    public List<Movie> findAll() {
        return em.createQuery("select m from Movie m", Movie.class)
                .getResultList();
    }

    public List<Movie> findByName(String name) {
        return em.createQuery("select m from Movie m where m.name = :name", Movie.class)
                .setParameter("name", name)
                .getResultList();
    }
}
