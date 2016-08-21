package test.com.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private final String TAG = "CheatActivity";

    private static final String EXTRA_ANSWER_IS_TRUE = "test.com.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_IS_SHOWN = "test.com.geoquiz.answer_is_shown";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "from onCreate..........");
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        Button showAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        showAnswerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                mAnswerTextView.setText(mAnswerIsTrue ? R.string.true_button : R.string.false_button);
                Intent data = new Intent();
                data.putExtra(EXTRA_ANSWER_IS_SHOWN, true);
                setResult(RESULT_OK, data);


                int cx = view.getWidth() / 2;
                int cy = view.getHeight() / 2;
                float radius = view.getWidth();
                Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mAnswerTextView.setVisibility(View.VISIBLE);
                        view.setVisibility(View.INVISIBLE);
                    }
                });
                anim.start();
            }
        });

    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean isCheater(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "from onStart..........");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "from onResume..........");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "from onPause..........");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "from onStop..........");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "from onDestroy..........");
    }
}
