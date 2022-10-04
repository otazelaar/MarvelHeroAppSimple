package com.otaz.marvelheroappsimple.utils

import java.math.BigInteger
import java.security.MessageDigest

class constants {
    companion object {
        const val API_BASE_URL: String = "https://gateway.marvel.com:443/v1/public/"
        const val TIMESTAMP = "1"
        const val API_KEY: String = "a87af3707c37833708b86e521942f60f"
        private const val API_KEY_PRIVATE = "537a1695943787966388d85726ba6572e8556f0e"
        const val HASH = "5363be808595fac13b89e36865b0962c"
        const val LIMIT = 50
        const val SEARCH_CHARACTERS_TIME_DELAY = 500L
        var CHARID = 1011334

        // var hASH = "${hash()}"

        fun hash():String{
            val input = "$TIMESTAMP$API_KEY_PRIVATE$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}