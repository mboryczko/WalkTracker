package com.mjbor.ready.database;

import com.mjbor.ready.model.Walk;

import java.util.List;

/**
 * Created by mjbor on 9/26/2017.
 */

public interface IRepository {

    public void updateLastWalk(Walk walk);
    public void saveWalk(Walk walk);
    public List<Walk> getAllWalks();
}
