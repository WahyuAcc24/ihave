package com.example.sony.tes.Murid;

import org.junit.Test;

/**
 * Created by SONY on 7/9/2018.
 */
public class ListGuruSdActivityTest {

    @Test
    public void tanteNakal() {
        //ini pake split
        String collectTime = "10:00,09:00,12:00";
        System.out.println("jam yang ku pilih:");
        for (String time: collectTime.split(",")) {
            System.out.println(time);
        }
    }

    @Test
    public void tanteNakalNggaPakeSplitJahatIh() {
        String collectTime = "10:00,09:00,12:00";
        System.out.println(collectTime);
    }

}