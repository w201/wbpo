package one.codium.wbpo.core.ds.fav

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import javax.inject.Inject

class FavDSImpl @Inject constructor(val prefs: SharedPreferences) : FavDS {
    private val favKey = "favKey"

    private val favCache = mutableSetOf<Int>()

    override fun getFavMoviesId(): Set<Int> {
        if (favCache.isEmpty()) {
            favCache.addAll(loadKeys())
        }
        return favCache
    }

    private fun loadKeys(): Set<Int> {
        val keysGson = prefs.getString(favKey, null)
        return if (keysGson != null) {
            return Gson().fromJson(keysGson, Array<Int>::class.java).toSet()
        } else {
            emptySet()
        }
    }

    override fun setFavMovie(id: Int) {
        val keys = getFavMoviesId()+id
        favCache.add(id)
        prefs.edit {
            putString(favKey, Gson().toJson(keys))
        }
    }

    override fun removeFavMovie(id: Int) {
        val keys = getFavMoviesId()-id
        favCache.remove(id)
        prefs.edit {
            putString(favKey, Gson().toJson(keys))
        }
    }
}
