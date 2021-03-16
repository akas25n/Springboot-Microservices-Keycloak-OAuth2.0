package com.photoappwebclient.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AlbumRest {

    private  String userId;
    private String albumId;
    private String albumTitle;
    private String albumDescription;
    private String albumUrl;
}
