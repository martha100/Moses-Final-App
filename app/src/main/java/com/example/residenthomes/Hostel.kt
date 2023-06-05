package com.example.residenthomes

class Hostel {
    var image: String = ""
    var hostel: String = ""
    var gender: String = ""
    var id: String = ""

    constructor(image: String,hostel: String,gender: String, id: String) {
        this.image = image
        this.hostel = hostel
        this.id = id
        this.gender = gender
    }
    constructor()
}