package startfirst.truyen.creepypasta.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


import startfirst.truyen.creepypasta.dto.Truyen;
import startfirst.truyen.creepypasta.utis.ConvertUnsigned;




import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

	 private static String DB_PATH;
	 
	    private static String DB_NAME = "CreepypastaTruyen.sqlite";
	 
	    private SQLiteDatabase myDataBase; 
	 
	    private final Context myContext;
	
	
	 public DataBaseHelper(Context context) {
		 
	    	super(context, DB_NAME, null, 1);
	        this.myContext = context;
	        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
	 }

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		
	}
	
	
	
	public ArrayList<Truyen> GetAllTruyen() {
		ArrayList<Truyen> uti = new ArrayList<Truyen>();
		
		Cursor dataRusult = myDataBase.query("Creepypasta", new String[]{"SoThuTu", "TenTruyen","NoiDungTruyen"}, null, null, null, null, null);
		
		dataRusult.moveToFirst();
		while (!dataRusult.isAfterLast()) {
			Truyen temp = new Truyen();
			temp.set_SoThuTu(dataRusult.getInt(0));
			temp.set_TenTruyen(dataRusult.getString(1));
			temp.set_NoiDung(dataRusult.getString(2));
			if (!dataRusult.getString(1).equals("  ")) {
				uti.add(temp);
			}
			dataRusult.moveToNext();
		}
		dataRusult.close();
		return uti;
	}
	
	
	
	
	public ArrayList<Truyen> GetThoByName(String subTitle) {
		ArrayList<Truyen> uti = new ArrayList<Truyen>();
		
		ArrayList<Truyen> data = GetAllTruyen();
		ConvertUnsigned convert = new ConvertUnsigned();
		
		for (int i = 0; i < data.size(); i++) {
			if (convert.ConvertString(data.get(i).get_TenTruyen()).startsWith(convert.ConvertString(subTitle))) {
				uti.add(data.get(i));
			}
		}
		return uti;
	}
	
	
	
	
	
	
	

	
	
	
	
	
	public void createDataBase() throws IOException{
		 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    		
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
	
	
	
	
	public void openDataBase() throws SQLException{
		 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
 
    }
	
	@Override
	public synchronized void close() {
		if(myDataBase != null)
		    myDataBase.close();
		super.close();
	}
	
	
	
	private void copyDataBase() throws IOException{
		 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	public Boolean isInstall() {
		return checkDataBase();
	}
	
	
	
	private boolean checkDataBase(){
		 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
}
