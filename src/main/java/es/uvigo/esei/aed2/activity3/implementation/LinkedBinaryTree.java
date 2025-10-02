package es.uvigo.esei.aed2.activity3.implementation;

import es.uvigo.esei.aed2.tree.binary.BinaryTree;

/*-
 * #%L
 * AEDII - Activities
 * %%
 * Copyright (C) 2025 Rosalía Laza Fidalgo, María Reyes Pavón Rial,
 * Florentino Fernández Riverola, María Novo Lourés, and Miguel Reboiro Jato
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import es.uvigo.esei.aed2.tree.exceptions.EmptyTreeException;

public class LinkedBinaryTree<T> implements BinaryTree<T> {

  private LinkedBinaryTreeNode<T> rootNode;

  public LinkedBinaryTree() {
    this.rootNode = null;
  }

  public LinkedBinaryTree(T value) throws NullPointerException {
    if (value == null) {throw new NullPointerException("NULL");}
    this.setRootValue(value);
  }

  public LinkedBinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild)
  throws NullPointerException {
    if (value == null || leftChild == null || rightChild == null){throw new NullPointerException("NULL NULL NULL");}
    this.setRightChild(rightChild);
    this.setLeftChild(leftChild);
    this.setRootValue(value);
  }

  private LinkedBinaryTree(LinkedBinaryTreeNode<T> node){
    this.rootNode = node;
  }

  private LinkedBinaryTreeNode<T> buildBinaryTree(BinaryTree<T> tree){
    if (tree == null){
      return null;
    }
    return new LinkedBinaryTreeNode<>(tree.getRootValue(), buildBinaryTree(tree.getLeftChild()), buildBinaryTree(tree.getRightChild()));
  }

  // Métodos lanzan excepción
  @Override
  public T getRootValue() throws EmptyTreeException {
    if (this.rootNode == null) {
      
    }
    return this.rootNode.getValue();
  }

  @Override
  public void setRootValue(T value) throws EmptyTreeException, NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private <T> boolean contains(T value,LinkedBinaryTreeNode<T> node ){
    if (node == null) {
      return false;
    }
    if (node.getValue() == value) {
      return true;
    }
    return (contains(value,node.getLeftChild()) ||contains(value,node.getRightChild()));
  }

  @Override
  public boolean contains(T value) {
    return contains(value,this.rootNode);
  }

  @Override
  public boolean hasLeftChild() {
    if (this.getLeftChild() == null ) {
      return false;
    }
    return true;
  }

  @Override
  public BinaryTree<T> getLeftChild() throws EmptyTreeException {
    if (this.isEmpty()) {
      throw new EmptyTreeException("empty tree");
    }
    if (this.rootNode.hasLeftChild()) {
      return new LinkedBinaryTree<>(this.rootNode.getLeftChild());
    }else{
      return null;
    }
  }

  @Override
  public void setLeftChild(BinaryTree<T> leftChild) throws EmptyTreeException, NullPointerException {
    if (leftChild == null) {
      throw new NullPointerException("null child");
    }
    if (this.isEmpty()) {
      throw new EmptyTreeException("EmptyTree child");
    }
    this.rootNode.setLeftChild(buildBinaryTree(leftChild));//requires a LinkedBinaryTreeNode
  }

  @Override
  public void removeLeftChild() throws EmptyTreeException {
    if (this.isEmpty()) {
      throw new EmptyTreeException("Empty tree");
    }
    if (this.hasLeftChild()){
      this.setLeftChild(null);
    }
  }

  @Override
  public boolean hasRightChild() {
    if (this.getRightChild() == null) {
      return false;
    }
    return true;
  }

  @Override
  public BinaryTree<T> getRightChild() throws EmptyTreeException {
        if (this.isEmpty()) {
      throw new EmptyTreeException("empty tree");
    }
    if (this.rootNode.hasRightChild()) {
      return new LinkedBinaryTree<>(this.rootNode.getRightChild());
    }else{
      return null;
    }
  }

  @Override
  public void setRightChild(BinaryTree<T> rightChild) throws EmptyTreeException, NullPointerException {
    if (rightChild == null) {
      throw new NullPointerException("null child");
    }
    if (this.isEmpty()) {
      throw new EmptyTreeException("EmptyTree child");
    }
    this.rootNode.setRightChild(buildBinaryTree(rightChild));//requires a LinkedBinaryTreeNode
  }

  @Override
  public void removeRightChild() throws EmptyTreeException {
     if (this.isEmpty()) {
      throw new EmptyTreeException("Empty tree");
    }
    if (this.hasRightChild()){
      this.setLeftChild(null);
    }
  }
  
  @Override
  public void clear() {
    this.setLeftChild(null);
    this.setRightChild(null);
    this.rootNode = null;
  }

  @Override
  public boolean isEmpty() {
    if (hasLeftChild() || this.hasRightChild() || (this.getRightChild().getRootValue() != null) && (this.getLeftChild().getRootValue() != null)) {
      return true;
    }
    return false;
  }
}
