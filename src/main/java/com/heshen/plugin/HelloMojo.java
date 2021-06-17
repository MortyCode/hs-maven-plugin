package com.heshen.plugin;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：河神
 * @date ：Created in 2021/1/28 2:16 下午
 *
 */
@Mojo(name = "hello",requiresDependencyResolution = ResolutionScope.COMPILE)
public class HelloMojo extends AbstractMojo {

    /**
     * The greeting to display.
     */
    @Parameter( property = "greeting", defaultValue = "Hello World!" )
    private String greeting;

    @Parameter( defaultValue = "${project}")
    private MavenProject mavenProject;

    public void execute() throws MojoExecutionException, MojoFailureException {
        Class<? extends HelloMojo> aClass = getClass();

        System.out.println(aClass);


        getLog().info("<==============河神说: Hello Mojo start==============>"+greeting);

        List dependencies = mavenProject.getDependencies();
        getLog().info("[dependencies]");
        for (Object dependency : dependencies) {
            getLog().info(dependency.toString());
        }
        getLog().info("[basedir]");
        File basedir = mavenProject.getBasedir();

        List<File> files = searchFiles(basedir, "java");

        for (File file : files) {
            long l = file.lastModified();
            Date date = new Date(l);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = dateFormat.format(date);
            getLog().info(file.getName()+"[lastModified]"+format);
        }

        File file = searchJar(basedir);
        getLog().info(">>>>>>>>jar:");
        if (file!=null){
            getLog().info(file.getName());
        }

        try {
            Runtime.getRuntime().exec("touch aa 123");
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLog().info("<==============河神说: Hello Mojo end==============>"+greeting);


    }

    public static List<File> searchFiles(File folder, final String keyword) {
        List<File> result = new ArrayList<File>();
        if (folder.isFile()){
            result.add(folder);
        }
        File[] subFolders = folder.listFiles((file) -> {
            return file.getName().toLowerCase().endsWith(keyword);
        });


        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }

        return result;
    }

    public static File searchJar(File folder) {

        File[] files = folder.listFiles((file) -> {
            return file.isDirectory() && "target".equals(file.getName().toLowerCase());
        });

        if (files != null&&files.length>0) {
            File target = files[0];
            File[] subFolders = target.listFiles((file) -> {
                return file.getName().toLowerCase().endsWith(".jar");
            });

            if (subFolders != null&&subFolders.length>0) {
                return subFolders[0];
            }
            return null;
        }
        return null;
    }
}
