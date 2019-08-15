package org.leguan.docgen;

import org.junit.Test;

import java.io.IOException;

public class JavadocFormatterTest {

  private JavadocFormatter javadocFormatter = new JavadocFormatter();

  @Test
  public void javadoc () throws IOException {

    String originJavadoc =  "/**\n" +
      " * The task creates a clean and \"ready to use\" project in any folder you like.<br>\n" +
      " * Based on the template you will get:<br>\n" +
      "\n" +
      " * - first item<br>\n" +
      " * - second item<br>\n";

    String filtered = javadocFormatter.getJavadoc(originJavadoc);
    System.out.println ("Filtered:\n" + filtered);

    IncludesJavadoc includesJavadoc = new IncludesJavadoc();
    includesJavadoc.setDoc(filtered);
    System.out.println ("Plain doc:\n" + includesJavadoc.getPlainDoc());
  }

}
