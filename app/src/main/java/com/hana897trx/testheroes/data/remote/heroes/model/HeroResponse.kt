package com.hana897trx.testheroes.data.remote.heroes.model

data class HeroResponse (
    val response: String = String(),
    val id: Long = 0L,
    val name: String = String(),
    val powerStats: PowerStatsModel = PowerStatsModel(),
    val biography: BiographyModel = BiographyModel(),
    val work: WorkModel = WorkModel(),
    val image: ImageModel = ImageModel(),
    val connections: ConnectionsModel = ConnectionsModel()
)

fun HeroResponse.toHeroesResponse() : HeroesResponse {
    return HeroesResponse(
        response = response,
        results = listOf(
            HeroModel(
                id,
                name,
                biography,
                image
            )
        )
    )
}