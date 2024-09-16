package one.codium.wbpo.core.ds.settings

internal interface SettingsDataSource {
    fun setValue(key: String, value: String)
    fun getValue(key: String): String?
}
