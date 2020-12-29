package com.example.imdb.controller

import com.example.imdb.repository.TitleBasicsRepository
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/titles")
class TitlesController(private val titleBasicsRepository: TitleBasicsRepository) {

    @GetMapping("/genres/{genre}/{page}")
    fun genres(
        @PathVariable("genre") genre: String,
        @PathVariable("page") page: Int,
    ): Any {
        val result = titleBasicsRepository.findByGenres(genre, PageRequest.of(page, 10))
        return if (result.isEmpty()) {
            "No data found!"
        } else {
            result
        }
    }
}