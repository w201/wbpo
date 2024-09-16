package one.codium.wbpo.core.db.entity.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

internal class CountryConverter {

    @TypeConverter
    fun listString(data: List<String>?) = data?.let { Gson().toJson(it) }

    @TypeConverter
    fun fromString(value: String?) = value?.let { Gson().fromJson(value, Array<String>::class.java).toList() }

}
