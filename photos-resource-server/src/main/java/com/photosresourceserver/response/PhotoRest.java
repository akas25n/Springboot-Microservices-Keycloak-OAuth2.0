package com.photosresourceserver.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PhotoRest {

    private String userId;
    private String photoId;
    private String albumId;
    private String photoTitle;
    private String photoDescription;
    private String photoUrl;
}
