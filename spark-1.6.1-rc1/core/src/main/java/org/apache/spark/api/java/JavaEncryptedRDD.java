/* package org.apache.spark.api.java;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;

public class JavaEncryptedRDD<T> extends JavaRDD<T> {
  public JavaEncryptedRDD(RDD<T> rdd, scala.reflect.ClassTag<T> classTag) {
    super(rdd, classTag);
  }

  @Override
  public void saveAsTextFile(String path) {
    /*RDD<T> r = this.mapPartitions(scala.Function1<scala.collection.Iterator<T>, scala.collection.Iterator<U>> f, true, scala.reflect.ClassTag<U> evidence$);

    rddToPairRDDFunctions(RDD<scala.Tuple2<K, V>> rdd, scala.reflec.ClassTag<K> kt, scal.reflect.ClassTag<V> vt, scala.math.Ordering<K> ord)
      .saveAsHadoopFile(path, scala.reflect.ClassTag<F> fm);
  }
}*/
