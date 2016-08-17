package test.com.geoquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    private ImageButton mPreviousButton;
    private ImageButton mNextButton;

    private Question[] mQuestionBank;
    private TextView mQuestionText;
    private int mQuestionIndex = 0;

    private final String TAG = "QuizActivity";

    public QuizActivity() {
        super();
    }

    private void initializeListeners() {
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);

        mQuestionText = (TextView) findViewById(R.id.question_text);

        mQuestionBank = new Question[]{
                new Question(R.string.question_az, true),
                new Question(R.string.question_ca, true),
                new Question(R.string.question_ga, false),
                new Question(R.string.question_tx, true),
                new Question(R.string.question_ut, false),
                new Question(R.string.question_wa, false)
        };

        mQuestionText.setText(mQuestionBank[mQuestionIndex].getTextResId());

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(this.getClass().getName(), "from trueButton......");

                if (mQuestionBank[mQuestionIndex].isAnswerTrue()) {
                    Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                }


            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(this.getClass().getName(), "from falseButton.......");

                if (!mQuestionBank[mQuestionIndex].isAnswerTrue()) {
                    Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionIndex = (mQuestionBank.length + mQuestionIndex - 1) % (mQuestionBank.length);
                Log.i(this.getClass().getName(), "mQuestionIndex -> " + mQuestionIndex);
                mQuestionText.setText(mQuestionBank[mQuestionIndex].getTextResId());

            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mQuestionIndex = (mQuestionIndex + 1) % (mQuestionBank.length);
                Log.i(this.getClass().getName(), "mQuestionIndex -> " + mQuestionIndex);
                mQuestionText.setText(mQuestionBank[mQuestionIndex].getTextResId());

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "from onCreate().........");
        setContentView(R.layout.activity_quiz);
//        getResources()

        if(savedInstanceState !=null){
            mQuestionIndex = savedInstanceState.getInt("mQuestionIndex",0);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeListeners();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState().......");
        outState.putInt("mQuestionIndex", mQuestionIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"onRestoreInstanceState().......");
    }
}
