package test.com.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    private ImageButton mPreviousButton;
    private ImageButton mNextButton;

    private Button cheatButton;

    private Question[] mQuestionBank;
    private TextView mQuestionText;
    private int mQuestionIndex = 0;

    private final String TAG = "QuizActivity";
    private final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;


    public QuizActivity() {
        super();
    }

    private void initializeListeners() {

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);

        mQuestionText = (TextView) findViewById(R.id.question_text);

        cheatButton = (Button) findViewById(R.id.cheat_button);

        mQuestionBank = new Question[]{
                new Question(R.string.question_az, true),
                new Question(R.string.question_ca, true),
                new Question(R.string.question_ga, false),
                new Question(R.string.question_tx, true),
                new Question(R.string.question_ut, false),
                new Question(R.string.question_wa, false)
        };

        mQuestionText.setText(mQuestionBank[mQuestionIndex].getTextResId());

        mTrueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(this.getClass().getName(), "from trueButton......");

                if (mQuestionBank[mQuestionIndex].isAnswerTrue()) {
                    checkAnswer(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                } else {
                    checkAnswer(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
                }

            }
        });

        mFalseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(this.getClass().getName(), "from falseButton.......");

                if (!mQuestionBank[mQuestionIndex].isAnswerTrue()) {
                    checkAnswer(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
                } else {
                    checkAnswer(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
                }
            }
        });

        mPreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionIndex = (mQuestionBank.length + mQuestionIndex - 1) % (mQuestionBank.length);
                Log.i(this.getClass().getName(), "mQuestionIndex -> " + mQuestionIndex);
                mQuestionText.setText(mQuestionBank[mQuestionIndex].getTextResId());
                mIsCheater = false;

            }
        });

        mNextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mQuestionIndex = (mQuestionIndex + 1) % (mQuestionBank.length);
                Log.i(this.getClass().getName(), "mQuestionIndex -> " + mQuestionIndex);
                mQuestionText.setText(mQuestionBank[mQuestionIndex].getTextResId());
                mIsCheater= false;

            }
        });

        cheatButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(this.getClass().getName(), "clicked on cheat button.......");
                Intent intent = CheatActivity.newIntent(QuizActivity.this, mQuestionBank[mQuestionIndex].isAnswerTrue());
//              startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);  // REQUEST_CODE_CHEAT is like identifier in onActivityResult method
//
//                //how to open whatsapp from my app
//
//
//                PackageManager pm = getPackageManager();
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "from onCreate().........");
        setContentView(R.layout.activity_quiz);
//        getResources()

        if (savedInstanceState != null) {
            mQuestionIndex = savedInstanceState.getInt("mQuestionIndex", 0);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeListeners();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "from onActivityResult..........");

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CODE_CHEAT && data != null) {
                mIsCheater = CheatActivity.isCheater(data);

            }
        }

    }

    private void checkAnswer(Context context, int messageResourceId, int toastLength) {

        if(mIsCheater){
            Toast.makeText(context, R.string.judgement_toast, toastLength).show();
        }else{
            Toast.makeText(context, messageResourceId, toastLength).show();
        }

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
        Log.i(TAG, "onSaveInstanceState().......");
        outState.putInt("mQuestionIndex", mQuestionIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState().......");
    }
}
