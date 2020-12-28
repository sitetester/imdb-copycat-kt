package com.example.imdb

import com.example.imdb.importer.*
import org.springframework.stereotype.Component

@Component
class DatasetsImportManager(
    titleRatingsImporter: TitleRatingsImporter,
    titlePrincipalsImporter: TitlePrincipalsImporter,
    nameBasicsImporter: NameBasicsImporter,
    titleBasicsImporter: TitleBasicsImporter,
    titleCrewImporter: TitleCrewImporter,
) {

    private val importers = mutableListOf<DatasetsImporter>()

    init {
        importers.add(titleRatingsImporter)
        importers.add(titlePrincipalsImporter)
        importers.add(nameBasicsImporter)
        importers.add(titleBasicsImporter)
        importers.add(titleCrewImporter)
    }

    fun manageImport() {

        importers.parallelStream().forEach {
            it.import()
        }
    }
}