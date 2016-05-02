package org.apache.spark.examples

import scala.math.random

import org.apache.spark._

import java.io._

object ModifiedWordCount {
	// def singleTest(text: String, output: PrintWriter, toEncrypt: Boolean) 
	// def multipleTest(text: String, output: PrintWriter, toEncrypt: Boolean, numFiles: Int) 
	def main(args: Array[String]) 
	{
		// Need to setup enviroment and then call tests here
		val writer = new PrintWriter(new File("output.txt" ))
		if (args(1).toBoolean) 
		{
			writer.write("===================================\n")
			writer.write("1 MB File Test\n")
			writer.write("===================================\n")
			singleTest("mobydick.txt",writer,args(0).toBoolean)

			writer.write("===================================\n")
			writer.write("500 MB File Test\n")
			writer.write("===================================\n")
			singleTest("fiveHundMB.txt",writer,args(0).toBoolean)

			writer.write("===================================\n")
			writer.write("2 GB File Test\n")
			writer.write("===================================\n")
			singleTest("twoGB.txt",writer,args(0).toBoolean)

			writer.write("===================================\n")
			writer.write("100, 1 MB File Test\n")
			writer.write("===================================\n")
			multipleTest("mobydick.txt",writer,args(0).toBoolean, 100)

			writer.write("===================================\n")
			writer.write("500, 1 MB File Test\n")
			writer.write("===================================\n")
			multipleTest("mobydick.txt",writer,args(0).toBoolean, 500)
		}
		else
		{
			writer.write("===================================\n")
			writer.write("5 GB File Test\n")
			writer.write("===================================\n")
			singleTest("fiveGB.txt",writer,args(0).toBoolean)
		}

		writer.close()
		
	}
	def singleTest(text: String, output: PrintWriter, toEncrypt: Boolean) =
	{
		// Set up spark
		val conf = new SparkConf().setAppName("Encrypted Word Count")
		val spark = new SparkContext(conf)
		// Set up enryption key and iv
		val keyGen = javax.crypto.KeyGenerator.getInstance("AES")
		keyGen.init(128)
		val key = keyGen.generateKey()
		val iv = Encryption.genIV()
		output.write("Test Start\n")

		var count: Long = 0;
		var encryptStartTime = new java.util.Date();
		var encryptEndTime = new java.util.Date();
		var decryptStartTime = new java.util.Date();
		var decryptEndTime = new java.util.Date();
		var countStartTime = new java.util.Date();
		var countEndTime = new java.util.Date();
		var startTime = new java.util.Date();
		var endTime = new java.util.Date();

		if (toEncrypt) 
		{
			// Create RDD from text file
			val r = spark.textFile(text)
			// Encrypt RDD
			val encrypted = r.encrypt(key, iv)
			encrypted.persist()
			/////////////////Add runJob command here
			spark.runJob(encrypted, (iter: Iterator[_]) => {})

			startTime = new java.util.Date()

			decryptStartTime = new java.util.Date()
			val decrypted = encrypted.decrypt(key, iv)
			/////////////////Add runJob command here.
			spark.runJob(decrypted, (iter: Iterator[_]) => {})
			decryptEndTime = new java.util.Date()

			countStartTime = new java.util.Date()
			count = decrypted.count()
			/////////////////Add runJob command here
			spark.runJob(decrypted, (iter: Iterator[_]) => {})
			countEndTime = new java.util.Date()

			encryptStartTime = new java.util.Date()
			encrypted = decrypted.encrypt(key, iv)
			/////////////////Add runJob command here.
			spark.runJob(encrypted, (iter: Iterator[_]) => {})
			encryptEndTime = new java.util.Date()

			endTime = new java.util.Date()
		}
		else 
		{
			// Create RDD from text file
			val r = spark.textFile(text)
			
			startTime = new java.util.Date()

			countStartTime = new java.util.Date()
			count = r.count()
			/////////////////Add runJob command here
			spark.runJob(r, (iter: Iterator[_]) => {})
			countEndTime = new java.util.Date()

			endTime = new java.util.Date()
		}
		val encryptTime = encryptEndTime.getTime() - encryptStartTime.getTime();
		val decryptTime = decryptEndTime.getTime() - decryptStartTime.getTime();
		val countTime = countEndTime.getTime() - countStartTime.getTime();
		val totalTime = endTime.getTime() - startTime.getTime();

		output.write("Word Count is: " + count + "\n");
		output.write("Encryption Time is: " + encryptTime + " ms\n")
		output.write("Decryption Time is: " + decryptTime + " ms\n")
		output.write("Count Time is: " + countTime + " ms\n")
		output.write("Total Time is: " + totalTime + " ms\n")
		spark.stop()
		output.write("Test Finish\n")
	}

