package com.elias.mapreduce.graph;

import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Vector;

public class PageRankJob extends RandomWalk {

  public static void main(String[] args) throws Exception {
    ToolRunner.run(new PageRankJob(), args);
  }

  @Override
  protected Vector createDampingVector(int numVertices, double stayingProbability) {
    return new DenseVector(numVertices).assign((1.0 - stayingProbability) / numVertices);
  }
}
