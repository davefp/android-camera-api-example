package com.example.cameraexample;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraActivity extends Activity implements OnClickListener, PictureCallback {

	CameraSurfaceView cameraSurfaceView;
	Button shutterButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);

		// set up our preview surface
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		cameraSurfaceView = new CameraSurfaceView(this);
		preview.addView(cameraSurfaceView);

		// grab out shutter button so we can reference it later
		shutterButton = (Button) findViewById(R.id.shutter_button);
		shutterButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_camera, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		takePicture();
	}

	private void takePicture() {
		shutterButton.setEnabled(false);
		cameraSurfaceView.takePicture(this);
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		// TODO something with the image data
		
		// Restart the preview and re-enable the shutter button so that we can take another picture
		camera.startPreview();
		shutterButton.setEnabled(true);
	}
}
