package com.fit_goal;

import com.fit_goal.credentials.Credentials;
import com.fit_goal.credentials.Seed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
class MongoDBConnection {

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

