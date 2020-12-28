package com.example.imdb.importer

import com.example.imdb.entity.TitleRatings
import org.springframework.stereotype.Component

@Component
class TitleRatingsImporter(private val bulkInsertHelper: BulkInsertHelper) : DatasetsImporter {

    private val path = "data/title_ratings.tsv"

    override fun import() {

        var count = 0
        var importCount = 0
        var entities = mutableListOf<TitleRatings>()

        getBufferedReaderReader(path).lines().skip(1).forEach { line ->

            count += 1
            val row = line.split("\t")

            entities.add(
                TitleRatings(
                    tconst = row[0],
                    averageRating = row[1].toDouble(),
                    numVotes = row[2].toInt()
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