package kr.kimyoungjae.admin.common.enums;

import lombok.Getter;

@Getter
public enum Roles {
      USER("ROLE_USER")
    , ADMIN("ROLE_ADMIN");


    String value;
    Roles(String value) {
          this.value = value;
    }

}
