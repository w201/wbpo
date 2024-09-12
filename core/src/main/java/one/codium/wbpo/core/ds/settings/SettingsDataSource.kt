package one.codium.wbpo.core.ds.settings

interface SettingsDataSource {
    fun setValue(key: String, value: String)
    fun getValue(key: String): String?
}
