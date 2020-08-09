package ru.otus.generics.hw;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DIYArrayListTest {

    private Integer[] testElem = {24,22,28,21};

    private void generate(int maxElem, List<? super Integer> l){
        for(int i=0; i<maxElem;i++){
            l.add(i);
        }
    }

    private void generate(int minElem, int maxElem, List<? super Integer> l){
        for(int i=minElem; i<maxElem;i++){
            l.add(i);
        }
    }

    @DisplayName("Тестирование работы метода коллекции AddAll")
    @Test
    void testCollectionsAddAll() {
        ArrayList<Integer> arrList = new ArrayList<>();
        DIYArrayList<Integer> diyArr = new DIYArrayList<>();

        generate(10, arrList);
        generate(10, diyArr);
        Collections.addAll(diyArr, testElem);
        Collections.addAll(arrList, testElem);

        assertEquals(arrList,diyArr);
    }

    @DisplayName("Тестирование работы метода коллекции copy")
    @Test
    void testCollectionsCopy() {
        ArrayList<Integer> arrList = new ArrayList<>();
        ArrayList<Integer> arrList2 = new ArrayList<>();

        DIYArrayList<Integer> diy = new DIYArrayList<>();
        DIYArrayList<Integer> diy2 = new DIYArrayList<>();

        generate(10,20, arrList);
        generate(20,30, arrList2);

        generate(10,20, diy);
        generate(20,30, diy2);

        Collections.copy(arrList, arrList2);
        Collections.copy(diy, diy2);

        System.out.print("ArrList: ");
        arrList.forEach(Integer -> System.out.print(Integer + ";"));
        System.out.println("");
        System.out.print("DIYArrList: ");
        diy.forEach(Integer -> System.out.print(Integer + ";"));

        assertEquals(arrList,diy);
    }

    @DisplayName("Тестирование работы метода коллекции sort")
    @Test
    void testCollectionsSort() {
        ArrayList<Integer> arrList = new ArrayList<>();

        DIYArrayList<Integer> diyArr = new DIYArrayList<>();

        generate(0,10, diyArr);
        generate(0,10, arrList);

        Collections.addAll(diyArr, testElem);
        Collections.addAll(arrList, testElem);

        Collections.sort(diyArr);
        Collections.sort(arrList);

        System.out.print("ArrList: ");
        arrList.forEach(Integer -> System.out.print(Integer + ";"));
        System.out.println("");
        System.out.print("DIYArrList: ");
        diyArr.forEach(Integer -> System.out.print(Integer + ";"));

        assertEquals(arrList,diyArr);
    }
}
