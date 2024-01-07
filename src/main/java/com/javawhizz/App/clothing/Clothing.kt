package com.javawhizz.App.clothing

import lombok.Data

@Data
class Clothing(val pictures: List<String> = emptyList<String>(), val objectName: String, val brandName: String, val price: String, val link: String, val firebaseId: String) {
}