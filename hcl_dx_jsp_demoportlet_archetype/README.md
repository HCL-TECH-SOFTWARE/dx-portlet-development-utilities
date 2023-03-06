# DX Portlet Archetype
This is a custom Archetype which is based on [Maven Portlet Archetype](https://maven.apache.org/archetypes/maven-archetype-portlet/) and can be used for creating HCL DX JSP Demo Portlets. Sample scripts for deployment/update and undeployment of a sample JSP Portlet are provided when using that Archetype on a Microsoft Visual Studio Code IDE. The .vscode folder can be ignored, when using any other IDE.

With the custom Archetype it is possible to generate a ready for use HCL DX JSP Demo Portlet. It can be used to show up a whole development lifecycle of an HCL DX JSP Demo Portlet.

# Typical Development Lifecycle
1) [DX JSP Portlet Generation](#create-a-hcl-dx-jsp-demo-portlet-sample-project) (Source-code generation) by using this Archetype
2) [WAR-File Generation](#build-the-war-file-deployment-artifact) (Deployment artifact)
3) [Portlet Deployment](#deployupdate-or-undeploy-the-portlet-using-dxclient-optional) (A sample XML file will be automatically generated when using this Archetype. 
    When using Microsoft Visual Studio Code, sample tasks are available a to deploy and update the portlet on a local HCL DX server.)
4) [Optional Test it](#test-the-generated-portlet-optional) (By adding the demo portlet manually to a page.)
5) [Portlet Undeployment](#deployupdate-or-undeploy-the-portlet-using-dxclient-optional) (A sample XML file will be automatically generated when using this Archetype. 
    When using Microsoft Visual Studio Code, a sample task is available to undeploy the portlet on a local HCL DX server.)


# Prerequistes

  ## Prerequistes to build and install the archetype
    - Maven needs to be installed

  ## Prerequistes to create a HCL DX JSP Demo Portlet
    - Maven needs to be installed
    - Java 1.8 needs to be installed
    - This archtype needs to be installed 

  ## Prerequistes to deploy a sample HCL DX JSP Demo Portlet 
    - When creating a new project based on this Archteype, sample scripts will be created which are prepared to run with a local HCL DX server. 
      If you want to deploy the sample WAR-file using the generated scripts, a HCL Digital Experience Server need to be running locally. If you want to deploy the WAR file on any other DX server, the script (tasks.json) need to be changed to point to the new server.
    - HCL DXClient needs to be installed on the local system and the configuration settings need to pointing to a local HCL DX server instance. 
      (For details please check: https://help.hcltechsw.com/digital-experience/9.5/containerization/dxclient.html)

# Install the DX archetype
Follow the commands in the below order to install the archetype.

1) After downloading the dx-portlet-development-utilities git repository, go into the hcl_dx_jsp_demoportlet_archetype folder
2) Run ```mvn clean install``` to install the archetype JAR.

# Create a HCL DX JSP Demo Portlet sample project
As soon as the new archetype is installed a new HCL JSP Demo Portlet can be created by using the following command: 

```mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:generate -DarchetypeArtifactId="hcl_dx_jsp_demoportlet_archetype" -DarchetypeGroupId="com.hcl.dx.demo" -DarchetypeVersion="1.0" -DgroupId="com.hcl.dx" -DartifactId="jspdemoportlet"```

You may want to change the -DgroupId and/or -DartifactId parameter values for your needs. 

# Build the WAR-File (Deployment artifact)
1) Go into the project directory
2) Run  ```mvn clean package``` to generate the HCL DX JSP Demo Portlet WAR file. 
   You will find the WAR file under a path similar to this: 
   ```/<project-folder>/target/jspdemoportlet-1.0.war```.

# Deploy/Update or Undeploy the Portlet using DXClient (Optional)
A new project that will be generated from this custom Archetype already include sample XML files to deploy/update and undeploy the HCL DX JSP Demo Portlet on a local HCL-DX server. When using Microsoft Visual Studio Code IDE you will also find two tasks that can be used for deployment/update (task: deploy_OR_Update_Portlet) and undeployment (task: undeploy_Portlet). For details, check file ```./vscode/tasks.json```. If any other IDE will be used the whole .vscode folder can be ignored and/or deleted. 

If a manual deployment or update of the portlet is needed the following dxclient command can be used:

```dxclient deploy-portlet -hostname <hostname> -dxPort <port> -dxConnectUsername <user> -dxConnectPassword <password> -dxUsername <user> -dxPassword <password> -warFile <workspaceFolder>/target/jspdemoportlet-1.0.war  -xmlFile <workspaceFolder>/scripts/DeployPortlet.xml```

If a manual undeployment of the portlet is needed the following command can be used:

```dxclient undeploy-portlet -hostname <hostname> -dxPort <port> -dxConnectUsername <user> -dxConnectPassword <password> -dxUsername <user> -dxPassword <password> -xmlFile <workspaceFolder>/scripts/UndeployPortlet.xml```

# Test the generated Portlet (Optional)
As soon as the HCL DX JSP Demo Portlet is deployed the following steps may used to test the portlet:

1) create a new empty page or use an existing one on your HCL DX Server (Make sure that the permissions are set correctly)
2) add the Portlet to the page and make sure that the portlet permissions are set correctly
3) Open the page and test the portlet

