package ru.skillbranch.devintensive.extentions

fun String.truncate(trunc: Int = 16): String{
    var newString = ""
    val mainString = this.trim()

    return if (mainString.length > trunc) {
        for (i in 0 until trunc - 1){
            newString += mainString[i]
        }
        newString += if (mainString[trunc - 1] == ' ') "..." else mainString[trunc - 1] + "..."
        newString
    }
    else mainString
}

fun String.stripHtml(): String {
    var skipIterator = 0
    var newString = ""

    while (true) {
        do {
            skipIterator++
        } while (this[skipIterator] != '>')
        if (this[skipIterator + 1] != '<') break
    }

    while (this[++skipIterator] != '<'){

        if (this[skipIterator] == ' ') {
            newString += ' '
            while (this[skipIterator] == ' ')
                skipIterator++
            if (this[skipIterator] == '<') break
        }
        newString += this[skipIterator]
    }
    return newString
}