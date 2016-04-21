package org.apache.spark.examples

import scala.math.random

import org.apache.spark._

import java.io._

object WordCountBenchmark {
	// def singleTest(text: String, output: PrintWriter, toEncrypt: Boolean) 
	// def multipleTest(text: String, output: PrintWriter, toEncrypt: Boolean, numFiles: Int) 
	def main(args: Array[String]) 
	{
		// Need to setup enviroment and then call tests here
		val writer = new PrintWriter(new File("output.txt" ))
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
		writer.write("5 GB File Test\n")
		writer.write("===================================\n")
		singleTest("fiveGB.txt",writer,args(0).toBoolean)

		writer.write("===================================\n")
		writer.write("100, 1 MB File Test\n")
		writer.write("===================================\n")
		multipleTest("mobydick.txt",writer,args(0).toBoolean, 100)

		writer.write("===================================\n")
		writer.write("500, 1 MB File Test\n")
		writer.write("===================================\n")
		multipleTest("mobydick.txt",writer,args(0).toBoolean, 500)

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

		if (toEncrypt) 
		{
			// Get start timestamp
			encryptStartTime = new java.util.Date()
			// Create RDD from text file
			val r = spark.textFile(text)
			// Encrypt RDD
			val encrypted = r.encrypt(key, iv)
			encrypted.first()
			encrypted.persist()
			encryptEndTime = new java.util.Date()
			decryptStartTime = new java.util.Date()
			//Decrypt RDD
			val decrypted = encrypted.decrypt(key, iv)
			// Get element count
			count = decrypted.count()
			// Record time
			decryptEndTime = new java.util.Date()
		}
		else 
		{
			// Get start timestamp
			encryptStartTime = new java.util.Date()
			// Create RDD from text file
			val r = spark.textFile(text)
			r.first()
			r.persist()
			encryptEndTime = new java.util.Date()
			decryptStartTime = new java.util.Date()
			// Get element count
			count = r.count()
			// Record time
			decryptEndTime = new java.util.Date()
		}
		val encryptTime = encryptEndTime.getTime() - encryptStartTime.getTime();
		val decryptTime = decryptEndTime.getTime() - decryptStartTime.getTime();
		val totalTime = encryptTime + decryptTime;

		output.write("Word Count is: " + count + "\n");
		output.write("Encryption Time is: " + encryptTime + " ms\n")
		output.write("Decryption Time is: " + decryptTime + " ms\n")
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

			if (toEncrypt) 
			{
				// Get start timestamp
				encryptStartTime = new java.util.Date()
				// Create RDD from text file
				val r = spark.textFile(text)
				// Encrypt RDD
				val encrypted = r.encrypt(key, iv)
				encrypted.first()
				encrypted.persist()
				encryptEndTime = new java.util.Date()
				decryptStartTime = new java.util.Date()
				//Decrypt RDD
				val decrypted = encrypted.decrypt(key, iv)
				// Get element count
				count = decrypted.count()
				// Record time
				decryptEndTime = new java.util.Date()
			}
			else 
			{
				// Get start timestamp
				encryptStartTime = new java.util.Date()
				// Create RDD from text file
				val r = spark.textFile(text)
				r.first()
				r.persist()
				encryptEndTime = new java.util.Date()
				decryptStartTime = new java.util.Date()
				// Get element count
				count = r.count()
				// Record time
				decryptEndTime = new java.util.Date()
			}
			totalCount += count;
			encryptTime += encryptEndTime.getTime() - encryptStartTime.getTime();
			decryptTime += decryptEndTime.getTime() - decryptStartTime.getTime();
			totalTime += encryptTime + decryptTime;
		}

		output.write("Word Count is: " + totalCount + "\n");
		output.write("Encryption Time is: " + encryptTime + " ms\n")
		output.write("Decryption Time is: " + decryptTime + " ms\n")
		output.write("Total Time is: " + totalTime + " ms\n")
		spark.stop()
		output.write("Test Finish\n")
	}
}
