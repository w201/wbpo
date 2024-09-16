package one.codium.wbpo.core.db.entity.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class CountryConverter {

    @TypeConverter
    fun listString(data: List<String>?): String? {
        return data?.let {Gson().toJson(it)}
    }

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let {Gson().fromJson(value, Array<String>::class.java).toList()}
    }

}
