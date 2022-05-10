package br.com.rorizinfo.peladaorganizada.data.persistence

import android.content.Context
import br.com.rorizinfo.peladaorganizada.ui.ext.SEPARATOR
import br.com.rorizinfo.peladaorganizada.ui.ext.stringToList

class LocalPreference(private val context: Context) {
    
    fun save(typePreference: BasePreference, value: String) {
        val shared = context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE)
        shared.edit().putString(typePreference.key, value).apply()
    }
    
    fun save(typePreference: BasePreference, value: Long) {
        val shared = context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE)
        shared.edit().putLong(typePreference.key, value).apply()
    }
    
    fun saveInt(typePreference: BasePreference, value: Int) {
        val shared = context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE)
        shared.edit().putInt(typePreference.key, value).apply()
    }
    
    fun save(typePreference: BasePreference, value: Boolean) {
        val shared = context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE)
        shared.edit().putBoolean(typePreference.key, value).apply()
    }
    
    fun saveList(typePreference: BasePreference, value: List<String>?) {
        val shared = context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE)
        shared.edit().putString(typePreference.key, value?.joinToString( separator = SEPARATOR)).apply()
    }
    
    fun addList(typePreference: BasePreference, value: MutableList<String>?) {
        value?.let {
            val currentList = getList(typePreference).apply { addAll(value) }
            saveList(typePreference, currentList)
        }
    }
    
    fun getList(typePreference: BasePreference): MutableList<String> {
        return context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).run {
            getString(typePreference.key, null)?.stringToList() ?: mutableListOf()
        }
    }
    
    fun getBoolean(typePreference: BasePreference, defaultValue: Boolean = false): Boolean {
        return context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).run {
            getBoolean(typePreference.key, defaultValue)
        }
    }
    
    fun get(key: BasePreference, defaultValue: String = ""): String {
        return context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).run {
            getString(key.key, defaultValue) ?: String()
        }
    }
    
    fun getInt(key: BasePreference, defaultValue: Int = 0): Int {
        return context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).run {
            getInt(key.key, defaultValue)
        }
    }
    
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).run {
            getLong(key, defaultValue)
        }
    }
    
    fun getLong(typePreference: BasePreference, defaultValue: Long = 0L): Long {
        return context.getSharedPreferences(KEY_SHARE_PREFERENCES, Context.MODE_PRIVATE).run {
            getLong(typePreference.key, defaultValue)
        }
    }
    
    companion object {
        private const val KEY_SHARE_PREFERENCES = "SharedPreferencesKey"
        private const val PREFS_FILENAME = "br.com.pelada"
        private const val DEVICE_ID = "device-id"
        
    }
}