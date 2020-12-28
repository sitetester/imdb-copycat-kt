package com.example.imdb.importer

import com.example.imdb.entity.TitlesCrew
import org.springframework.stereotype.Component

@Component
class TitleCrewImporter(private val bulkInsertHelper: BulkInsertHelper) : DatasetsImporter {

    private val path = "data/title_crew.tsv"

    override fun import() {

        var count = 0
        var importCount = 0
        var entities = mutableListOf<TitlesCrew>()

        getBufferedReaderReader(path).lines().skip(1).forEach { line ->
            count += 1
            val row = line.split("\t")

            entities.add(
                TitlesCrew(
                    tconst = row[0],
                    directors = row[1],
                    writers = row[2]
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