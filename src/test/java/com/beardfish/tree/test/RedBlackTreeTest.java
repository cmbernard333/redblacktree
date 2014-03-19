package com.beardfish.tree.test;

import com.beardfish.tree.RedBlackTree;

import org.junit.Test;

/**
 * Created by christian on 3/12/14.
 */
public class RedBlackTreeTest {

  @Test
  public void testRedBlackTreeInsert() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    for (int i = 1; i< 9; i++) {
      rbt.add(i);
    }
    System.out.println("Finished");
  }
  
  /* TODO tree insertion breaks down at a certain number 'n'. Currently 1-4 works */
  @Test
  public void depthFirstSearchTest() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    for(int i = 1; i < 11; i++) {
      rbt.add(i);
    }
    rbt.depthFirstSearch();
  }

}
