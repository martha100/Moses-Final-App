package com.example.residenthomes

class Amenity {
    var breakfast:String = ""
    var lunch:String = ""
    var supper:String = ""
    var service:String = ""
    var services:String = ""
    var breakfasttime:String = ""
    var lunchtime:String = ""
    var suppertime:String = ""
    var id:String = ""

    constructor(breakfast: String, lunch: String, supper: String, service: String,
                services: String, breakfasttime:String, lunchtime:String, suppertime:String, id: String){
        this.breakfast = breakfast
        this.lunch = lunch
        this.supper = supper
        this.service = service
        this.services = services
        this.breakfasttime = breakfasttime
        this.lunchtime = lunchtime
        this.suppertime = suppertime
        this.id = id
    }
    constructor()
}