package com.sfsj.asus.dao;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.DateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import me.itangqi.greendao.Note;
import me.itangqi.greendao.NoteDao;

public class MainActivity extends ListActivity  {

/*
    private Button add;
    private Button search;
    private EditText editText;
    private Cursor cursor;
    public static final String TAG = "DaoExample";
    String textColumn = NoteDao.Properties.Text.columnName;
    String orderBy = textColumn + " COLLATE LOCALIZED ASC";
    private String[] from;
    private int[] to;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
       // setupDatabase();
        setupDatabase();
        // 获取 NoteDao 对象
       // getNoteDao();
        //initData();


    }

    private void initData() {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from,
                to);
        setListAdapter(adapter);

    }

*//*    private NoteDao getNoteDao() {
       return BaseApplication.getD;
    }
    private SQLiteDatabase getDb() {
        // 通过 BaseApplication 类提供的 getDb() 获取具体 db
        return ;
    }*//*

*//*    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }*//*
private void setupDatabase() {
    helper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME, null);
    db = helper.getWritableDatabase();
    // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
    daoMaster = new DaoMaster(db);
    daoSession = daoMaster.newSession();
}

    private void initView() {
        add = (Button) findViewById(R.id.buttonAdd);
        search = (Button) findViewById(R.id.buttonSearch);
        editText = (EditText) findViewById(R.id.editTextNote);
        add.setOnClickListener(this);
       search.setOnClickListener( this);
        cursor = getDb().query(getNoteDao().getTablename(), getNoteDao().getAllColumns(), null, null, null, null, orderBy);
        from = new String[]{textColumn, NoteDao.Properties.Comment.columnName};
        to = new int[]{android.R.id.text1, android.R.id.text2};

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
        if ( noteText.equals("")) {
            ToastUtils.show(getApplicationContext(), "Please enter a note to query");
        } else {
            //查询过程 可以被重复执行的类
            Query query = getNoteDao().queryBuilder()
                    .where(NoteDao.Properties.Text.eq("Test1"))
                    .orderAsc(NoteDao.Properties.Date)
                    .build();
            //查询结果返回集合
            List notes=query.list();
            ToastUtils.show(getApplicationContext(), "There have " + notes.size() + " records");
        }

        //查询结果 方便输出 设置两个标志
        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_VALUES=true;

    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());
        if(noteText==null||noteText.equals("")){
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
//在接口中获取
 *//*   private NoteDao getNoteDao() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
      //  return ((BaseApplication) this.getApplicationContext()).getDaoSession().getNoteDao();
        return getDaoSession().getNoteDao();
    }*//*
*//*    public DaoSession getDaoSession() {
        return daoSession;
    }
    private SQLiteDatabase getDb() {
        // 通过 BaseApplication 类提供的 getDb() 获取具体 db
        return (MainActivity.this).getDb();
    }*//*
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        getNoteDao().deleteByKey(id);
        //或者一次删除所有
        cursor.requery();
    }










    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    public SQLiteDatabase getDb() {
        return db;
    }

    public NoteDao getNoteDao() {
        return daoSession.getNoteDao();
    }


*//*  private void setupDatabase() {
        helper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME, null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }*/
}
