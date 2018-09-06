package com.demo.commonlist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.commonlist.R;
import com.demo.commonlist.adapter.CommonAdapter;
import com.demo.commonlist.adapter.CommonAdapter2;
import com.demo.commonlist.data.BookData;
import com.demo.commonlist.data.CommonData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCommon();
        initCommon2();
    }

    private void initCommon() {
        CommonAdapter mAdapter = new CommonAdapter(this, getCommonDatas());

        //CommonAdapter mAdapter = new CommonAdapter(this, getBookDatas()); //因为类型不一样不能通用

        CommonAdapter mAdapter2 = new CommonAdapter(this, getCommonDatasByBook()); //可以转换

        //listview.setAdapter(mAdapter); //设置adapter
    }

    private void initCommon2() {
        CommonAdapter2 mAdapter = new CommonAdapter2<CommonData>(this, getCommonDatas()) {
            @Override
            public String setDesc(CommonData data) {
                return data.desc;
            }
        };

        CommonAdapter2 mAdapter2 = new CommonAdapter2<BookData>(this, getBookDatas()) {
            @Override
            public String setDesc(BookData data) {
                return data.bookName;
            }
        };

        //CommonAdapter2中放置的是一个泛型，在new的时候设置了他的类，所以这个适用更为广泛。


        //listview.setAdapter(mAdapter); //设置adapter
    }

    private List<CommonData> getCommonDatas() {
        List<CommonData> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(new CommonData(i, "desc" + i));
        }
        return result;
    }

    private List<BookData> getBookDatas() {
        List<BookData> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(new BookData(i, "BookName" + i));
        }
        return result;
    }

    private List<CommonData> getCommonDatasByBook() {
        List<CommonData> result = new ArrayList<>();
        for (BookData book : getBookDatas()) {
            result.add(new CommonData(book.bookId, book.bookName));
        }
        return result;
    }

}
