package ru.skillbranch.devintensive.models

import java.util.*

class ImageMessage(
    id:String,
    from:User,
    chat:Chat,
    isIncoming: Boolean = false,
    date:Date = Date(),
    val image:String?
): BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String = "id - $id, from - $from, chat - $chat, isInc - $isIncoming, date - $date, image - $image"
}