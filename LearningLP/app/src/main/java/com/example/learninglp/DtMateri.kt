package com.example.learninglp

class DtMateri{
    var desc: String? = null
    var img: String? = null
    var language: String? = null

    constructor(){

    }
    constructor(desc: String?, img: String?, language: String?){
        this.desc = desc
        this.img = img
        this.language = language
    }
}