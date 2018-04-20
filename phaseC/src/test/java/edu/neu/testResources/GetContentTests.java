package edu.neu.testResources;

import edu.neu.comparison.GetContent;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GetContentTests {
    GetContent gc = new GetContent();
    @Test
    public void test01(){

        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(1);
        a1.add(2);
        gc.setMatchedContentF1(a1);
        assertEquals(a1,gc.getMatchedContentF1());
    }

    @Test
    public void test02(){

        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(3);
        a2.add(4);
        gc.setMatchedContentF2(a2);
        assertEquals(a2,gc.getMatchedContentF2());
    }
}
