import java.util.*;

public class Main {
    public static void main(String[] args) {

        MyList<Integer> myList = new MyLinkedList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);

        myList.remove(3);
        System.out.println(myList.contains(2));

        myList.set(2, 22);
        for (Integer i : myList) {
            System.out.println(i);
        }


        ListIterator<Integer> listIterator = myList.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.add(11);

        }

        System.out.println(myList);

        while (listIterator.hasPrevious()) {
            listIterator.previous();
            listIterator.remove();
        }

        System.out.println(myList);


    }
}
