package org.apache.spark.api.java;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;

public class JavaEncryptedRDD<T> extends JavaRDD<T> {
  public JavaEncryptedRDD(RDD<T> rdd, scala.reflect.ClassTag<T> classTag) {
    super(rdd, classTag);
  }

  @Override
  public void saveAsTextFile(String path) {
    super.saveAsTextFile("/tmp" + path);
  }
}
