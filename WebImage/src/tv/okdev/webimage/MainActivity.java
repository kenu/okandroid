package tv.okdev.webimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Loader image - will be shown before loading image
		int loader = R.drawable.loader;

		// Imageview to show
		ImageView image = (ImageView) findViewById(R.id.image);

		// Image url
		String image_url = "http://fc02.deviantart.net/fs71/f/2012/362/4/d/aoa___get_out_alternative_cover_by_lordkpopromir-d5ph4le.jpg";

		// ImageLoader class instance
		ImageLoader imgLoader = new ImageLoader(getApplicationContext());

		// whenever you want to load an image from url
		// call DisplayImage function
		// url - image url to load
		// loader - loader image, will be displayed before getting image
		// image - ImageView
		imgLoader.DisplayImage(image_url, loader, image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
