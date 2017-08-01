package own.com.litepaltestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button creatDatabase, addData, upData, deleteData, queryData;
    private static final String TAG = "MainActivity.this";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creatDatabase = (Button) findViewById(R.id.creat_db);
        addData = (Button) findViewById(R.id.add_data);
        upData = (Button) findViewById(R.id.update_data);
        deleteData = (Button) findViewById(R.id.delete_data);
        queryData = (Button) findViewById(R.id.query_data);
        creatDatabase.setOnClickListener(this);
        addData.setOnClickListener(this);
        upData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
queryData.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.creat_db:
                LitePal.getDatabase();
                break;
            case R.id.add_data:
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(15.48);
                book.setPress("Unknow");
                book.save();
                break;
            case R.id.update_data:
                 book=new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(513);
                book.setPrice(10.95);
                book.setPress("Unknow");
                book.save();
                book.setPrice(15.99);
                book.save();
                book.setPrice(10);
                book.setToDefault("pages");
                book.updateAll();
//                book.setPress("Anchor");
//                book.updateAll("name=? and author=?","The Lost Symbol","Dan Brown");
                break;
            case R.id.delete_data:
                DataSupport.deleteAll(Book.class,"price < ?","15");
                break;
            case R.id.query_data:
//                List<Book> books=DataSupport.findAll(Book.class);
//                for(Book book1:books){
//                    Log.d(TAG, "Book name is: "+book1.getName());
//                    Log.d(TAG, "Book author is: "+book1.getAuthor());
//                    Log.d(TAG, "Book page is: "+book1.getPages());
//                    Log.d(TAG, "Book price is: "+book1.getPrice());
//                    Log.d(TAG, "Book press is: "+book1.getPress());
//                }
                Book books=DataSupport.findFirst(Book.class);
                books.setName("This is First Book");
                books.setAuthor("Somebody");
                books.setPrice(1000);
                books.setPress("another");
                books.setPages(250);
                books.save();
                Log.d(TAG, "Book name is: "+books.getName());
                    Log.d(TAG, "Book author is: "+books.getAuthor());
                    Log.d(TAG, "Book page is: "+books.getPages());
                    Log.d(TAG, "Book price is: "+books.getPrice());
                    Log.d(TAG, "Book press is: "+books.getPress());
                break;
            default:
                break;
        }

    }
}
