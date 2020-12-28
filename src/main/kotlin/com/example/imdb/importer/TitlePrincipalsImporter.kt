package com.example.imdb.importer

import com.example.imdb.entity.TitlePrincipals
import org.springframework.stereotype.Component

@Component
class TitlePrincipalsImporter(private val bulkInsertHelper: BulkInsertHelper) : DatasetsImporter {

    private val path = "data/title_principals.tsv"

    override fun import() {

        var count = 0
        var importCount = 0
        var entities = mutableListOf<TitlePrincipals>()

        getBufferedReaderReader(path).lines().skip(1).forEach { line ->

            count += 1
            val row = line.split("\t")

            entities.add(
                TitlePrincipals(
                    tconst = row[0],
                    ordering = row[1],
                    nconst = row[2],
                    category = row[3],
                    job = row[4],
                    characters = row[5],
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