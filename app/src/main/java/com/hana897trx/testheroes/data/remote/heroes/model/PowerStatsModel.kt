package com.hana897trx.testheroes.data.remote.heroes.model

data class PowerStatsModel(
    val intelligence: Int = 0,
    val strength: Int = 0,
    val speed: Int = 0,
    val durability: Int = 0,
    val power: Int = 0,
    val combat: Int = 0
)

fun PowerStatsModel.getMapState(): String {
    return "Intelligence: $intelligence\nStrength: $strength\nSpeed: $speed\nDurability: $durability\nPower: $power\nCombat: $combat"
}