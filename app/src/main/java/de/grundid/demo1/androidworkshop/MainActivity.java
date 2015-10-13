package de.grundid.demo1.androidworkshop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
	}

	@Override protected void onStart() {
		super.onStart();
		Ion.with(this)
				.load("http://api.grundid.de/tree/imageStream")
				.asString()
				.setCallback(
						new FutureCallback<String>() {

							@Override public void onCompleted(Exception e, String result) {
								if (e == null) {
									try {
										ObjectMapper objectMapper = new ObjectMapper();
										Image[] images = objectMapper
												.readValue(result, Image[].class);
										recyclerView.setAdapter(new ImageCardAdapter(images));
									}
									catch (IOException e1) {
										e1.printStackTrace();
									}
								}
								else
									e.printStackTrace();
							}
						});


	}
}
