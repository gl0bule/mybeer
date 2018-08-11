package fr.jzrk.mybeer.tasks;

import android.os.AsyncTask;

import java.util.List;

import fr.jzrk.mybeer.models.Beer;
import fr.jzrk.mybeer.services.SearchBeerService;

public class SearchBeerTask extends AsyncTask<String, Void, List<Beer>> {


    private final SearchBeerService searchbeerservice;

    private final ITaskListener<List<Beer>> listener;


    public SearchBeerTask(ITaskListener<List<Beer>> listener) {
        searchbeerservice = new SearchBeerService();
        this.listener = listener;
    }

    @Override
    protected List<Beer> doInBackground(String... query) {
        return searchbeerservice.searchBeer(query[0]);
    }

    @Override
    protected void onPostExecute(List<Beer> beers) {
        super.onPostExecute(beers);
        this.listener.onTaskFinished(this);
    }
}
