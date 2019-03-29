package es.diegogargallotarin.cloudpowerbank.common.utils

import android.util.Log
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import es.diegogargallotarin.cloudpowerbank.common.JSON_OBJECT_MAPPER
import java.io.IOException

object JsonObjectMapper {

    private val mapper = ObjectMapper()

    fun <T> fromJson(json: String?, clazz: Class<T>): T? {
        if (json != null) {
            try {
                return mapper.readValue(json, clazz)
            } catch (e: IOException) {
                Log.e(JSON_OBJECT_MAPPER, "Cannot parse '" + json
                        + "' to class '" + clazz.simpleName + "'", e)
            }

        }
        return null
    }

    fun toJson(`val`: Any): String? {
        try {
            return mapper.writeValueAsString(`val`)
        } catch (e: JsonProcessingException) {
            Log.e("Cannot convert '$`val`'  json", e.toString())
        }

        return null
    }
}