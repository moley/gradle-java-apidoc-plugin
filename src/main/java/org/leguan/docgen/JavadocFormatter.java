package org.leguan.docgen;

public class JavadocFormatter {

  String getJavadoc(final String javadoc) {
    String filtered = javadoc.replace("/*", "").replace("*/", "").replace("*", "").trim();
    filtered = filtered.replace("<BR>", " +\n").replace("<br>", " +\n");
    filtered = filtered.replace("<b>", "*").replace("</b>", "*");

    return filtered;
  }
}
