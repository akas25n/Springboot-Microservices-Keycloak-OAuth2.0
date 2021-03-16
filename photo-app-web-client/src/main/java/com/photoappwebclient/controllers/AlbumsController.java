package com.photoappwebclient.controllers;

import com.photoappwebclient.response.AlbumRest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class AlbumsController {

    OAuth2AuthorizedClientService oauth2AuthorizedClientService;
    //RestTemplate restTemplate;
    WebClient webClient;

    public AlbumsController(OAuth2AuthorizedClientService oauth2AuthorizedClientService, WebClient webClient) {
        this.oauth2AuthorizedClientService = oauth2AuthorizedClientService;
        this.webClient = webClient;
    }

    @GetMapping("/albums")
    public String getAlbums(Model model
                            /*@AuthenticationPrincipal OidcUser principal*/){
        //reading JWT Access token
        /*Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oAuth2Client = oauth2AuthorizedClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        String jwtAccessToken = oAuth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken: " + jwtAccessToken);

        System.out.println("Principal = " + principal);
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue: " + idTokenValue);

        String url = "http://localhost:8083/albums"; // API Gateway
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<List<AlbumRest>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AlbumRest>>() {});
        List<AlbumRest> albums = responseEntity.getBody();*/

        String url = "http://localhost:8083/albums"; // API Gateway
        List<AlbumRest> albums = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>(){})
                .block();

        model.addAttribute("albs", albums);

        return "albums";
    }
}
