package kr.hs.emirim.ko502804.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ResultActivity2 extends AppCompatActivity {
    int[] imgVIds = {R.id.imgv_01, R.id.imgv_02, R.id.imgv_03,R.id.imgv_04, R.id.imgv_05, R.id.imgv_06,R.id.imgv_07, R.id.imgv_08, R.id.imgv_09};
    ImageView[] imgv = new ImageView[imgVIds.length];
    ViewFlipper viewFlip;
    int[] imgScrIds = {R.drawable.s1, R.drawable.s2, R.drawable.s3,R.drawable.s4, R.drawable.s5, R.drawable.s6,R.drawable.s7, R.drawable.s8, R.drawable.s9};
    int[] voteCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        Intent intent = getIntent();
        voteCount = intent.getIntArrayExtra("voteCount");

        viewFlip = findViewById(R.id.view_flip);
        viewFlip.setFlipInterval(1000);
        sortDescImgSrc();
        for(int i=0; i<imgv.length; i++){
            imgv[i] = findViewById(imgVIds[i]);
        }
        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(btnListener);
        btnStop.setOnClickListener(btnListener);

    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:
                    viewFlip.startFlipping();
                    break;
                case R.id.btn_stop:
                    viewFlip.stopFlipping();
                    break;
            }
        }
    };

    protected void sortDescImgSrc() {
        int tempVote = 0;
        int tempSrc = 0;
        for(int i=0; i<voteCount.length-1; i++){
            for(int j=0; j<voteCount.length; j++){
                if(voteCount[i] < voteCount[j]){
                    tempVote = voteCount[i];
                    tempSrc = imgScrIds[i];
                    voteCount[i] = voteCount[j];
                    imgScrIds[i] = imgScrIds[j];
                    voteCount[j] = tempVote;
                    imgScrIds[j] = tempSrc;
                }
            }
        }
        for(int i=0; i<voteCount.length; i++){
            Log.i("Sorting 결과: ", voteCount[i]+"");
        }
    }
}