package com.example.imdb.repository

import com.example.imdb.entity.TitleBasics
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TitleBasicsRepository : JpaRepository<TitleBasics, Long> {

    // https://thorben-janssen.com/jpql/
    @Query("SELECT tb, tr FROM TitleBasics tb, TitleRatings tr WHERE tb.tconst = tr.tconst AND tb.genres = :genres ORDER BY tr.averageRating")
    fun findByGenres(genres: String, pageable: Pageable): List<Any>

}