package fr.jzrk.mybeer.services;

import java.util.List;

import fr.jzrk.mybeer.models.Beer;

public interface ISearchBeerService {

    List<Beer> searchBeer(String query);

}
