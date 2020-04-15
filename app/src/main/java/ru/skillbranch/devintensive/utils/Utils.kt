package ru.skillbranch.devintensive.utils

import java.lang.Exception
import java.util.*

object Utils {
    var letterTransliters = mapOf("а" to "a", "б" to "b", "в" to "v",
        "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z",
        "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m",
        "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t",
        "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch",
        "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "",
        "э" to "e", "ю" to "yu", "я" to "ya")

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String =" "): String {

        if (payload.split(" ").size < 2) return ""

        var firstPart = ""
        var lastPart = ""
        val (firstName, lastName) = payload.split(" ")

        for (i in firstName.toLowerCase(Locale.ROOT))
            firstPart += if (i.toString() in letterTransliters) letterTransliters[i.toString()] else i

        for (i in lastName.toLowerCase(Locale.ROOT))
            lastPart += if (i.toString() in letterTransliters) letterTransliters[i.toString()] else i

        return firstPart.capitalize() + divider + lastPart.capitalize()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        val firstLetter = firstName?.getOrNull(0)?.toUpperCase()
        val secondLetter = lastName?.getOrNull(0)?.toUpperCase()

        return if ((firstLetter in 'A'..'z' || firstLetter in 'А'..'я')&&
            (secondLetter in 'A'..'z' || secondLetter in 'А'..'я')) "$firstLetter$secondLetter"
        else if (firstLetter in 'A'..'z' || firstLetter in 'А'..'я') "$firstLetter"
        else null
    }
}