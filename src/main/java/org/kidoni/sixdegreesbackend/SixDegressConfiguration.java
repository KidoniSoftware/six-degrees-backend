package org.kidoni.sixdegreesbackend;

import org.kidoni.sixdegreesbackend.tmdb.TmdbClient;
import org.kidoni.sixdegreesbackend.tmdb.TmdbConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class SixDegressConfiguration {
    @Bean
    public RestClient restClient(TmdbConfigurationProperties tmdbConfigurationProperties) {
        return RestClient.builder()
                .baseUrl(tmdbConfigurationProperties.getUrl())
                .requestFactory(new JdkClientHttpRequestFactory())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tmdbConfigurationProperties.getToken())
                .build();
    }

    @Bean
    public TmdbClient tmdbClient(RestClient restClient) {
        return new TmdbClient(restClient);
    }
}
