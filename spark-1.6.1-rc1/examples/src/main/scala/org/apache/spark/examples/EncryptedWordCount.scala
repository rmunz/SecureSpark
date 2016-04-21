package org.apache.spark.examples

import scala.math.random

import org.apache.spark._

object EncryptedWordCount {
	def main(args: Array[String]) {
		// Set up spark
		val conf = new SparkConf().setAppName("Encrypted Word Count")
		val spark = new SparkContext(conf)
		// Set up enryption key and iv
		val keyGen = javax.crypto.KeyGenerator.getInstance("AES")
		keyGen.init(128)
		val key = keyGen.generateKey()
		val iv = Encryption.genIV()
		// Get start timestamp
		val startTime = new java.util.Date()
		// Create RDD from text file
		val r = spark.textFile("README.md")
		// Encrypt RDD
		val encrypted = r.encrypt(key, iv)
		encrypted.persist()
		//Decrypt RDD
		val decrypted = encrypted.decrypt(key, iv)
		// Get element count
		val count = decrypted.count()
		// Record time
		val endTime = new java.util.Date()
		val time = endTime.getTime() - startTime.getTime();
		println("Count is: " + count + " took: " + time);
		spark.stop()
	}
}
