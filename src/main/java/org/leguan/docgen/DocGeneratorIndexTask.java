package org.leguan.docgen;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;

/**
 * Generator task to generate api doc index
 * @author OleyMa
 * @since leguan 0.62
 */
public class DocGeneratorIndexTask extends DefaultTask {

  @TaskAction
  public void generate () throws IOException {

    for (Project project: getProject().getAllprojects()) {
    //TODO
    }




  }


}
