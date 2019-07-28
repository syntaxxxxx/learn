package com.syntax.learn.common.utils

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private inline fun <T> SharedPreferences.delegate(
    key: String?,
    defaultValue: T,
    crossinline getter: SharedPreferences.(String, T) -> T,
    crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor

): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
            getter(key ?: property.name, defaultValue)

        override fun setValue(
            thisRef: Any, property: KProperty<*>,
            value: T
        ) =
            edit().setter(key ?: property.name, value).apply()
    }
}

fun SharedPreferences.string(key: String, default: String) = delegate(
    key,
    default,
    SharedPreferences::getString,
    SharedPreferences.Editor::putString
)

fun SharedPreferences.stringNullable(key: String) = delegate(
    key,
    null,
    SharedPreferences::getString,
    SharedPreferences.Editor::putString
)

fun SharedPreferences.boolean(key: String, default: Boolean = false) = delegate(
    key,
    default,
    SharedPreferences::getBoolean,
    SharedPreferences.Editor::putBoolean
)