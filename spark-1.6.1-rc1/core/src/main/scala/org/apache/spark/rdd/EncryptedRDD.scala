/*package org.apache.spark.rdd

import scala.reflect.{classTag, ClassTag}

import org.apache.hadoop.io.{NullWritable, Text}
import org.apache.hadoop.mapred.TextOutputFormat

import org.apache.spark._
import org.apache.spark.Partitioner._

class EncryptedRDD[T: ClassTag](prev: RDD[T]) extends RDD[T](prev) {
	override def compute(split: Partition, context: TaskContext): Iterator[T] = {
		firstParent[T].iterator(split, context)
	}
 
	override protected def getPartitions: Array[Partition] = firstParent[T].partitions

	def saveAsCypherFile(path: String): Unit = withScope {
    val nullWritableClassTag = implicitly[ClassTag[NullWritable]]
    val textClassTag = implicitly[ClassTag[Text]]
    val r = this.mapPartitions { iter =>
      val text = new Text()
      iter.map { x =>
        text.set(java.util.Base64.getEncoder().encodeToString(Encryption.XOR(0x8D.toByte, x.toString)))
        (NullWritable.get(), text)
      }
    }
    RDD.rddToPairRDDFunctions(r)(nullWritableClassTag, textClassTag, null)
      .saveAsHadoopFile[TextOutputFormat[NullWritable, Text]](path)
	}
}*/
