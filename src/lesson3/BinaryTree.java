package lesson3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;
    Node<T> parent = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size ++;

        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove( Object o) {
        if(contains(o)){
            Node<T> node = new Node<T>((T) o);
            if(root.value == node.value && root.right == null && root.left == null) root = null;
            else if(root.value == node.value && root.left != null && root.right == null) root = root.left;
            else if(root.value == node.value  && root.left == null && root.right != null) root = root.right;
            else remove(root, node);
            size --;
        }
        return false;
    }

    public void remove(Node elements, Node node){
        int comparison = ((T) elements.value).compareTo((T)node.value);
        if(comparison != 0) {
            parent = elements;
            if(comparison > 0) remove(elements.left, node);
            else remove(elements.right, node);
        }
        else{
            if(elements.left == null && elements.right == null){
                if(elements == parent.right) parent.right = null;
                else parent.left = null;
            }
            else if(elements.left == null && elements.right != null){
                if(elements == parent.right) parent.right = elements.right;
                else parent.left = elements.right;
            }
            else if(elements.left != null && elements.right == null){
                if(elements == parent.right) parent.right = elements.left;
                else parent.left = elements.left;
            }
            else if(elements.left != null && elements.right != null){
                if(elements.right.left == null){
                    if(elements == root) root = elements.right;
                    else if(elements == parent.right) parent.right = elements.right;
                    else parent.left = elements.right;
                    elements.right.left = elements.left;
                    elements.right = elements.right.right;
                }else{
                    Node m = searchRemove(elements.right);
                    if(elements == root) root = m.left;
                    else if(elements == parent.right) parent.right = m.left;
                    else parent.left = m.left;
                    m.left.left = elements.left;
                    m.left.right = elements.right;
                    m.left = null;
                }
            }
        }
    }

    private Node searchRemove(Node elements){
        if(elements.left.left == null) return elements;
        else return searchRemove(elements.left);
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {}

        private Node<T> findNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        if(toElement == null) throw new IllegalArgumentException();
        SortedSet<T> set = new TreeSet<>();
        searchHead(root, toElement, set);
        return set;
    }

    public void searchHead(Node node, T toElement, SortedSet<T> set){
        if(node.left != null) searchHead(node.left, toElement, set);
        if(((T)node.value).compareTo(toElement) < 0){
            if(node.right != null) searchHead(node.right, toElement, set);
            set.add((T) node.value);
        }
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        if(fromElement == null) throw new IllegalArgumentException();
        SortedSet<T> set = new TreeSet<>();
        searchTail(root, fromElement, set);
        return set;
    }

    public void searchTail(Node node, T fromElement, SortedSet<T> set){
        if(node.right != null) searchTail(node.right, fromElement, set);
        int comparison = ((T)node.value).compareTo(fromElement);
        if(comparison > 0 || comparison == 0){
            if(node.left != null) searchTail(node.left, fromElement, set);
            set.add((T) node.value);
        }
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
