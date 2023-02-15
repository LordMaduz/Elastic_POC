package com.elastic.poc.config;

import lombok.Data;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;

@Configuration
@ConfigurationProperties(prefix = "elastic.cluster")
@EnableElasticsearchRepositories(basePackages = "com.dbs.itt.dega.repo")
@ComponentScan(basePackages = {"com.dbs.itt.dega"})
@Data
public class ElasticClientConfiguration extends ElasticsearchConfiguration {

    private String username;
    private String password;
    private String connectionString;


    @Override
    public ClientConfiguration clientConfiguration() {
        final String[] connections = connectionString.split(",");
        return ClientConfiguration.builder()
                .connectedTo(connections[0], connections[1], connections[2])
                .build();
    }

    private SSLContext createSSLContext() {
        SSLContext context = null;
        try {
            SSLContextBuilder sslBuilder = SSLContexts.custom()
                    .loadTrustMaterial(null, (x509Certificates, s) -> true);
            context = sslBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return context;
    }
}
