package com.example.imdb.importer

import com.example.imdb.entity.TitleBasics
import org.springframework.stereotype.Component

@Component
class TitleBasicsImporter(private val bulkInsertHelper: BulkInsertHelper) : DatasetsImporter {

    private val path = "data/title_basics.tsv"

    override fun import() {

        var count = 0
        var importCount = 0
        var entities = mutableListOf<TitleBasics>()

        getBufferedReaderReader(path).lines().skip(1).forEach { line ->

            count += 1
            val row = line.split("\t")

            entities.add(
                TitleBasics(
                    tconst = row[0],
                    titleType = row[1],
                    primaryTitle = row[2],
                    originalTitle = row[3],
                    isAdult = row[4],
                    startYear = row[5],
                    endYear = row[6],
                    runtimeMinutes = row[7],
                    genres = row[8],
                )
            )

            if (count == batchSize) {
                bulkInsertHelper.bulkInsert(entities)
                importCount += count

                println("[${this::class.java.simpleName}] Records inserted so far: $importCount")

                // reset
                count = 0
                entities = mutableListOf()
            }
        }

        // flush the pending entities when count < batchSize
        bulkInsertHelper.bulkInsert(entities)

        println("${this::class.java.simpleName} - DONE")
    }
}