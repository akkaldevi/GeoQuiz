package test.com.geoquiz;

/**
 * Created by Yugandhar on 8/5/16.
 */
public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {

        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public Question(int textResId, boolean answerTrue) {

        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
