package tv.okdev.fileexttest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		checkExternalStorage();
		createFile();
		readRaw();
		readExtFile();
	}

	private void readRaw() {
		tv.append("\nData read from res/raw/textfile.txt:");
		InputStream is = this.getResources().openRawResource(R.raw.textfile);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr, 8192); // 2nd arg is buffer
															// size

		// More efficient (less readable) implementation of above is the
		// composite expression
		/*
		 * BufferedReader br = new BufferedReader(new InputStreamReader(
		 * this.getResources().openRawResource(R.raw.textfile)), 8192);
		 */

		try {
			String test;
			while (true) {
				test = br.readLine();
				// readLine() returns null if no more lines in the file
				if (test == null)
					break;
				tv.append("\n" + "    " + test);
			}
			br.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tv.append("\n\nThat is all");
	}

	private void checkExternalStorage() {
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// Can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// Can only read the media
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			// Can't read or write
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		tv.append("\n\nExternal Media: readable=" + mExternalStorageAvailable
				+ " writable=" + mExternalStorageWriteable);

	}

	private void createFile() {

		// Find the root of the external storage.
		// See
		// http://developer.android.com/guide/topics/data/data-storage.html#filesExternal
		File root = Environment.getExternalStorageDirectory();
		tv.append("\nExternal file system root: " + root);

		// See
		// http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder
		File dir = new File(root.getAbsolutePath() + "/download");
		dir.mkdirs();
		File file = new File(dir, "myData.txt");

		try {
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			pw.println("Hi, How are you");
			pw.println("Hello");
			pw.flush();
			pw.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.i(TAG,
					"******* File not found. Did you"
							+ " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
		} catch (IOException e) {
			e.printStackTrace();
		}
		tv.append("\n\nFile written to " + file);

	}

	private void readExtFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
