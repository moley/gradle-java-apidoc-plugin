package org.leguan.testdata;

import org.leguan.docgen.ApiDocItem;

import java.util.HashMap;

public class NotAnnotatedConfiguration extends HashMap {

  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_USER = "leguan.someconfiguration.user";

  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_PASSWORD = "leguan.someconfiguration.password";

  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_ADMIN_USER = "leguan.someconfiguration.adminUser";

  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_ADMIN_PASSWORD = "leguan.someconfiguration.adminPassword";

  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_FOLDER = "leguan.someconfiguration.folder";

}
