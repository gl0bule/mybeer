package fr.jzrk.mybeer.services;

import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import fr.jzrk.mybeer.models.Beer;
import fr.jzrk.mybeer.models.SearchResponse;

public class SearchBeerService implements ISearchBeerService{

    private static final String KEY = "3fb832160850da3fb006f7009a11cbd2";

    private static final String BASE_URI = "https://api.brewerydb.com/v2";

    private final static String LOG_TAG = SearchBeerService.class.getSimpleName();

    private final RestTemplate restTemplate;

    public SearchBeerService() {

         restTemplate = new RestTemplate();
         restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
    }

    @Override
    public List<Beer> searchBeer(String query) {
        URI uri = UriComponentsBuilder.fromHttpUrl(BASE_URI)//
                .path("search")//
                .queryParam("key", KEY)//
                .queryParam("q", query)//
                .build()//
                .encode()//
                .toUri();//

        HttpHeaders requestHeaders = new HttpHeaders();
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(acceptableMediaTypes);

        HttpEntity httpEntity = new HttpEntity<Object>(requestHeaders);

        ResponseEntity<SearchResponse> responseEntity = restTemplate
                .exchange(uri, HttpMethod.GET, httpEntity, SearchResponse.class );

        Log.d(LOG_TAG, "nb beers: " + responseEntity.getBody().getData().size());


        return responseEntity.getBody().getData();
    }
}
