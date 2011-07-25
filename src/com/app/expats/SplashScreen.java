package com.app.expats;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

public class SplashScreen extends Activity {
	private LinearLayout l1;
	private LinearLayout l2;
	private LinearLayout mlinearLayout;
	private Button btn;
	Context c;

	int viewPosition = 0;

	public void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		btn = (Button) findViewById(R.id.Btn_i);
		btn.setBackgroundColor(Color.TRANSPARENT);
		mlinearLayout = (LinearLayout) findViewById(R.id.linear_layout);
		l1 = (LinearLayout) findViewById(R.id.linear_layout1);
		l2 = (LinearLayout) findViewById(R.id.linear_layout2);

		c = this;

		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// Find the center of the container
				float centerX = mlinearLayout.getWidth() / 2.0f;
				float centerY = mlinearLayout.getHeight() / 2.0f;

				// Create a new 3D rotation with the supplied parameter
				// The animation listener is used to trigger the next animation
				final Rotate3dAnimation rotation;
				rotation = new Rotate3dAnimation(0, 90, centerX, centerY, 0,
						false);
				rotation.setDuration(350);
				rotation.setFillAfter(true);
				rotation.setInterpolator(new LinearInterpolator());
				rotation
						.setAnimationListener(new DisplayNextView(viewPosition));

				if (viewPosition == 1) {

					// Find the center of the container
					centerX = l2.getWidth() / 2.0f;
					centerY = l2.getHeight() / 2.0f;

					// Create a new 3D rotation with the supplied parameter
					// The animation listener is used to trigger the next
					// animation
					Rotate3dAnimation rotation1;
					rotation1 = new Rotate3dAnimation(0, 90, centerX, centerY,
							0, false);
					rotation1.setDuration(350);
					rotation1.setFillAfter(true);
					rotation1.setInterpolator(new LinearInterpolator());
					rotation1.setAnimationListener(new DisplayNextView(
							viewPosition));
					l2.startAnimation(rotation1);

				} else if (viewPosition == 0) {
					l1.startAnimation(rotation);
				}

			}

		});

		mlinearLayout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (viewPosition == 1) {

					// Find the center of the container
					final float centerX = l2.getWidth() / 2.0f;
					final float centerY = l2.getHeight() / 2.0f;

					// Create a new 3D rotation with the supplied parameter
					// The animation listener is used to trigger the next
					// animation
					final Rotate3dAnimation rotation;
					rotation = new Rotate3dAnimation(0, 90, centerX, centerY,
							0, false);
					rotation.setDuration(350);
					rotation.setFillAfter(true);
					rotation.setInterpolator(new LinearInterpolator());
					rotation.setAnimationListener(new DisplayNextView(
							viewPosition));
					l2.startAnimation(rotation);

				} else if (viewPosition == 0) {
					// Intent myintent = new Intent(c, MainActivity.class);
					// startActivity(myintent);
					// finish();
				}

			}

		});

	}

	private final class DisplayNextView implements Animation.AnimationListener {
		int mPosition;

		private DisplayNextView(int position) {
			mPosition = position;
		}

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			if (viewPosition == 0) {
				l1.post(new SwapViews(mPosition));
			} else

			{
				l2.post(new SwapViews(mPosition));
			}
			// Toast.makeText(c, "called", Toast.LENGTH_SHORT).show();

		}

		public void onAnimationRepeat(Animation animation) {
		}
	}

	private final class SwapViews implements Runnable {

		int mPosition;

		public SwapViews(int position) {
			mPosition = position;
		}

		public void run() {
			final float centerX = l1.getWidth() / 2.0f;
			final float centerY = l1.getHeight() / 2.0f;
			Rotate3dAnimation rotation;

			if (mPosition == 0) {
				l1.setVisibility(View.GONE);
				l2.setVisibility(View.VISIBLE);
				l2.requestFocus();

				viewPosition = 1;

				// rotation = new Rotate3dAnimation(90, 180, centerX, centerY,
				// 310.0f, false);
			} else {
				l2.setVisibility(View.GONE);
				l1.setVisibility(View.VISIBLE);
				l1.requestFocus();
				viewPosition = 0;

			}

			rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 0, false);
			rotation.setDuration(350);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new LinearInterpolator());
			if (viewPosition == 0) {
				l1.startAnimation(rotation);
			} else

			{
				l2.startAnimation(rotation);
			}

		}
	}
}