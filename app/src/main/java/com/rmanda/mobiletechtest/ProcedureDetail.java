    package com.rmanda.mobiletechtest;

    import android.support.design.widget.CollapsingToolbarLayout;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.view.animation.Animation;
    import android.view.animation.AnimationUtils;
    import android.widget.ImageView;
    import android.widget.TextView;
    import com.bumptech.glide.Glide;
    import com.google.gson.annotations.SerializedName;


    import java.util.HashMap;

    public class ProcedureDetail extends AppCompatActivity {
        public static final String KEY_DETAIL = "key_detail";
        private TextView tvName, tvMore, tvDetail,tvMore2;
        private ImageView ivImage, ivImage2, ivImage3;
        private ImageView ivImageCard;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.procedure_detail);
            tvName = findViewById(R.id.procedure_id);
            tvDetail = findViewById(R.id.procedure_name);
            tvMore = findViewById(R.id.moredetail);
            tvMore2 = findViewById(R.id.moredetail2);
            ivImageCard = findViewById(R.id.header_img);
            ivImage = findViewById(R.id.imageView);
            ivImage2 = findViewById(R.id.imageView2);
            ivImage3 = findViewById(R.id.imageView3);
            CollapsingToolbarLayout collapsingToolbar =
                    findViewById(R.id.collapsing_toolbar);

            HashMap<String, String> dataset = (HashMap<String, String>) getIntent().getSerializableExtra(KEY_DETAIL);
            tvName.setText(dataset.get("id"));
            tvDetail.setText(dataset.get("name"));
            tvMore.setText(dataset.get("name"));
            tvMore2.setText(dataset.get("name"));
            Glide.with(this)
                    .load(dataset.get("icon"))
                    .into(ivImageCard);

            Glide.with(this)
                    .load(dataset.get("icon"))
                    .into(ivImage);


            Glide.with(this)
                    .load(dataset.get("icon"))
                    .into(ivImage2);

            Glide.with(this)
                    .load(dataset.get("card"))
                    .into(ivImage3);


    }

    }