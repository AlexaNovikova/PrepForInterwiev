
import java.util.Iterator;
import java.util.ListIterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {

    private T[] list;
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity <= 0  " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Comparable[capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public MyArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        list = (T[]) new Comparable[capacity];
    }

    public void add(T item) {
        // проверка на превышение лоад фактора (0.75)
        checkLoadF();
        list[size] = item;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    public void add(int index, T item) {
        // проверка на превышение лоад фактора (0.75)
        checkLoadF();
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    public T remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        T temp = list[index];
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size--;
        list[size] = null;
        return temp;
    }

    public int indexOf(T obj) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    private class ListIter extends Iter implements ListIterator<T> {

        int index = 0;
        boolean isPrevious;
        boolean isNext;

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            isPrevious = true;
            isNext = false;
            return list[index--];
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
            if (isNext) {
                if (MyArrayList.this.remove(list[index - 1])) {
                    index--;
                }
            }
            if (isPrevious) {
                MyArrayList.this.remove(list[index]);
            }
        }


        /**
         * @param t - новое значение элемента списка
         *          устанавливает значение, указанное параметром, для элемента пройденного
         *          итератором методом Next, prev.
         */

        @Override
        public void set(T t) {
            list[index] = t;
        }

        /**
         * @param t - значение нового элемента списка
         *          создает новый элемент списка и добавляет его между пройденным элементом
         *          методами Next, Prev
         *          и текущим положением итератора
         */

        @Override
        public void add(T t) {
            MyArrayList.this.add(index, t);
            if (isNext) {
                index++;
            }
        }

        /**
         * Метод начинает перебор элементов с начала списка,
         * возвращает True если есть последующий элемент(элемент не равен null),
         * при достижении конца списка
         * указатель устанавливается на последнем элементе и метод возвращает false
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            isPrevious = false;
            isNext = true;
            return list[index++];
        }
    }

    private class Iter implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return list[index++];
        }
    }


    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return list[index];
    }

    @Override
    public boolean contains(T object) {
        return indexOf(object) >= 0;
    }

    @Override
    public void set(int index, T item) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        list[index] = item;
    }

    public int size() {
        return size;
    }


    public boolean remove(T obj) {
        int i = indexOf(obj);
        if (i == -1) {
            return false;
        }
        remove(i);
        return true;
    }

    private void checkLoadF() {
        if (size > capacity * 3 / 4) {
            capacity += capacity;
            T[] listOld = list.clone();
            list = (T[]) new Comparable[capacity];
            for (int i = 0; i < listOld.length; i++) {
                list[i] = listOld[i];
            }
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

}
