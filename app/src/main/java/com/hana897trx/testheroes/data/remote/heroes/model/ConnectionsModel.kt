package com.hana897trx.testheroes.data.remote.heroes.model

import com.google.gson.annotations.SerializedName

data class ConnectionsModel(
    @SerializedName("group-affiliation")
    val groupAffiliation: String = String()
)

fun ConnectionsModel.getMapState(): String {
    return "Group Affiliation: $groupAffiliation"
}