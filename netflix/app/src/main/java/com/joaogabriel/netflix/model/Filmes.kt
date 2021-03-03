package com.joaogabriel.netflix.model

import com.joaogabriel.netflix.R
import java.util.*

data class Filmes(
    val capaFilme: Int
)

class FilmesBuilder {
    var capaFilme = 0

    fun build(): Filmes = Filmes(capaFilme)
}

fun filmes(block: FilmesBuilder.() -> Unit): Filmes = FilmesBuilder().apply(block).build()

fun addFilmes(): ArrayList<Filmes> {
    val filmes = ArrayList<Filmes>()

    filmes.add(filmes { capaFilme = R.drawable.filme1 })
    filmes.add(filmes { capaFilme = R.drawable.filme2 })
    filmes.add(filmes { capaFilme = R.drawable.filme3 })
    filmes.add(filmes { capaFilme = R.drawable.filme4 })
    filmes.add(filmes { capaFilme = R.drawable.filme5 })
    filmes.add(filmes { capaFilme = R.drawable.filme6 })
    filmes.add(filmes { capaFilme = R.drawable.filme7 })
    filmes.add(filmes { capaFilme = R.drawable.filme8 })
    filmes.add(filmes { capaFilme = R.drawable.filme9 })
    filmes.add(filmes { capaFilme = R.drawable.filme11 })
    filmes.add(filmes { capaFilme = R.drawable.filme12 })

    return filmes
}
