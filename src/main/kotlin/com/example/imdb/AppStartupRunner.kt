package com.example.imdb

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class AppStartupRunner(private val datasetsImporter: DatasetsImportManager) {

    @PostConstruct
    fun init() {
        datasetsImporter.manageImport()
    }
}