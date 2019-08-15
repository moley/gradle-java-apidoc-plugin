package org.leguan.docgen;

import java.util.HashMap;

public class IncludesJavadoc {

  private String doc;

  private String plainDoc = "";

  private JavadocFormatter javadocFormatter = new JavadocFormatter();


  private HashMap<String, String> docTags = new HashMap<>();

  public String getDoc() {
    return doc;
  }

  public void setDoc(String comment) {
    comment = javadocFormatter.getJavadoc(comment);
    System.out.println ("setDoc " + comment);
    this.doc = comment;
    for (String nextLine: comment.split("\n")) {
      String trimmedLine = nextLine.trim();
      if (trimmedLine.contains("@")) {
        String key = trimmedLine.split(" ")[0];
        String keyWithoutTag = key.substring(1);
        String value = trimmedLine.replace(key, "").trim();
        docTags.put(keyWithoutTag, value);
      }
      else
        plainDoc += (trimmedLine + "\n");
    }
  }

  public String getPlainDoc() {
    return plainDoc;
  }

  public String getDocTags(String key) {
    return docTags.get(key);
  }
}
