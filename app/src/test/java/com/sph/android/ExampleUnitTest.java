package com.sph.android;

import com.sph.android.model.db.data.RecordDb;
import com.sph.android.model.rest.Record;
import com.sph.android.ui.MainActivity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void checkGetAdapterRecordsSingleYear() {
        List<Record> input = new ArrayList<>();
        Record rec1 = new Record();
        rec1.setQuarter("2005-Q1");
        rec1.setVolumeOfMobileData("0.00062");

        Record rec2 = new Record();
        rec2.setQuarter("2005-Q2");
        rec2.setVolumeOfMobileData("0.000634");

        Record rec3 = new Record();
        rec3.setQuarter("2005-Q3");
        rec3.setVolumeOfMobileData("0.000718");

        input.add(rec1);
        input.add(rec2);
        input.add(rec3);

        List<RecordDb> output = MainActivity.getAdapterRecords(input);
        assertEquals(output.get(0).getYear(), "2005");
        assertEquals(output.get(0).getVolumeOfMobileData(), "0.001972");
        assertEquals(output.size(), 1);

    }

    @Test
    public void checkGetAdapterRecordsDoubleYear() {
        List<Record> input = new ArrayList<>();
        Record rec1 = new Record();
        rec1.setQuarter("2005-Q1");
        rec1.setVolumeOfMobileData("0.00062");

        Record rec2 = new Record();
        rec2.setQuarter("2005-Q2");
        rec2.setVolumeOfMobileData("0.000634");

        Record rec3 = new Record();
        rec3.setQuarter("2005-Q3");
        rec3.setVolumeOfMobileData("0.000718");


        Record rec4 = new Record();
        rec4.setQuarter("2006-Q3");
        rec4.setVolumeOfMobileData("0.00718");

        input.add(rec1);
        input.add(rec2);
        input.add(rec3);
        input.add(rec4);

        List<RecordDb> output = MainActivity.getAdapterRecords(input);
        assertEquals(output.get(1).getYear(), "2006");
        assertEquals(output.get(1).getVolumeOfMobileData(), "0.00718");
        assertEquals(output.size(), 2);

    }
}