package com.joaogabriel.marvel.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MarvelHashGenerator {

    fun generate(timestamp: Long, privateKey: String, publicKey: String): String {
        return try {
            val concatResult = timestamp.toString() + privateKey + publicKey
            md5(concatResult)
        } catch (e: Exception) {
            ""
        }
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun md5(s: String): String {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(s.toByteArray())
        val messageDigest = digest.digest()
        val bigInt = BigInteger(1, messageDigest)
        var hashText = bigInt.toString(16)
        while (hashText.length < 32) {
            hashText = "0$hashText"
        }
        return hashText
    }
}