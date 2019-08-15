package org.leguan.docgen;

import org.apache.commons.io.FileUtils;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class DocGeneratorTaskTest {

  @Test
  public void propertiesTyped () throws IOException {

    File projectDir = new File ("build/apidocExample");
    System.out.println ("Testdata in " + projectDir.getAbsolutePath());

    File originSourceDir = new File ("src/test/java/org/leguan/testdata");
    File sourceDir = new File (projectDir, "src/main/java/org/leguan/testdata");
    sourceDir.mkdirs();
    FileUtils.copyDirectory(originSourceDir, sourceDir);


    Project project = ProjectBuilder.builder().withProjectDir(projectDir).build();
    project.setVersion("0.58");
    project.getPlugins().apply(JavaPlugin.class);
    project.getPlugins().apply(DocGeneratorPlugin.class);

    DocGeneratorTask docuReferenceGenerator = project.getTasks().create("hans", DocGeneratorTask.class);
    docuReferenceGenerator.setJavadocPrefix("../javadoc");
    docuReferenceGenerator.setClasspathEntries(injectClasspath());
    docuReferenceGenerator.setTypes(Arrays.asList("properties", "extensions"));
    docuReferenceGenerator.generate();

    for (File next: docuReferenceGenerator.outputPath.listFiles()) {
      System.out.println (next.getAbsolutePath());
      System.out.println(String.join("\n", Files.readAllLines(next.toPath())));
      System.out.println ("-------------");
    }

  }

  private Collection<URL> injectClasspath () throws MalformedURLException {
    Collection<URL> urls = new ArrayList<URL>();
    String [] entries = System.getProperty("java.class.path").split(":");
    for (String next: entries) {
      if (new File (next).exists() )
        urls.add(new File (next).toURI().toURL());
    }

    return urls;
  }
}
