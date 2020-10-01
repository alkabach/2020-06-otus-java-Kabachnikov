package ru.otus.generics.hw;

import java.util.*;

public class DIYArrayList<E> implements List<E> {
  private E[] elements;
  private int size;
  private int cursor;
  

  public DIYArrayList() {
    elements = (E[]) new Object[0];
  }

  @Override
  public int size() {
    return elements.length;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new DIYArrayList.Itr();
  }
  private class Itr implements Iterator<E> {
    int cursor;
    int lastRet = -1;

    public boolean hasNext() {
      return cursor != size;
    }

    @SuppressWarnings("unchecked")
    public E next() {
         int i = cursor;
      if (i >= size)
        throw new NoSuchElementException();
      Object[] elements = DIYArrayList.this.elements;
      if (i >= elements.length)
        throw new ConcurrentModificationException();
      cursor = i + 1;
      return (E) elements[lastRet = i];
    }

    public void set(E e) {
      if (lastRet < 0)
        throw new IllegalStateException();

      try {
        DIYArrayList.this.set(lastRet, e);
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elements, size);
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

//  @Override
//  public boolean add(E e) {
//    try{
//      E[] tempStore = elements;
//      elements = (E[]) new Object[tempStore.length + 1];
//      System.arraycopy(tempStore,0, elements,0,tempStore.length);
//      elements[tempStore.length] = e;
//      size += 1;
//    } catch(ClassCastException ex){
//      ex.printStackTrace();
//    }
//    return false;
//  }

  @Override
  public boolean add(E e) {
    try{
      E[] tempStore = elements;
      int prefCapacity = tempStore.length >> 1;
//      elements = (E[]) new Object[prefCapacity];
      elements = grow();
      System.arraycopy(tempStore,0, elements,0,tempStore.length);
      elements[tempStore.length] = e;
      size += 1;
    } catch(ClassCastException ex){
      ex.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    try{
      E[] a = (E[]) c.toArray();
      int numNew = c.size();
      if (numNew == 0)
        return false;
      E[] tempStore = elements;
      elements = (E[]) new Object[tempStore.length + numNew];
      System.arraycopy(tempStore,0, elements,0,tempStore.length);
      System.arraycopy(a, 0, elements, tempStore.length,a.length);
      size = numNew + size;
    } catch(ClassCastException ex){
      ex.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
  public void clear() {

  }

  @Override
  public E get(int index) {
    return elements[index];
  }

  @Override
  public E set(int index, E element) {
    if(index < 0 || index == size)
      return null;
    E oldValue = elements[index];
    elements[index] = element;
    return oldValue;
  }

  @Override
  public void add(int index, E element) {
  }

  @Override
  public E remove(int index) {
    return null;
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator<E> listIterator() {
    return new DIYArrayList.ListItr(0);
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return new DIYArrayList.ListItr(index);
  }

  private class ListItr extends Itr implements ListIterator<E> {
    ListItr(int index) {
      super();
      cursor = index;
    }

    public boolean hasPrevious() {
      return cursor != 0;
    }

    public int nextIndex() {
      return cursor;
    }

    @Override
    public E previous() {
      int i = cursor - 1;
      if (i < 0)
        throw new NoSuchElementException();
      Object[] elementData = DIYArrayList.this.elements;
      if (i >= elementData.length)
        throw new ConcurrentModificationException();
      cursor = i;
      return (E) elementData[lastRet = i];
    }

    @Override
    public int previousIndex() {
      return 0;
    }

    @Override
    public void remove() {
    }

    @Override
    public void set(E e) {
      try {
        DIYArrayList.this.set(lastRet, e);
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }

    @Override
    public void add(E e) {
    }
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return null;
  }

  @Override
  public void sort(Comparator<? super E> c) {
    Arrays.sort((E[]) elements, 0, size, c);
  }
}
