package com.example.kanal_kanalearn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.kanal_kanalearn.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//basically sqlite stuff
public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "KanaQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionTable(){
        Question q1 = new Question("[Hiragana] Which letter is [と] ?", "to", "ni", "yo", 1);
        addQuestion(q1);
        Question q2 = new Question("[Hiragana] Which letter is [れ] ?", "ko", "sa", "re", 3);
        addQuestion(q2);
        Question q3 = new Question("[Hiragana] Which letter is [ざ] ?", "ki", "gi", "chi", 2);
        addQuestion(q3);
        Question q4 = new Question("[Hiragana] Which one is [sa] in kana letter ?", "か", "さ", "る", 2);
        addQuestion(q4);
        Question q5 = new Question("[Hiragana] Which one is [wo] in kana letter ?", "を", "わ", "ん", 1);
        addQuestion(q5);
        Question q6 = new Question("[Katakana] Which letter is [テ] ?", "te", "o", "ru", 1);
        addQuestion(q6);
        Question q7 = new Question("[Katakana] Which letter is [ハ] ?", "ba", "he", "ha", 3);
        addQuestion(q7);
        Question q8 = new Question("[Katakana] Which letter is [き] ?", "ki", "nu", "me", 1);
        addQuestion(q8);
        Question q9 = new Question("[Katakana] Which one is [na] in kana letter ?", "ユ", "ナ", "り", 2);
        addQuestion(q9);
        Question q10 = new Question("[Katakana] Which one is [ru] in kana letter ?", "あ", "る", "だ", 2);
        addQuestion(q10);
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if(c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;

    }
}
