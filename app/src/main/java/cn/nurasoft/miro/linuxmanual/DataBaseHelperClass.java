package cn.nurasoft.miro.linuxmanual;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * Created by miro on 13/02/17~.~
 **/

public class DataBaseHelperClass extends SQLiteOpenHelper {
    private Context context;
    private static String DB_PATH="";
    private static final String DATABASE_NAME="Test.db";
    private static final int DATABASEVERSION=1;
    private static SQLiteDatabase sqLiteDatabase;

   public DataBaseHelperClass(Context context) {
        super(context,DATABASE_NAME,null,DATABASEVERSION);
        DB_PATH="/data/data/"+context.getPackageName()+"/databases";
        this.context=context;
    }

    void createDataBase() throws IOException{
        boolean databasesExist = checkDataBase();
        if (!databasesExist){
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
                String TAG = "Linux manul";
                Log.e(TAG,"Created successfully!");
            }catch (IOException me){
                throw new Error("Has some errors while copying databases!");
            }
        }
    }
    boolean checkDataBase(){
        File databaseFile = new File(DB_PATH+DATABASE_NAME);
        Log.v("database exists!",databaseFile+"   "+databaseFile.exists());
        return databaseFile.exists();
    }

    void copyDataBase() throws IOException{
        InputStream myInput =context.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH+DATABASE_NAME;
        OutputStream myOutput =new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer,0,length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException{
        String myPath = DB_PATH+DATABASE_NAME;
        sqLiteDatabase =sqLiteDatabase.openDatabase(myPath,null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized  void close(){
        if (sqLiteDatabase!=null)
            sqLiteDatabase.close();
        super.close();
    }

    public String arg[] = new String[4];
    public String[] getinfo(String CName){
        arg[0]=null;
        arg[1]=null;
        arg[2]=null;
        arg[3]=null;
        String query="select * from Users where ComName='"+CName+"'";
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery(query,null);
        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do {
                    arg[0]=cursor.getString(0);
                    arg[1]=cursor.getString(1);
                    arg[2]=cursor.getString(2);
                    arg[3]=cursor.getString(3);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        return arg;
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int i,int ii){
        if(ii>i)
            try{
                copyDataBase();
            }catch (IOException e){
                e.printStackTrace();
            }
    }
}
