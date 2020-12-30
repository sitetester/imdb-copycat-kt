package com.example.imdb.controller

import com.example.imdb.repository.TitlesRepository
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/titles")
class TitlesController(private val titlesRepository: TitlesRepository) {

    @GetMapping("/titles/{title}/{page}")
    fun titles(
        @PathVariable("title") title: String,
        @PathVariable("page") page: Int,
    ): Any {
        val result = titlesRepository.findByPrimaryTitle(title, PageRequest.of(page, 10))
        return if (result.isEmpty()) {
            "No data found!"
        } else {
            result
        }
    }

    @GetMapping("/titlesLike/{title}/{page}")
    fun titlesLike(
        @PathVariable("title") title: String,
        @PathVariable("page") page: Int,
    ): Any {
        val result = titlesRepository.findByPrimaryTitleContainingIgnoreCase(title, PageRequest.of(page, 10))
        return if (result.isEmpty()) {
            "No data found!"
        } else {
            result
        }
    }

    @GetMapping("/genres/{genres}/{page}")
    fun genres(
        @PathVariable("genres") genre: String,
        @PathVariable("page") page: Int,
    ): Any {
        val result = titlesRepository.findByGenres(genre, PageRequest.of(page, 10))
        return if (result.isEmpty()) {
            "No data found!"
        } else {
            result
        }
    }
}