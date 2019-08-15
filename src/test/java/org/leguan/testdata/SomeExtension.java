package org.leguan.testdata;

import org.leguan.analysis.BuildEnvironmentConnectionException;
import org.leguan.analysis.ProgressNotifier;
import org.leguan.docgen.ApiDoc;
import org.leguan.docgen.ApiDocItem;

import java.net.URL;

/**
 * Special extension for our project
 */
@ApiDoc(type="extensions")
public interface SomeExtension {
  /**
   * trigger a collection of jobs
   * @param nodename nodename to be built on
   * @param username username to login to build tool, username of current user
   * @param password password to login to build tool, password of current user
   * @param progressNotifier notifies the progress of the build
   * @throws BuildEnvironmentConnectionException if exception occurs when connecting with build environment
   * @return url to find the triggered job again
   * @deprecated  Because old rubbish should be removed
   * @author OleyMa
   * @since 0.40
   */
  @ApiDocItem
  URL triggerJob (final String nodename,
                  final String username, final String password,
                  final ProgressNotifier progressNotifier) throws BuildEnvironmentConnectionException;


  /**
   * configures a collection of projects
   * @param nodename         nodename to be built on
   * @param username         username to login to build tool, username of current user
   * @param password         password to login to build tool, pw of current user
   * @param progressNotifier notifies the progress of the build
   * @throws BuildEnvironmentConnectionException if exception occurs when connecting with build environment
   * @deprecated  Weils Leben eben net wurscht is
   */
  void configureProject(final String nodename,
                        final String username,
                        final String password,
                        final ProgressNotifier progressNotifier) throws BuildEnvironmentConnectionException;


}
