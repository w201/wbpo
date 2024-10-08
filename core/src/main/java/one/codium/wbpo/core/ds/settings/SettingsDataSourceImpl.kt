package one.codium.wbpo.core.ds.settings

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

internal class SettingsDataSourceImpl @Inject constructor(
    private val prefs: SharedPreferences
) : SettingsDataSource {
    override fun setValue(key: String, value: String) {
        prefs.edit {
            putString(key, value)
        }
    }

    override fun getValue(key: String): String?  = prefs.getString(key, null)
}
