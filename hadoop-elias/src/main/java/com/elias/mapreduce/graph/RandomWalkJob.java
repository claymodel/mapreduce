package com.elias.mapreduce.graph;

import java.util.Map;

import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;

public class RandomWalkJob extends RandomWalk {

  private int sourceVertexIndex;

  public static void main(String[] args) throws Exception {
    ToolRunner.run(new RandomWalkJob(), args);
  }

  @Override
  protected Vector createDampingVector(int numVertices, double stayingProbability) {
    Vector dampingVector = new RandomAccessSparseVector(numVertices, 1);
    dampingVector.set(sourceVertexIndex, 1.0 - stayingProbability);
    return dampingVector;
  }

  @Override
  protected void addSpecificOptions() {
    addOption("sourceVertexIndex", null, "index of source vertex", true);
  }

  @Override
  protected void evaluateSpecificOptions(Map<String, String> parsedArgs) {
    sourceVertexIndex = Integer.parseInt(parsedArgs.get("--sourceVertexIndex"));
  }

}