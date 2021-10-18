import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {

    private Node head;
    private Node tail;
    private int size;


    @Override
    public void add(T object) {
        if (size == 0) {
            insertFirst(object);
        } else {
            insertLast(object);
        }
    }

    private void insertLast(T object) {
        Node node = new Node(object);
        if (isEmpty()) {
            head = node;
        } else {
            node.setPrevious(tail);
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    private void insertFirst(T object) {
        Node node = new Node(object, head);
        if (isEmpty()) {
            tail = node;
        } else {
            head.setPrevious(node);
        }
        head = node;
        size++;
    }

    private boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void add(int index, T object) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is wrong");
        }
        if (index == size) {
            insertLast(object);
        } else if (index == 0) {
            insertFirst(object);
        } else {
            Node current;
            int i;
            if (index <= size / 2) {
                current = head;
                i = 0;
                while (i < index) {
                    current = current.getNext();
                    i++;
                }
            } else {
                current = tail;
                i = size;
                while (i > index + 1) {
                    current = current.getPrevious();
                    i--;
                }
            }
            Node newNode = new Node(object, current, current.getPrevious());
            current.getPrevious().setNext(newNode);
            current.setPrevious(newNode);
            size++;

        }
    }

    @Override
    public boolean remove(T object) {
        if (head.getValue().equals(object)) {
            removeFirst();
            return true;
        } else if (tail.getValue().equals(object)) {
            removeLast();
            return true;
        } else {
            Node current = head;
            int index = 0;
            while (index < size && !current.getValue().equals(object)) {
                current = current.getNext();
                index++;
            }
            if (index != size) {
                T item = current.getValue();
                Node prev = current.getPrevious();
                prev.setNext(current.getNext());
                current.getNext().setPrevious(current);
                size--;
                return true;
            }
        }
        return false;
    }

    private T removeLast() {
        T object = getTail();
        tail = tail.getPrevious();

        if (isEmpty()) {
            head = null;
        } else {
            tail.setNext(null);
        }
        size--;
        return object;
    }

    private T removeFirst() {
        T object = getHead();
        head = head.getNext();
        if (isEmpty() || size == 1) {
            tail = null;
        } else {
            head.setPrevious(null);
        }
        size--;
        return object;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index is wrong");
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node current;
            int i;
            if (index <= size / 2) {
                current = head;
                i = 0;
                while (i < index) {
                    current = current.getNext();
                    i++;
                }
            } else {
                current = tail;
                i = size;
                while (i > index + 1) {
                    current = current.getPrevious();
                    i--;
                }
            }
            T item = current.getValue();
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            return item;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public boolean contains(T object) {
        return indexOf(object) > -1;
    }

    @Override
    public int indexOf(T object) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.value.equals(object)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public void set(int index, T object) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is wrong");
        }
        Node current;
        int i;
        if (index <= size / 2) {
            current = head;
            i = 0;
            while (i < index) {
                current = current.getNext();
                i++;
            }
        } else {
            current = tail;
            i = size;
            while (i > index + 1) {
                current = current.getPrevious();
                i--;
            }
        }
        current.setValue(object);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    private class ListIter extends Iter implements ListIterator<T> {
        int index = 0;
        Node current = new Node(null, head);
        boolean isNext;
        boolean isPrev;


        /**
         * Метод начинает перебор элементов с конца,
         * возвращает True если есть предшествующий элемент,
         * при достижении начала списка происходит установка итератора на first элементе,
         * метод возвращает false
         */
        @Override
        public boolean hasPrevious() {
            if (current.getPrevious() != null) {
                return true;
            } else {
                current = new Node(null, head);
                return false;
            }
        }

        @Override
        public T previous() {
            isPrev = true;
            isNext = false;
            current = current.getPrevious();
            return current.getValue();
        }

        @Override
        public int nextIndex() {
            index++;
            return index;
        }

        @Override
        public int previousIndex() {
            index--;
            return index;
        }


        @Override
        public void remove() {
            if (current.getValue() != null) {
                MyLinkedList.this.remove(current.value);
            }
        }


        /**
         * @param t - новое значение элемента списка
         *          устанавливает значение, указанное параметром, для элемента пройденного
         *          итератором методом Next, prev.
         */

        @Override
        public void set(T t) {
            if (isPrev && (current.getPrevious() != null || current.getNext() != null)) {
                current.setValue(t);
            }

            if (isNext && ((current.getNext() != null || current.getPrevious() != null && current.getNext() == null))) {
                current.setValue(t);
            }
        }

        /**
         * @param t - значение нового элемента списка
         *          создает новый элемент списка и добавляет его между пройденным элементом
         *          методами Next, Prev
         *          и текущим положением итератора
         */

        @Override
        public void add(T t) {
            if (isPrev) {
                if (current.getPrevious() != null) {
                    Node node = new Node(t, current, current.getPrevious());
                    current.getPrevious().setNext(node);
                    current.setPrevious(node);
                    previous();
                } else if (current.getNext() != null) {
                    Node node = new Node(t, current, null);
                    current.setPrevious(node);
                    head = node;
                    previous();
                }
            }
            if (isNext) {
                if (current.getNext() != null) {
                    Node node = new Node(t, current.getNext(), current);
                    current.getNext().setPrevious(node);
                    current.setNext(node);
                    next();
                } else if (current.getNext() == null && current.getPrevious() != null) {
                    Node node = new Node(t, null, current);
                    current.setNext(node);
                    tail = node;
                    next();
                }
            }
            size++;
        }

        /**
         * Метод начинает перебор элементов с начала списка,
         * возвращает True если есть последующий элемент(элемент не равен null),
         * при достижении конца списка
         * указатель устанавливается на последнем элементе и метод возвращает false
         */
        @Override
        public boolean hasNext() {
            if (current.getNext() != null) {
                return true;
            } else {
                current = new Node(null, null, tail);
                return false;
            }

        }

        @Override
        public T next() {
            isPrev = false;
            isNext = true;
            current = current.getNext();
            return current.getValue();
        }
    }

    private class Iter implements Iterator<T> {
        Node current = new Node(null, head);

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            current = current.getNext();
            return current.getValue();
        }
    }


    private class Node {

        private T value;
        private Node next;
        private Node previous;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value, Node next, Node previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public T getValue() {
            return value;
        }

        public Node getPrevious() {
            return previous;
        }

        public Node getNext() {
            return next;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

    }

    public T getHead() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Элемент не найден");
        }
        return head.value;
    }

    public T getTail() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Элемент не найден");
        }
        return tail.value;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.value).append(", ");
            current = current.getNext();
        }
        if (!isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
