package de.grundid.demo1.androidworkshop;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.koushikdutta.ion.Ion;

import java.text.DateFormat;
import java.util.Date;

public class ImageCardViewHolder extends RecyclerView.ViewHolder {

	private final DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	private ImageView imageView;
	private TextView title;
	private TextView description;
	private TextView annotation;

	public ImageCardViewHolder(View itemView) {
		super(itemView);
		imageView = (ImageView)itemView.findViewById(R.id.image);
		title = (TextView)itemView.findViewById(R.id.title);
		description = (TextView)itemView.findViewById(R.id.description);
		annotation = (TextView)itemView.findViewById(R.id.annotation);
	}

	public void updateWithImage(Image image) {
		Ion.with(imageView).load(image.getImage());
		title.setText(image.getTitle());
		description.setText(image.getDescription());
		StringBuilder sb = new StringBuilder();
		sb.append("Erstellt am ").append(df.format(new Date(image.getCreatedDate())));
		sb.append(" von ").append(image.getUserName());
		sb.append(" aus ").append(image.getUserLocation());
		annotation.setText(sb);
	}
}
