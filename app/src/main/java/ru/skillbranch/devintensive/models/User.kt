package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    var id:String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit: Date? = Date(),
    var isOnline:Boolean = false
) {

    init {
        println(
            (if(lastName=="Doe") "Who is it? It's - $firstName $lastName"
            else "It's not Doe, but he's also good. Welcome, $firstName $lastName") + "\n")
    }

    fun printMe() = println("""
            id         = $id         
            firstName  = $firstName  
            lastName   = $lastName   
            avatar     = $avatar     
            rating     = $rating     
            respect    = $respect    
            lastVisit  = $lastVisit  
            isOnline   = $isOnline   
        
        """)

    companion object Factory {
        private var lastId = -1
        fun makeUser(fullName: String?): User{
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return  User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null)

    constructor(id: String) : this(id, firstName = "John", lastName="Doe")

    constructor(builder: Builder) : this(builder.id, builder.firstName,
    builder.lastName,
    builder.avatar,
    builder.rating,
    builder.respect,
    builder.lastVisit,
    builder.isOnline)

    class Builder() {
        var id:String = "${++lastId}"
        var firstName:String? = null
        var lastName:String? = null
        var avatar:String? = null
        var rating:Int = 0
        var respect:Int = 0
        var lastVisit: Date? = Date()
        var isOnline:Boolean = false

        fun id(s: String) = apply{ this.id = s }
        fun firstName(s: String) = apply{ this.firstName = s }
        fun lastName(s: String) = apply{ this.lastName = s }
        fun avatar(s: String) = apply{ this.avatar = s }
        fun rating(n: Int) = apply{ this.rating = n }
        fun respect(n: Int) = apply{ this.respect = n }
        fun lastVisit(d: Date) = apply{ this.lastVisit = d }
        fun isOnline(b: Boolean) = apply{ this.isOnline = b }
        fun build():User = User(this)
    }
}