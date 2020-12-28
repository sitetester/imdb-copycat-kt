package com.example.imdb.importer

import com.example.imdb.entity.NameBasics
import org.springframework.stereotype.Component

@Component
class NameBasicsImporter(private val bulkInsertHelper: BulkInsertHelper) : DatasetsImporter {

    private val path = "data/name_basics.tsv"

    override fun import() {

        var count = 0
        var importCount = 0
        var entities = mutableListOf<NameBasics>()

        getBufferedReaderReader(path).lines().skip(1).forEach { line ->

            count += 1
            val row = line.split("\t")

            entities.add(
                NameBasics(
                    nconst = row[0],
                    primaryName = row[1],
                    birthYear = row[2],
                    deathYear = row[3],
                    primaryProfession = row[4],
                    knownForTitles = row[5]
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