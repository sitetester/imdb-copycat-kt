package com.example.imdb.entity

import javax.persistence.*

@Entity
class NameBasics(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var nconst: String,
    val primaryName: String,
    val birthYear: String,
    val deathYear: String,
    val primaryProfession: String,
    val knownForTitles: String
)

@Entity
data class TitlesCrew(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val tconst: String,

    @Column(columnDefinition = "text")
    val directors: String,

    @Column(columnDefinition = "text")
    val writers: String
)

@Entity
data class TitleRatings(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val tconst: String,
    val averageRating: Double,
    val numVotes: Int
)

@Entity
@Table(name = "title_basics", indexes = [Index(columnList = "genres")])
data class TitleBasics(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val tconst: String,
    val titleType: String,

    @Column(columnDefinition = "text")
    val primaryTitle: String,

    @Column(columnDefinition = "text")
    val originalTitle: String,

    val isAdult: String,
    val startYear: String,
    val endYear: String,
    val runtimeMinutes: String,
    val genres: String
)

data class TitleBasicsWithRatings(
    val titleBasics: TitleBasics,
    val titleRatings: TitleRatings
)

data class TitleBasicsWithNameBasics(
    val titleBasics: TitleBasics,
    val nameBasics: NameBasics
)

@Entity
data class TitlePrincipals(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val tconst: String,
    val ordering: String,
    val nconst: String,
    val category: String,
    val job: String,

    @Column(columnDefinition = "text")
    val characters: String
)
