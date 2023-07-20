package com.example.finalproject.kakaoDTO;

import lombok.Data;

@Data
public class KakaoProfile {
    private Long id;
    private String connected_at;
    private Properties properties;
    private KakaoAccount kakao_account;

    @Data
    public static class Properties {
        private String nickname;
        public String profile_image;
        public String thumbnail_image;
        private String birthday;
        private String gender;

        public String getBirthday() {
            return this.birthday;
        }
    }

    @Data
    public static class KakaoAccount {
        private Boolean profile_nickname_needs_agreement;
        private Profile profile;
        private Boolean has_email;
        private Boolean email_needs_agreement;
        public Boolean is_email_valid;
        public Boolean is_email_verified;
        public String email;
        private String birthday; // birthday 필드 추가
        private Boolean has_birthday;
        private Boolean birthday_needs_agreement;
        private String birthday_type;
        private Boolean has_gender;
        private Boolean gender_needs_agreement;
        private String gender;

        @Data
        public static class Profile {
            private String nickname;
            public String thumbnail_image_url;
            public String profile_image_url;
        }
    }
}
