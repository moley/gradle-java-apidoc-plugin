package org.leguan.docgen;

import java.util.ArrayList;
import java.util.List;

public class DocGeneratorRootData {

  private String modulename;

  private String version;

  private List<DocGeneratorContainerData> containers = new ArrayList<DocGeneratorContainerData>();

  public String getModulename() {
    return modulename;
  }

  public void setModulename(String modulename) {
    this.modulename = modulename;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public List<DocGeneratorContainerData> getContainers() {
    return containers;
  }

  public void setContainers(List<DocGeneratorContainerData> containers) {
    this.containers = containers;
  }
}
