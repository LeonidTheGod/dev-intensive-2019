package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    var TranslMap = mapOf("а" to "a", "б" to "b", "в" to "v",
        "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z",
        "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m",
        "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t",
        "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch",
        "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "",
        "э" to "e", "ю" to "yu", "я" to "ya")

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts = fullName?.trim()?.split(" ")

        val firstName = if (parts?.getOrNull(0) != "") parts?.getOrNull(0) else null
        val lastName = if (parts?.getOrNull(1) != "") parts?.getOrNull(1) else null
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String =" "): String {
        var word = ""

        for (i in payload)
            word += when{
                (i.isUpperCase()) -> TranslMap[i.toString().toLowerCase(Locale.ROOT)]?.capitalize() ?: i.toString()
                (i.toString() in TranslMap) -> TranslMap[i.toString()]
                else -> i}
        return word.replace(Regex(" +"), divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        val firstLetter = firstName?.trim()?.getOrNull(0)?.toUpperCase()
        val secondLetter = lastName?.trim()?.getOrNull(0)?.toUpperCase()

        return if ((firstLetter in 'A'..'Z' || firstLetter in 'А'..'Я')&&
            (secondLetter in 'A'..'Z' || secondLetter in 'А'..'Я')) "$firstLetter$secondLetter"
        else if (firstLetter in 'A'..'Z' || firstLetter in 'А'..'Я') "$firstLetter"
        else if (secondLetter in 'A'..'Z' || secondLetter in 'А'..'Я') "$secondLetter"
        else null
    }
}