package org.leguan.docgen;

import org.junit.Assert;
import org.junit.Test;

public class DocGeneratorItemDataTest {

  @Test
  public void readDocu () {
    DocGeneratorItemData docGeneratorItemData = new DocGeneratorItemData();
    docGeneratorItemData.setDoc("Hello\nsecondLine\n   @author OleyMa\n@deprecated Must be replaced");
    Assert.assertEquals ("Hello\nsecondLine\n", docGeneratorItemData.getPlainDoc());
    Assert.assertEquals ("OleyMa", docGeneratorItemData.getDocTags("author"));
    Assert.assertEquals ("Must be replaced", docGeneratorItemData.getDocTags("deprecated"));
  }

  @Test
  public void emptyLine () {
    DocGeneratorItemData docGeneratorItemData = new DocGeneratorItemData();
    docGeneratorItemData.setDoc("First line\n\nThird line");
    Assert.assertEquals ("First line\n\nThird line\n", docGeneratorItemData.getPlainDoc());
  }

  @Test
  public void bold () {
    DocGeneratorItemData docGeneratorItemData = new DocGeneratorItemData();
    docGeneratorItemData.setDoc("<b>First line</b>");
    Assert.assertEquals ("*First line*\n", docGeneratorItemData.getPlainDoc());
  }
}
