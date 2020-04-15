package ru.skillbranch.devintensive.models

import java.util.*

class TextMessage(
    id:String,
    from:User,
    chat:Chat,
    isIncoming: Boolean = false,
    date:Date = Date(),
    val text:String?
): BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String = "id - $id , from - $from ,chat - $chat ,isInc - $isIncoming, date - $date,text - $text"
}