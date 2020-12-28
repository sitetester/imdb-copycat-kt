package com.example.imdb.importer

import java.io.BufferedReader
import java.io.InputStreamReader

interface DatasetsImporter {

    val batchSize: Int
        get() = 25000

    fun import()

    fun getBufferedReaderReader(path: String): BufferedReader {
        return BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream(path)))
    }
}