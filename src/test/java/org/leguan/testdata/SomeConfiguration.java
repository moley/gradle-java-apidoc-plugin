package org.leguan.testdata;

import org.leguan.docgen.ApiDoc;
import org.leguan.docgen.ApiDocItem;

import java.util.HashMap;

/**
 * Special properties for our project
 * @since 0.30
 * @deprecated Use NewerConfiguration instead
 * @author OleyMa
 */
@ApiDoc(type="properties")
public class SomeConfiguration extends HashMap {

  /**
   * This configuration is used to save the user
   * @since 0.30
   * @deprecated Not available anymore, because users are over estimated
   * @author OleyMa
   */
  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_USER = "leguan.someconfiguration.user";

  /**
   * This configuration is used to save the password
   */
  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_PASSWORD = "leguan.someconfiguration.password";

  /**
   * This configuration is used to save the adminuser
   */
  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_ADMIN_USER = "leguan.someconfiguration.adminUser";

  /**
   * This configuration is used to save the adminpassword
   */
  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_ADMIN_PASSWORD = "leguan.someconfiguration.adminPassword";

  /**
   * This configuration is used to save the folder
   */
  @ApiDocItem
  String PROPERTY_SOMECONFIGURATION_FOLDER = "leguan.someconfiguration.folder";

}
