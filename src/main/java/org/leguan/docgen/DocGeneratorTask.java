package org.leguan.docgen;

import groovy.lang.Closure;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.gradle.api.tasks.TaskAction;
import org.leguan.language.file.Module;
import org.leguan.language.file.ModuleReader;
import org.leguan.language.java.*;
import org.leguan.refactoring.TemplatingAdapter;
import org.leguan.refactoring.api.RefactoringConfiguration;
import org.leguan.tasks.AbstractJavaMigrationTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Predicate;

/**
 * Generator task to generate api doc
 *
 * @author OleyMa
 * @since leguan 0.62
 */
@ApiDoc(type = "")
public class DocGeneratorTask extends AbstractJavaMigrationTask {

  private FullQualifiedName fqnApiDoc = new FullQualifiedName(ApiDoc.class.getName());
  private FullQualifiedName fqnApiDocItem = new FullQualifiedName(ApiDocItem.class.getName());


  JavaProject javaProject;

  JavaProjectFactory javaProjectFactory = new JavaProjectFactory();


  @ApiDocItem
  File outputPath = getProject().file("build/docs/apidoc");

  @ApiDocItem
  Collection<String> types;


  @ApiDocItem
  String javadocPrefix = "";

  Collection<File> generatedFiles = new ArrayList<File>();



  HashMap<String, DocGeneratorRootData> models = new HashMap<String, DocGeneratorRootData>();

  @TaskAction
  public void generate() throws IOException {

    Set<File> mainSourcepaths = getSourcepaths("main");

    for (File next : mainSourcepaths) {
      System.out.println("Sourcepath found: " + next.getAbsolutePath());
    }

    if (mainSourcepaths.isEmpty()) {
      System.out.println("No sourcepaths available in project " + getProject().getName());
      return;
    }

    File projectDir = getProject().getProjectDir();


    RefactoringConfiguration configuration = new RefactoringConfiguration();


    ModuleReader moduleReader = new ModuleReader(projectDir);
    Module module = moduleReader.getModule();
    javaProject = javaProjectFactory.createProject();
    javaProject.read(module, getClasspath("main"), mainSourcepaths, configuration);

    JavaTypeContainer javaTypeContainer = javaProject.getJavaTypeContainer();
    javaTypeContainer = getContainerClosure().call(javaTypeContainer);

    String projectname = getProject().getName();
    String version = getProject().getVersion().toString();

    for (String type : types) {
      DocGeneratorRootData rootData = new DocGeneratorRootData();
      models.put(type, rootData);
      rootData.setModulename(projectname);
      rootData.setVersion(version);

      List<DocGeneratorContainerData> containers = new ArrayList<DocGeneratorContainerData>();
      rootData.setContainers(containers);

      for (JavaType nextType : javaTypeContainer.getAllItems()) {

        for (JavaAnnotation annotation : nextType.annotations()) {

          if (type.equals(annotation.getValue("type"))) {
            System.out.println (" - Type " + nextType.getElementName().toString());

            DocGeneratorContainerData docGeneratorData = new DocGeneratorContainerData();
            containers.add(docGeneratorData);
            docGeneratorData.setType(nextType.getName());
            docGeneratorData.setDoc(nextType.getJavadoc());
            docGeneratorData.setReference(getReference(projectname, nextType.getName()));


            for (JavaField nextField : nextType.getFields().getAllItems()) {
              for (JavaAnnotation nextAnnotation : nextField.annotations()) {
                try {
                  if (nextAnnotation.getType().equals(fqnApiDocItem)) {
                    DocGeneratorItemData childData = new DocGeneratorItemData();
                    childData.setDoc(nextField.getJavadoc());
                    childData.setName(nextField.getName());
                    childData.setValue(nextField.getInitialValue());
                    childData.setCode(nextField.getType().getName() + " " + nextField.getName());

                    docGeneratorData.getItems().add(childData);
                  }
                } catch (Exception e) {
                  getLogger().info("Annotation could not be read in field " + nextField.getName() + " of type " + nextType.getName());
                }
              }
            }

            for (JavaMethod nextMethod : nextType.getMethods().getAllItems()) {
              for (JavaAnnotation nextAnnotation : nextMethod.annotations()) {
                try {
                  if (nextAnnotation.getType().equals(fqnApiDocItem)) {
                    DocGeneratorItemData childData = new DocGeneratorItemData();
                    childData.setDoc(nextMethod.getJavadoc());
                    childData.setName(nextMethod.getName());

                    Collection<String> params = new ArrayList<String>();
                    for (JavaMethodParameter nextParam : nextMethod.getParameters()) {
                      params.add(nextParam.getType().getName() + " " + nextParam.getName());
                    }

                    String parameterList = "(" + String.join(", ", params) + ")";

                    childData.setCode(nextMethod.getType().getName() + " " + nextMethod.getName() + " " + parameterList);
                    docGeneratorData.getItems().add(childData);
                  }
                } catch (Exception e) {
                  getLogger().info("Annotation could not be read in field " + nextMethod.getName() + " of type " + nextType.getName());
                }

              }
            }

          }
        }
      }

      System.out.println ("Creating " + containers.size() + " artifacts for apidoc type " + type);

      if (containers.size() > 0) {
        File file = new File(outputPath, type + ".adoc");
        generatedFiles.add(file);
        file.getParentFile().mkdirs();
        TemplatingAdapter adapter = new TemplatingAdapter();
        adapter.setTemplatesBasePath("apidoc");
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("root", rootData);
        adapter.execute(new FileOutputStream(file), type + ".ftlh", hashMap);
      }
    }

    if (generatedFiles.size() > 0) {

      String indexContent = "= API of project " + getProject().getName() + " - Version " + getProject().getVersion().toString() + "";
      indexContent += "\n";
      indexContent += ":nofooter:\n\n";

      for (File next : generatedFiles) {
        String capitalized = StringUtils.capitalize(next.getName().replace(".adoc", ""));
        indexContent += "link:" + next.getName().replace(".adoc", ".html") + "[" + capitalized + "] \n\n";
      }


      File file = new File(outputPath, "index.adoc");
      file.getParentFile().mkdirs();
      FileUtils.write(file, indexContent, Charset.defaultCharset());
    }

  }

  private Closure<JavaTypeContainer> getContainerClosure() {
    return new Closure<JavaTypeContainer>(null) {
      public JavaTypeContainer doCall(JavaTypeContainer originContainer) {
        return originContainer.filterContainer(new Predicate<JavaType>() {
          @Override
          public boolean test(JavaType javaType) {
            for (JavaAnnotation annotation : javaType.annotations()) {
              try {
                if (annotation.getType().equals(fqnApiDoc)) {
                  return true;
                }
              } catch (Exception e) {
                getLogger().info("Annotation could not be read in type " + javaType.getName());
              }
            }
            return false;
          }
        });
      }

    };
  }

  private String getReference(final String modulename, final String fqn) {
    return javadocPrefix + "/" + modulename + "/" + fqn.replace(".", "/") + ".html";
  }




  public HashMap<String, DocGeneratorRootData> getModels() {
    return models;
  }

  public void setModels(HashMap<String, DocGeneratorRootData> models) {
    this.models = models;
  }

  public Collection<String> getTypes() {
    return types;
  }

  public void setTypes(final Collection<String> types) {
    this.types = types;
  }

  public String getJavadocPrefix() {
    return javadocPrefix;
  }

  public void setJavadocPrefix(String javadocPrefix) {
    this.javadocPrefix = javadocPrefix;
  }
}
