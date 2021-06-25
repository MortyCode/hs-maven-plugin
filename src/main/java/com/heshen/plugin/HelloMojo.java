package com.heshen.plugin;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
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

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Class<? extends HelloMojo> aClass = getClass();

        System.out.println(aClass);


        getLog().info("<==============河神说: Hello Mojo start==============>"+greeting);

        List dependencies = mavenProject.getDependencies();
        getLog().info("[dependencies]---->");
        for (Object object : dependencies) {
            Dependency dependency =  (Dependency)object;
            getLog().info(dependency.toString());
        }



        getLog().info("<==============河神说: Hello Mojo end==============>"+greeting);


    }

}
