package com.beardfish.tree.test;

import java.util.Iterator;

import com.beardfish.tree.RedBlackTree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by christian on 3/12/14.
 */
public class RedBlackTreeTest {

    @Test
    public void testRedBlackTreeInsert() {
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        for (int i = 1; i < 9; i++) {
            rbt.add(i);
        }
        Iterator<Integer> it = rbt.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

    @Test
    public void randomInsertTest() {
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        int [] numbers = {13,8,17,1,11,15,25,6,22,27};
        for(int i = 0; i <numbers.length;i++) {
            rbt.add(numbers[i]);
        }
        /* print in order */
        rbt.depthFirstSearch();
    }


    @Test
    public void depthFirstSearchTest() {
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        System.out.println("Depth First Search:");
        for (int i = 1; i < 9; i++) {
            rbt.add(i);
        }
        rbt.depthFirstSearch();
    }

    @Test
    public void breadthFirstSearchTest() {
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        System.out.println("Breadth First Search: ");
        for (int i =1; i <11 ;i++) {
            rbt.add(i);
        }
        rbt.breadthFirstSearch();
    }


    @Test
    public void characterContainsTest() {
        RedBlackTree<Character> rbt = new RedBlackTree<Character>();
        char arr[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < arr.length; i++) {
            rbt.add(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            Assert.assertTrue(rbt.contains(new Character(arr[i])));
        }

    }

    @Test
    public void integerContainsTestFalse() {
        RedBlackTree<String> rbt = new RedBlackTree<String>();
        Assert.assertFalse(rbt.contains("wombat"));
    }

}
