import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Element<Item> first = null;
    private Element<Item> last = null;
    private int size = 0;

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can't be null");
        }
        Element<Item> newElement = new Element<>(item);
        if (size == 0) {
            first = newElement;
            last = newElement;
        }
        else {
            first.prev = newElement;
            newElement.next = first;
            first = newElement;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can't be null");
        }
        Element<Item> newElement = new Element<>(item);
        if (size == 0) {
            first = newElement;
            last = newElement;
        }
        else {
            last.next = newElement;
            newElement.prev = last;
            last = newElement;
        }
        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("que is empty");
        }
        size--;
        Item result = first.element;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        else {
            last = null;
        }
        return result;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("que is empty");
        }
        size--;
        Item result = last.element;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        else {
            first = null;
        }
        return result;
    }

    private void print() {
        System.out.print("[");
        for (Item i : this) {
            System.out.print(i + ", ");
        }
        System.out.println("]");
        System.out.println("size: " + size());
        System.out.println(isEmpty());
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Element<Item> currentItem = first;

            @Override
            public boolean hasNext() {
                return currentItem != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("no more elements");
                }
                Item i = currentItem.element;
                currentItem = currentItem.next;
                return i;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static class Element<T> {
        private final T element;
        private Element<T> next;
        private Element<T> prev;

        public Element(T element) {
            this.element = element;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> que = new Deque<>();
        que.addFirst(1);
        que.print();
        que.removeFirst();
        que.print();

        que.addLast(2);
        que.print();
        que.removeLast();
        que.print();

        que.addFirst(3);
        que.print();
        que.removeLast();
        que.print();

        que.addLast(4);
        que.print();
        que.removeFirst();
        que.print();


        System.out.println("size: " + que.size());
        System.out.println(que.isEmpty());
        que.addFirst(2);
        que.addFirst(3);
        que.addFirst(4);
        que.addFirst(5);
        que.addLast(6);
        que.addLast(7);
        que.addLast(8);
        que.addLast(9);
        que.addLast(10);
        que.addFirst(0);
        que.print();
        System.out.println("size: " + que.size());

        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());
        que.print();
        System.out.println("size: " + que.size());

        System.out.println(que.removeLast());
        System.out.println(que.removeLast());
        System.out.println(que.removeLast());
        que.print();
        System.out.println("size: " + que.size());
    }
}
