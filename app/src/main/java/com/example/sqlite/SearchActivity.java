package com.example.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;

public class SearchActivity extends AppCompatActivity {
    private EditText etSearch;
    private ListView lvResutl;
    private Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.etSearchKeyword);
        lvResutl = findViewById(R.id.lvSearchResult);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadWord();
            }
        });
    }

    private void LoadWord() {
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = myHelper.GetAllWords(sqLiteDatabase);

        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 0; i<wordList.size(); i++){
            hashMap.put(wordList.get(i).getUsername(), wordList.get(i).getPassword());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())
        );
        lvResutl.setAdapter(stringArrayAdapter);
    }
}
