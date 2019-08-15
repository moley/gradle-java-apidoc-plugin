package org.leguan.testdata;

import org.leguan.docgen.ApiDoc;
import org.leguan.docgen.ApiDocItem;

import java.util.HashMap;

@ApiDoc(type="hans")
public class AnnotatedButDifferentTypeConfiguration extends HashMap {

  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_USER = "leguan.someconfiguration.user";


}
