package com.hana897trx.testheroes.data.remote.heroes.model

data class HeroModel(
    val id: Long = 0L,
    val name: String = String(),
    val biography: BiographyModel = BiographyModel(),
    val image: ImageModel = ImageModel()
)
