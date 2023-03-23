package kr.kimyoungjae.admin.domain.experiences.enums;

import lombok.Getter;

@Getter
public enum ExperienceType {
      WORK("Work", "회사")
    , EDUCATION("Education", "교육 기관")
    , CERTIFICATE("Certificate", "자격증")
    , OTHER("Other", "기타 활동");

      private String value;
      private String comment;

      ExperienceType(String value, String comment) {
          this.value = value;
          this.comment = comment;
      }


}
