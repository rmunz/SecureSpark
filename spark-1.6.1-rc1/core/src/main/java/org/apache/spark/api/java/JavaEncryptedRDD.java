package org.apache.spark.api.java

import org.apache.spark.api.java.javaRDD;

public class JavaEncryptedRDD<T> extend JavaRDD<T>{
  public class JavaEncryptedRDD(RDD<T> rdd, scala.reflect.ClassTag<T> classTag) {
    super(rdd, classTag);
  }

  @Override
  public void saveAsTextFile(path) {
    super.saveAsTextFile("/tmp" + path);
  }
}
