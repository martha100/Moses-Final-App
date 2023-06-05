package com.example.residenthomes

class Room {
    var hostelnames:String = ""
    var studentname:String = ""
    var phonenumber:String = ""
    var room:String = ""
    var entrydate:String = ""
    var id:String = ""

    constructor(
        hostelnames: String,
        studentname: String,
        phonenumber: String,
        room: String,
        entrydate: String,
        id:String
    ) {
        this.hostelnames = hostelnames
        this.studentname = studentname
        this.phonenumber = phonenumber
        this.room = room
        this.entrydate = entrydate
        this.id = id
    }
    constructor()
}