###############################################################################
This Team (Secure Spark)								                      #
###############################################################################
Ryan Munz (rtm8889@vt.edu)
Steven Stulga (sjstulga@vt.edu)
Mitch Wagner (mitchw@vt.edu)
Hudson Worden (hpworden@vt.edu)

-------------------------------------------------------------------------------
Link to Repository:
https://github.com/rmunz/SecureSpark.git

-------------------------------------------------------------------------------
Description of Repository:

The repository started with the v1.6.1 release of Apache Spark and then the
team added the following files the the following directories:

+ \spark-1.6.1-rc1\examples\src\main\scala\org\apache\spark\examples
	- EncryptedWordCount.scala
	- ModifiedWordCount.scala
	- WordCountBenchmark.scala

+ \spark-1.6.1-rc1\core\src\main\scala\org\apache\spark\rdd
	- EncryptedRDD.scala

The following files were modified:

+ \spark-1.6.1-rc1\core\src\main\scala\org\apache\spark\rdd
	- RDD.scala

+ \spark-1.6.1-rc1\core\src\main\scala\org\apache\spark
	- SparkContext.scala

-------------------------------------------------------------------------------
How to Build:

Once all modified/new files have been added to the correct directories, Spark
needs to be built in order for the changes to take hold. The build command is:

> build/mvn -DskipTests clean package

This is called from within the spark-1.6.1-rc1\ directory.

You will also have to build the test text files for the benchmark if you want 
to run the word count benchmark yourself. to do this you will need to compile
the GenerateFiles.java class. Run the following commands

> java GenerateFiles "fiveHundMB.txt" 0.5
> java GenerateFiles "twoGB.txt" 2
> java GenerateFiles "fiveGB.txt" 5

You need to make sure "mobydick.txt" is in the same directory as GenerateFiles.

-------------------------------------------------------------------------------
How to Run:

Use the command to run our word count benchmark

> ./bin/run-example ModifiedWordCount False True "noEncrypt.txt"

Where the first argument is whether to use encryption or not, second argument
is whether to run the 1 MB to 2 GB files or the 5 GB file. The last argument is
the output text file name.