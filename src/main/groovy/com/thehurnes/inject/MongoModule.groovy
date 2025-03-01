package com.thehurnes.inject

import com.google.inject.Provides
import com.mongodb.ConnectionString
import com.mongodb.async.client.MongoClient
import com.mongodb.async.client.MongoClients
import com.mongodb.async.client.MongoDatabase
import groovy.transform.CompileStatic
import ratpack.guice.ConfigurableModule

import javax.inject.Singleton

@CompileStatic
class MongoModule extends ConfigurableModule<Config> {

    static class Config {

        ConnectionString connectionString
        String dbName

    }

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    MongoClient mongoClient(Config config) {
        return MongoClients.create(config.connectionString)
    }

    @Provides
    @Singleton
    MongoDatabase db(Config config, MongoClient client) {
        return client.getDatabase(config.dbName)
    }

}
