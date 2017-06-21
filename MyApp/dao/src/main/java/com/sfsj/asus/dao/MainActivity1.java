package com.sfsj.asus.dao;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import me.itangqi.greendao.Note;
import me.itangqi.greendao.NoteDao;

import static android.content.ContentValues.TAG;

/**
 * Created by ${zhangxiaocong} on 2017/6/13.
 */
//代码重构
public class MainActivity1 extends ListActivity implements View.OnClickListener{

    private Button add;
    private Button search;
    private EditText editText;
    private Cursor cursor;
    private String orderBy;
    private String textColumn;
    private SQLiteDatabase db;
    private NoteDao noteDao;
    private String[] from;
    private int[] to;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        add.setOnClickListener(this);
        search.setOnClickListener( this);
    }

    private void initData() {
        cursor();
        from = new String[]{textColumn, NoteDao.Properties.Comment.columnName};
        to = new int[]{android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from,
                to);
        setListAdapter(adapter);
    }

    private void cursor() {
        textColumn = NoteDao.Properties.Text.columnName;
        orderBy = textColumn + " COLLATE LOCALIZED ASC";
        cursor = getDb().query(getNoteDao().getTablename(), getNoteDao().getAllColumns(), null, null, null, null, orderBy);
    }

    private NoteDao getNoteDao() {

        BaseApplication baseApplication ;
        baseApplication= (BaseApplication) this.getApplicationContext();
        noteDao = baseApplication.getDaoSession().getNoteDao();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

        return noteDao;
    }

    private SQLiteDatabase getDb() {
        BaseApplication baseApplication ;
        baseApplication= (BaseApplication) this.getApplicationContext();
        db = baseApplication.getDb();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db;
    }

    private void initView() {
        add = (Button) findViewById(R.id.buttonAdd);
        search = (Button) findViewById(R.id.buttonSearch);
        editText = (EditText) findViewById(R.id.editTextNote);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAdd:
                addNote();
                ToastUtils.show(getApplicationContext(), "添加");
                break;
            case R.id.buttonSearch:
                searchNote();
                break;
            default:
                ToastUtils.show(getApplicationContext(), "What's wrong ?");
                break;
        }
    }

    private void searchNote() {
        String noteText = editText.getText().toString();
        editText.setText("");
        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());
        if(noteText.equals("")){
            ToastUtils.show(getApplicationContext(), "Please enter a note to add");
        }else {
            Query query=getNoteDao().queryBuilder()
                    .where(NoteDao.Properties.Text.eq(noteText))
                    .orderAsc(NoteDao.Properties.Date)
                    .build();
            List notes=query.list();
            ToastUtils.show(getApplicationContext(),notes.size()
                    +"条记录");
            QueryBuilder.LOG_VALUES=true;
            QueryBuilder.LOG_SQL=true;
        }

    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());
        if(noteText.equals("")){
            ToastUtils.show(getApplicationContext(), "Please enter a note to add");
        }else {
            note = new Note(null, noteText, comment, new Date());
            //getNoteDao().insert(note); 添加对象的获取方法
            getNoteDao().insert(note);
        }
        // 插入操作，简单到只要你创建一个 Java 对象
        Log.d(TAG, "Inserted new note, ID: " + note.getId());
        cursor.requery();
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        getNoteDao().deleteByKey(id);
        //或者一次删除所有
        cursor.requery();
    }
}
