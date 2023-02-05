package com.dbs.itt.dega.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
    private String host;
    private String port;


    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(host + ":" + port)
                .withBasicAuth(username, password)
                .build();
    }

    @Bean
    public RestHighLevelClient client() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        final RestClientBuilder builder = RestClient.builder(new HttpHost(host, Integer.valueOf(port)))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        return new RestHighLevelClient(builder);
    }

    private SSLContext createSSLContext() {
        SSLContext context = null;
        try {
            SSLContextBuilder sslBuilder = SSLContexts.custom()
                    .loadTrustMaterial(null, (x509Certificates, s) -> true);
            context = sslBuilder.build();
        } catch (Exception e) {

        }
        return context;
    }
}
