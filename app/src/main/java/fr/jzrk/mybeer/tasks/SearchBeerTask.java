package fr.jzrk.mybeer.tasks;

import android.os.AsyncTask;

import java.util.List;

import fr.jzrk.mybeer.models.Beer;
import fr.jzrk.mybeer.services.SearchBeerService;

public class SearchBeerTask extends AsyncTask<String, Void, List<Beer>> {


    private final SearchBeerService searchbeerservice;

    public SearchBeerTask() {
        searchbeerservice = new SearchBeerService();
    }

    @Override
    protected List<Beer> doInBackground(String... query) {
        return searchbeerservice.searchBeer(query[0]);
    }
}
