package com.example.climatico
import java.text.Normalizer

object Utils {
    fun capitalizeFirstLetter(string: String): String {
        if (string.isEmpty()) {
            return ""
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1)
    }

    fun removeAccent(string : String) : String{
        val normalized = Normalizer.normalize(string, Normalizer.Form.NFD)
        return normalized.replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")

    }
}