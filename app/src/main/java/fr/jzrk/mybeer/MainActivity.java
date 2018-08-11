package fr.jzrk.mybeer;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import fr.jzrk.mybeer.models.Beer;
import fr.jzrk.mybeer.services.SearchBeerService;
import fr.jzrk.mybeer.tasks.ITaskListener;
import fr.jzrk.mybeer.tasks.SearchBeerTask;

public class MainActivity extends AppCompatActivity implements ITaskListener<List<Beer>>{

    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    private ListView listBeerView;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = this.findViewById(R.id.plain_text_input);
        Button searchButton = this.findViewById(R.id.searchButton);
        ListView listBeer = this.findViewById(R.id.listBeer);

        editText.setText("You said beer ?");

       adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());

        listBeer.setAdapter(adapter);

        ITaskListener<List<Beer>> listener = this;

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "Click on button");
                Editable research = editText.getText();
                SearchBeerTask task = new SearchBeerTask(listener);
                task.execute(research.toString());
                Log.i(LOG_TAG, "research : " + research.toString());

                }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTaskFinished(AsyncTask<?, ?, List<Beer>> task) {
        try {
            List<Beer> listBeer = task.get();
            List<String> listBeerName = listBeer.stream().map(b -> b.getName()).collect(Collectors.toList());
            adapter.clear();
            adapter.addAll(listBeerName);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
