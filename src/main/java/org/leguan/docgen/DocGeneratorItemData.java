package org.leguan.docgen;

import java.util.HashMap;

public class DocGeneratorItemData extends IncludesJavadoc {





  private String name;

  private String value;

  private String code;



  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value != null ? value: "";
  }

  public void setValue(String value) {
    this.value = value;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


}
