import java.util.ListIterator;

public interface MyList<T> extends Iterable<T> {

    public void add(T object);

    public void add(int index, T object);

    public boolean remove(T object);

    public T remove(int index);

    public int size();

    public T get(int index);

    public boolean contains(T object);

    public int indexOf(T object);

    public void set(int index, T item);

    public ListIterator<T> listIterator();
}
