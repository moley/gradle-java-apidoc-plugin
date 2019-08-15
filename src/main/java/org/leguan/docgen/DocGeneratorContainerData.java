package org.leguan.docgen;

import java.util.ArrayList;
import java.util.Collection;

public class DocGeneratorContainerData extends IncludesJavadoc {
  private String type;
  private String reference;

  private Collection<DocGeneratorItemData> items = new ArrayList<DocGeneratorItemData>();

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public Collection<DocGeneratorItemData> getItems() {
    return items;
  }

  public void setItems(Collection<DocGeneratorItemData> items) {
    this.items = items;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

}
