package com.example.imdb.command

import com.example.imdb.DatasetsImportManager
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class ImportCommand(private val datasetsImportManager: DatasetsImportManager) {

    @ShellMethod("Imports IMDB datasets")
    fun import() {
        return datasetsImportManager.manageImport()
    }
}