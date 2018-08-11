package fr.jzrk.mybeer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.jzrk.mybeer.models.Beer;
import fr.jzrk.mybeer.services.SearchBeerService;
import fr.jzrk.mybeer.tasks.SearchBeerTask;

public class MainActivity extends AppCompatActivity {

    private final List<Beer> listBeers = new ArrayList<>();

    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = this.findViewById(R.id.plain_text_input);
        editText.setText("Do you <3 beer ?");
        Button searchButton = this.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "Click on button");
                Editable research = editText.getText();
                SearchBeerTask task = new SearchBeerTask();
                task.execute(research.toString());
                Log.i(LOG_TAG, "research : " + research.toString());
            }

        });
    }

}