	def multipleTest(text: String, output: PrintWriter, toEncrypt: Boolean, numFiles: Int) =
	{
		// Set up spark
		val conf = new SparkConf().setAppName("Encrypted Word Count")
		val spark = new SparkContext(conf)
		// Set up enryption key and iv
		val keyGen = javax.crypto.KeyGenerator.getInstance("AES")
		keyGen.init(128)
		val key = keyGen.generateKey()
		val iv = Encryption.genIV()

		var encryptTime: Long = 0
		var decryptTime: Long = 0
		var countTime: Long = 0
		var totalTime: Long = 0
		var totalCount: Long = 0
		var count: Long = 0;

		output.write("Test Start\n")
		for (i: Int <- 1 to numFiles)
		{
			var encryptStartTime = new java.util.Date();
			var encryptEndTime = new java.util.Date();
			var decryptStartTime = new java.util.Date();
			var decryptEndTime = new java.util.Date();
			var countStartTime = new java.util.Date();
			var countEndTime = new java.util.Date();
			var startTime = new java.util.Date();
			var endTime = new java.util.Date();

			if (toEncrypt) 
			{
				// Create RDD from text file
				val r = spark.textFile(text)
				// Encrypt RDD
				val encrypted = r.encrypt(key, iv)
				encrypted.persist()
				/////////////////Add runJob command here
				spark.runJob(encrypted, (iter: Iterator[_]) => {})

				startTime = new java.util.Date()

				decryptStartTime = new java.util.Date()
				val decrypted = encrypted.decrypt(key, iv)
				/////////////////Add runJob command here.
				spark.runJob(decrypted, (iter: Iterator[_]) => {})
				decryptEndTime = new java.util.Date()

				countStartTime = new java.util.Date()
				count = decrypted.count()
				/////////////////Add runJob command here
				spark.runJob(decrypted, (iter: Iterator[_]) => {})
				countEndTime = new java.util.Date()

				encryptStartTime = new java.util.Date()
				encrypted = decrypted.encrypt(key, iv)
				/////////////////Add runJob command here.
				spark.runJob(encrypted, (iter: Iterator[_]) => {})
				encryptEndTime = new java.util.Date()

				endTime = new java.util.Date()

				totalCount += count;
				encryptTime += encryptEndTime.getTime() - encryptStartTime.getTime();
				decryptTime += decryptEndTime.getTime() - decryptStartTime.getTime();
				countTime += countEndTime.getTime() - countStartTime.getTime();
				totalTime += endTime.getTime() - startTime.getTime();
			}
			else 
			{
				// Create RDD from text file
				val r = spark.textFile(text)
				
				startTime = new java.util.Date()

				countStartTime = new java.util.Date()
				count = r.count()
				/////////////////Add runJob command here
				spark.runJob(r, (iter: Iterator[_]) => {})
				countEndTime = new java.util.Date()

				endTime = new java.util.Date()
				totalCount += count;
				countTime += countEndTime.getTime() - countStartTime.getTime();
				totalTime += endTime.getTime() - startTime.getTime();
			}
		}

		output.write("Word Count is: " + count + "\n");
		if (toEncrypt)
		{
			output.write("Encryption Time is: " + encryptTime + " ms\n")
			output.write("Decryption Time is: " + decryptTime + " ms\n")
		}
		output.write("Count Time is: " + countTime + " ms\n")
		output.write("Total Time is: " + totalTime + " ms\n")
		spark.stop()
		output.write("Test Finish\n")
	}
}
