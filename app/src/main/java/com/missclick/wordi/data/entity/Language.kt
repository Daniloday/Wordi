package com.missclick.wordi.data.entity

sealed class Language(val code : String){
    object English : Language("en")

}
