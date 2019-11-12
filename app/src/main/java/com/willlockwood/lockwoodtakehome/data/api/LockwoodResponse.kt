package com.willlockwood.lockwoodtakehome.data.api

import com.google.gson.GsonBuilder
import com.willlockwood.lockwoodtakehome.data.model.Lockwood

class LockwoodResponse {

    var hits: List<Lockwood> = ArrayList()

    companion object {
        // TODO: I keep this here in case apis I start working with are uncooperative; can probably be removed
        fun parseJSON(response: String): LockwoodResponse {
            val gson = GsonBuilder().create()
            return gson.fromJson(response, LockwoodResponse::class.java)
        }
    }
}