package de.grundid.demo1.androidworkshop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ImageCardAdapter extends RecyclerView.Adapter<ImageCardViewHolder> {

	private Image[] images;

	public ImageCardAdapter(Image[] images) {
		this.images = images;
	}

	@Override public ImageCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card, parent, false);
		return new ImageCardViewHolder(v);
	}

	@Override public void onBindViewHolder(ImageCardViewHolder holder, int position) {
		holder.updateWithImage(images[position]);
	}

	@Override public int getItemCount() {
		return images.length;
	}
}
