package mobile.rentacar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import mobile.rentacar.R.id;
import mobile.rentacar.R.dimen;
import mobile.rentacar.R.color;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class BasicActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Basic Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onBackPressed() {
        Intent l = new Intent(BasicActivity.this, LoginActivity.class);
        startActivity(l);
        super.onBackPressed();  // optional depending on your needs
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent l = new Intent(BasicActivity.this, LoginActivity.class);
                startActivity(l);
            }
        });

        Bundle b = this.getIntent().getExtras();
        ArrayList<String> vehicleIds = b.getStringArrayList("vehiclesById");
        ArrayList<String> vehicleNames = b.getStringArrayList("vehiclesByName");
        ArrayList<String> vehicleImages = b.getStringArrayList("vehiclesByImg");
        ArrayList<String> rentDates = b.getStringArrayList("vehiclesByRentDate");
        ArrayList<String> expireDates = b.getStringArrayList("vehiclesByExpireDate");

        ArrayList<ImageView> imageViews = new ArrayList<>();

        LinearLayout ll0;
        ll0 = (LinearLayout) findViewById(R.id.BasicActivityLinearLayout0);

        for (int i = 0; i < (vehicleImages != null ? vehicleImages.size() : 0); i++) {

            LinearLayout ll = new LinearLayout(this);
            ll.setId(i + 1);
            final float scale = this.getResources().getDisplayMetrics().density;
            int height = (int) (150 * scale + 0.5f);
            LayoutParams lp = new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    height
            );
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll0.addView(ll, lp);

            LinearLayout ll1 = new LinearLayout(this);
            ll1.setId(i + 2);
            int width = (int) (0 * scale + 0.5f);
            ll1.setOrientation(LinearLayout.VERTICAL);
            ll1.setPadding((int) this.getResources().getDimension(dimen.activity_horizontal_margin),
                    (int) this.getResources().getDimension(dimen.activity_vertical_margin),
                    (int) this.getResources().getDimension(dimen.activity_horizontal_margin),
                    (int) this.getResources().getDimension(dimen.activity_vertical_margin));

            LayoutParams lp1 = new LayoutParams(
                    width,
                    LayoutParams.MATCH_PARENT
            );
            lp1.weight = 0.5f;

            LinearLayout ll2 = new LinearLayout(this);
            ll2.setId(i + 3);
            ll2.setOrientation(LinearLayout.VERTICAL);
            ll2.setPadding((int) this.getResources().getDimension(dimen.activity_horizontal_margin),
                    (int) this.getResources().getDimension(dimen.activity_vertical_margin),
                    (int) this.getResources().getDimension(dimen.activity_horizontal_margin),
                    (int) this.getResources().getDimension(dimen.activity_vertical_margin));

            ll.addView(ll1, lp1);
            ll.addView(ll2, lp1);

            TextView vehicleName = new TextView(this);
            LayoutParams tlp = new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
            );
            vehicleName.setTextColor(Color.parseColor("#76ACCC"));
            vehicleName.setText(vehicleNames.get(i));

            ImageView vehicleImage = new ImageView(this);
            new DownloadImageTask(vehicleImage).execute(vehicleImages.get(i));
            imageViews.add(vehicleImage);

            ll1.addView(vehicleName, tlp);
            ll1.addView(imageViews.get(i), tlp);

            TextView rentDateLabel = new TextView(this);
            rentDateLabel.setText(R.string.rent_date_label);
            TextView rentDate = new TextView(this);
            rentDate.setText(rentDates.get(i));
            rentDate.setGravity(Gravity.CENTER);
            rentDate.setTextColor(Color.WHITE);
            rentDate.setBackgroundColor(ContextCompat.getColor(this, R.color.rentDate));
            TextView expireDateLabel = new TextView(this);
            expireDateLabel.setText(R.string.expire_date_label);
            TextView expireDate = new TextView(this);
            expireDate.setText(expireDates.get(i));
            expireDate.setGravity(Gravity.CENTER);
            expireDate.setTextColor(Color.WHITE);
            expireDate.setBackgroundColor(ContextCompat.getColor(this, R.color.expireDate));

            ll2.addView(rentDateLabel, tlp);
            ll2.addView(rentDate, tlp);
            ll2.addView(expireDateLabel, tlp);
            ll2.addView(expireDate, tlp);
        }

        //ATTENTION: This was auto-generated to implement the App Indexing API.
        //See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urlDisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
