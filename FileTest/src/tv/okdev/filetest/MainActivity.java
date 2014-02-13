package tv.okdev.filetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createFile();
		String text = readFile();
		TextView tv = (TextView)findViewById(R.id.text);
		tv.setText(text);
	}

	private String readFile() {
		FileInputStream inputStream;
		String text = "";
		try {
			inputStream = openFileInput(filename);
			byte[] reader = new byte[inputStream.available()];
			while (inputStream.read(reader) != -1) {
			}
			text = new String(reader);

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	String filename = "myfile";

	private void createFile() {
		String string = "Hello world. Hello world. Hello world.";
		FileOutputStream outputStream;

		try {
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
			outputStream.write(string.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
