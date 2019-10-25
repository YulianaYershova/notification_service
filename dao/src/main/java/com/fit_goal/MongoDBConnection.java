package com.fit_goal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MongoDBConnection {

    /**
     * The credentials username and password.
     */
    private Credentials credentials;

    /**
     * The lis of seeds.
     */
    private List<Seed> seeds;

    /**
     * The db.
     */
    private String database;
}

